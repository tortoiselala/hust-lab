
#include <cmath>
#include "TDebug.h"
#include "GBNSenderWindow.h"
/*
窗口维护以数组进行模拟
每个窗口拥有两个基本属性：
	1.maxSeqNum
	2.windowSize
每个窗口会动态维护以下属性
	1.basePacketPosition
	2.nextPacketPosition
	2.baseSeqNum
	3.nextSeqNum
	注：为了方便实现，在实际实现的过程中会额外申请一个存储空间，即数组大小为windowSize + 1
		basePacketPosition标识时间最早的等待ack的分组在数组中的位置
		nextPacketPosition标识下一次请求的报文位置
		baseSeqNum为时间最早的等待ack的分组的报文序号
		nextSeqNum为下一次请求所返回的报文序号，报文序号满足以下关系 seqnum <= maxSeqNum

	规定：1).nextPacketPosition == basePacketPosition时，数组为空
				nextPacketPosition == basePacketPosition - 1时，数组为满
		  2).报文序号数目必须大于窗口数目

每个窗口拥有以下可以调用的方法
	1.int getNumOfPacketSentWaitingAck();
		该方法返回一个整形值表示当前数组已经存储的分组数量，计算的方法为
			对于nextPacketPosition >= basePacketPosition
				return basePacketPosition - nextPacketPosition
			对于nextPacketPosition < basePacketPosition
				return windowSize - basePacketPosition + nextPacketPosition
	2.bool isPacketSentWaitingAckArrayNone()
		该方法返回一个bool值，表示数组是否为空
			根据getNumOfPacketSentWaitingAck的返回值判断，为0时返回true, 否则false
	3.bool isPacketSentWaitingAckArrayFull()
		该方法返回一个bool值，表示数组是否已满
			根据getNumOfPacketSentWaitingAck的返回值判断，为1时返回true， 否则false
	4.bool getNextSeqNumAndNextPacketPosition(int & nextSeqNum, int & nextPacketPosition);
		该方法返回bool值表示申请成功与否，
			申请成功后，报文序号与存储位置由nextSeqNum与nextPacketPosition表示
			首先调用isPacketSentWaitingAckArrayFull()判断是否已满，满则返回false，否则计算
			计算：
				nextSeqNum = this->nextSeqNum
				nextPacketPosition = this->nextPacketPosition

				++this->nextSeqNum;
				this->nextSeqNum %= (this->maxSeqNum + 1)
				++this->nextPacketPosition;
				this->nextPacketPosition %= (this -> windowSzie + 1)
			返回true
	5.void afterRecvAckUpdateBasePositionAndSeqNum(int packet.acknum);
		该方法根据传入的收到ack的报文序号更新baseSeqNum与basePacketPosition
		a.计算packet.acknum与baseSeqNum之间的距离。
		计算:
			int disBetweenPacketSeqNumAndBaseSeqNum = 0;
			if(packet.acknum >= this->baseSeqNum){
				disBetweenPacketSeqNumAndBaseSeqNum = packet.acknum - this->baseSeqNum + 1;
			}
			else{
				disBetweenPacketSeqNumAndBaseSeqNum = this->maxSeqNum - this->baseSeqNum + packet.acknum + 2;
			}
		b.更新baseSeqNum
			this->baseSeqNum = (this->baseSeqNum + disBetweenPacketSeqNumAndBaseSeqNum) % (this->maxSeqNum + 1)
		c.更新basePacketPosition
			this->basePacketPosition = (this->basePacketPosition + disBetweenPacketSeqNumAndBaseSeqNum) % (this->windowSize + 1);
*/
Window::Window(int wSize, int maxSeqNumBits)
{
	this->windowSize = wSize;
	this->maxSeqNum = (int)pow(2, maxSeqNumBits);

	this->baseSeqNum = 0;
	this->nextSeqNum = 0;

	this->basePacketPosition = 0;
	this->nextPacketPosition = 0;
	this->packetsSentWaitingAckArray = new Packet[this->windowSize + 1];
	TDebugFormat("初始化窗口", "窗口大小:%d, 最大报文序号:%d", this->windowSize, this->maxSeqNum);
}

Window::~Window()
{
	delete[] this->packetsSentWaitingAckArray;
}

/*
int getNumOfPacketSentWaitingAck();
	该方法返回一个整形值表示当前数组已经存储的分组数量，计算的方法为
	对于nextPacketPosition >= basePacketPosition
		return basePacketPosition - nextPacketPosition
	对于nextPacketPosition < basePacketPosition
		return windowSize - basePacketPosition + nextPacketPosition
*/
 int Window::getNumOfPacketSentWaitingAck()
{
	if (this->nextPacketPosition >= this->basePacketPosition) {
		TDebugFormat("", "当前存储已发送但等待接收ack的分组有：%d个", this->nextPacketPosition - this->basePacketPosition);
		return this->nextPacketPosition - this->basePacketPosition;
	}
	TDebugFormat("", "当前存储已发送但等待接收ack的分组有：%d个", 
		this->windowSize - this->basePacketPosition + this->nextPacketPosition);
	return this->windowSize - this->basePacketPosition + this->nextPacketPosition;
}

 /*
 bool isPacketSentWaitingAckArrayNone()
	 该方法返回一个bool值，表示数组是否为空
	 根据getNumOfPacketSentWaitingAck的返回值判断，为0时返回true, 否则false
	 数组为空，返回true
	 数组不为空，返回false
*/
 bool Window::isPacketSentWaitingAckArrayNone()
{
	if (this->getNumOfPacketSentWaitingAck() == 0) {
		return true;
	}
	return false;
}

 /*
 bool isPacketSentWaitingAckArrayFull()
	该方法返回一个bool值，表示数组是否已满
	根据getNumOfPacketSentWaitingAck的返回值判断，为this->windowSize时返回true， 否则false
 */

 bool Window::isPacketSentWaitingAckArrayFull()
{
	 if (this->getNumOfPacketSentWaitingAck() == this->windowSize) {
		 return true;
	 }
	 return false;
}

 /*
 bool getNextSeqNumAndNextPacketPosition(int & nextSeqNum, int & nextPacketPosition);
	该方法返回bool值表示申请成功与否，
	申请成功后，报文序号与存储位置由nextSeqNum与nextPacketPosition表示
		首先调用isPacketSentWaitingAckArrayFull()判断是否已满，满则返回false，否则计算
		计算：
			nextSeqNum = this->nextSeqNum
			nextPacketPosition = this->nextPacketPosition

			 ++this->nextSeqNum;
			this->nextSeqNum %= (this->maxSeqNum + 1)
			++this->nextPacketPosition;
			this->nextPacketPosition %= (this -> windowSzie + 1)
			返回true
 */

 bool Window::getNextSeqNumAndNextPacketPosition(int & nSeqNum, int & nPacketPosition)
{
	if (this->isPacketSentWaitingAckArrayFull()) {
		nSeqNum = 0;
		nPacketPosition = 0;
		TDebugFormat("已达到最大等待数目，当前等待ack数目", "%d", this->getNumOfPacketSentWaitingAck());
		return false;
	}
	nSeqNum = this->nextSeqNum;
	nPacketPosition = this->nextPacketPosition;

	++this->nextSeqNum;
	this->nextSeqNum %= (this->maxSeqNum + 1);
	++this->nextPacketPosition;
	this->nextPacketPosition %= (this->windowSize + 1);
	TDebugFormat("获取报文序号与存储位置编号", "报文序号:%d, 存储位置编号%d.", nSeqNum, nPacketPosition);

	return true;
}

 /*
 void afterRecvAckUpdateBasePositionAndSeqNum(int packet.acknum);
 该方法根据传入的收到ack的报文序号更新baseSeqNum与basePacketPosition
 a.计算packet.acknum与baseSeqNum之间的距离。
 计算:
 int disBetweenPacketSeqNumAndBaseSeqNum = 0;
 if(packet.acknum >= this->baseSeqNum){
 disBetweenPacketSeqNumAndBaseSeqNum = packet.acknum - this->baseSeqNum + 1;
 }
 else{
 disBetweenPacketSeqNumAndBaseSeqNum = this->maxSeqNum - this->baseSeqNum + packet.acknum + 2;
 }
 b.更新baseSeqNum
 this->baseSeqNum = (this->baseSeqNum + disBetweenPacketSeqNumAndBaseSeqNum) % (this->maxSeqNum + 1)
 c.更新basePacketPosition
 this->basePacketPosition = (this->basePacketPosition + disBetweenPacketSeqNumAndBaseSeqNum) % (this->windowSize + 1);

 */

 void Window::afterRecvAckUpdateBasePositionAndSeqNum(const Packet & packet)
 {
	 /*
	 计算需要确认的分组有这样一个bug，
		如果base = 5  ， next = 6 收到的recv = 3，下面这种计算方法可能会认为，距离为this->maxSeqNum - base + recv
		实际上的计算方式应该是3不响应

		为了解决这个bug，应该首先判断是否有该序号的分组正在等待
		判断的方法是：如果next >= base 则收到的recv必然有这样的关系： base <= recv < next
						如果next < base 则收到的recv必然有这样关系  base <= recv <= maxSeqNum  || 0 < recv < next
	 */
	 // a.计算需要确认的分组的数目

	 if (this->nextSeqNum >= this->baseSeqNum && this->baseSeqNum <= packet.acknum && this->nextSeqNum > packet.acknum) {
		 ;
	 }
	 else if (((this->nextSeqNum < this->baseSeqNum && ((this->baseSeqNum <= packet.acknum && this->maxSeqNum >= packet.acknum) || (this->nextSeqNum > packet.acknum && packet.acknum >= 0))))) {
		 ;
	 }
	 else {
		 TDebugFormat("收到的ack不属于等待队列，忽略", "该ack内容为%d, 当前各参数为 this->baseSeqNum = %d, this->nextSeqNum = %d", packet.acknum, this->baseSeqNum, this->nextSeqNum);
		 return;
	 }
	 int disBetweenPacketSeqNumAndBaseSeqNum = 0;
	 if (packet.acknum >= this->baseSeqNum) {
		 disBetweenPacketSeqNumAndBaseSeqNum = packet.acknum - this->baseSeqNum + 1;
	 }
	 else {
		 disBetweenPacketSeqNumAndBaseSeqNum = this->maxSeqNum - this->baseSeqNum + packet.acknum + 2;
	 }

	
	// b.更新baseSeqNum
	 ; this->baseSeqNum = (this->baseSeqNum + disBetweenPacketSeqNumAndBaseSeqNum) % (this->maxSeqNum + 1);
	// c.更新basePacketPosition
	this->basePacketPosition = (this->basePacketPosition + disBetweenPacketSeqNumAndBaseSeqNum) % (this->windowSize + 1);
	TDebugFormat(
		"收到ack后开始确认，",
		"总共确认数量:%d, 确认后各参数为: this->basePacketPosition = %d, this->nextPacketPosition = %d, this->baseSeqNUm = %d, this->nextSeqNum = %d",
		disBetweenPacketSeqNumAndBaseSeqNum, this->basePacketPosition, this->nextPacketPosition, this->baseSeqNum, this->nextSeqNum);
 }
 
 void Window::timeoutResendAllWaitingPacket()
 {
	 TDebugFormat("超时重发所有等待的分组，当前需要重发分组数量:", "%d", this->getNumOfPacketSentWaitingAck());
	 if (this->nextPacketPosition >= this->basePacketPosition) {
		 for (int i = this->basePacketPosition; i < this->nextPacketPosition; ++i) {
			 TDebugFormat("超时重发所有等待的分组，正在重发位置为:", "%d的分组", i);
			 pns->sendToNetworkLayer(RECEIVER, this->packetsSentWaitingAckArray[i]);	//调用模拟网络环境的sendToNetworkLayer，通过网络层发送到对方
		 }
	 }
	 else {
		 for (int i = this->basePacketPosition; i < this->windowSize + 1; ++i) {
			 TDebugFormat("超时重发所有等待的分组，正在重发位置为:", "%d的分组", i);
			 pns->sendToNetworkLayer(RECEIVER, this->packetsSentWaitingAckArray[i]);	//调用模拟网络环境的sendToNetworkLayer，通过网络层发送到对方
		 }
		 for (int i = 0; i < this->nextPacketPosition; ++i) {
			 TDebugFormat("超时重发所有等待的分组，正在重发位置为:", "%d的分组", i);
			 pns->sendToNetworkLayer(RECEIVER, this->packetsSentWaitingAckArray[i]);	//调用模拟网络环境的sendToNetworkLayer，通过网络层发送到对方
		 }
	 }
 }
