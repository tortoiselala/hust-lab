
#include <cmath>
#include "TDebug.h"
#include "GBNSenderWindow.h"
/*
����ά�����������ģ��
ÿ������ӵ�������������ԣ�
	1.maxSeqNum
	2.windowSize
ÿ�����ڻᶯ̬ά����������
	1.basePacketPosition
	2.nextPacketPosition
	2.baseSeqNum
	3.nextSeqNum
	ע��Ϊ�˷���ʵ�֣���ʵ��ʵ�ֵĹ����л��������һ���洢�ռ䣬�������СΪwindowSize + 1
		basePacketPosition��ʶʱ������ĵȴ�ack�ķ����������е�λ��
		nextPacketPosition��ʶ��һ������ı���λ��
		baseSeqNumΪʱ������ĵȴ�ack�ķ���ı������
		nextSeqNumΪ��һ�����������صı�����ţ���������������¹�ϵ seqnum <= maxSeqNum

	�涨��1).nextPacketPosition == basePacketPositionʱ������Ϊ��
				nextPacketPosition == basePacketPosition - 1ʱ������Ϊ��
		  2).���������Ŀ������ڴ�����Ŀ

ÿ������ӵ�����¿��Ե��õķ���
	1.int getNumOfPacketSentWaitingAck();
		�÷�������һ������ֵ��ʾ��ǰ�����Ѿ��洢�ķ�������������ķ���Ϊ
			����nextPacketPosition >= basePacketPosition
				return basePacketPosition - nextPacketPosition
			����nextPacketPosition < basePacketPosition
				return windowSize - basePacketPosition + nextPacketPosition
	2.bool isPacketSentWaitingAckArrayNone()
		�÷�������һ��boolֵ����ʾ�����Ƿ�Ϊ��
			����getNumOfPacketSentWaitingAck�ķ���ֵ�жϣ�Ϊ0ʱ����true, ����false
	3.bool isPacketSentWaitingAckArrayFull()
		�÷�������һ��boolֵ����ʾ�����Ƿ�����
			����getNumOfPacketSentWaitingAck�ķ���ֵ�жϣ�Ϊ1ʱ����true�� ����false
	4.bool getNextSeqNumAndNextPacketPosition(int & nextSeqNum, int & nextPacketPosition);
		�÷�������boolֵ��ʾ����ɹ����
			����ɹ��󣬱��������洢λ����nextSeqNum��nextPacketPosition��ʾ
			���ȵ���isPacketSentWaitingAckArrayFull()�ж��Ƿ����������򷵻�false���������
			���㣺
				nextSeqNum = this->nextSeqNum
				nextPacketPosition = this->nextPacketPosition

				++this->nextSeqNum;
				this->nextSeqNum %= (this->maxSeqNum + 1)
				++this->nextPacketPosition;
				this->nextPacketPosition %= (this -> windowSzie + 1)
			����true
	5.void afterRecvAckUpdateBasePositionAndSeqNum(int packet.acknum);
		�÷������ݴ�����յ�ack�ı�����Ÿ���baseSeqNum��basePacketPosition
		a.����packet.acknum��baseSeqNum֮��ľ��롣
		����:
			int disBetweenPacketSeqNumAndBaseSeqNum = 0;
			if(packet.acknum >= this->baseSeqNum){
				disBetweenPacketSeqNumAndBaseSeqNum = packet.acknum - this->baseSeqNum + 1;
			}
			else{
				disBetweenPacketSeqNumAndBaseSeqNum = this->maxSeqNum - this->baseSeqNum + packet.acknum + 2;
			}
		b.����baseSeqNum
			this->baseSeqNum = (this->baseSeqNum + disBetweenPacketSeqNumAndBaseSeqNum) % (this->maxSeqNum + 1)
		c.����basePacketPosition
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
	TDebugFormat("��ʼ������", "���ڴ�С:%d, ��������:%d", this->windowSize, this->maxSeqNum);
}

Window::~Window()
{
	delete[] this->packetsSentWaitingAckArray;
}

/*
int getNumOfPacketSentWaitingAck();
	�÷�������һ������ֵ��ʾ��ǰ�����Ѿ��洢�ķ�������������ķ���Ϊ
	����nextPacketPosition >= basePacketPosition
		return basePacketPosition - nextPacketPosition
	����nextPacketPosition < basePacketPosition
		return windowSize - basePacketPosition + nextPacketPosition
*/
 int Window::getNumOfPacketSentWaitingAck()
{
	if (this->nextPacketPosition >= this->basePacketPosition) {
		TDebugFormat("", "��ǰ�洢�ѷ��͵��ȴ�����ack�ķ����У�%d��", this->nextPacketPosition - this->basePacketPosition);
		return this->nextPacketPosition - this->basePacketPosition;
	}
	TDebugFormat("", "��ǰ�洢�ѷ��͵��ȴ�����ack�ķ����У�%d��", 
		this->windowSize - this->basePacketPosition + this->nextPacketPosition);
	return this->windowSize - this->basePacketPosition + this->nextPacketPosition;
}

 /*
 bool isPacketSentWaitingAckArrayNone()
	 �÷�������һ��boolֵ����ʾ�����Ƿ�Ϊ��
	 ����getNumOfPacketSentWaitingAck�ķ���ֵ�жϣ�Ϊ0ʱ����true, ����false
	 ����Ϊ�գ�����true
	 ���鲻Ϊ�գ�����false
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
	�÷�������һ��boolֵ����ʾ�����Ƿ�����
	����getNumOfPacketSentWaitingAck�ķ���ֵ�жϣ�Ϊthis->windowSizeʱ����true�� ����false
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
	�÷�������boolֵ��ʾ����ɹ����
	����ɹ��󣬱��������洢λ����nextSeqNum��nextPacketPosition��ʾ
		���ȵ���isPacketSentWaitingAckArrayFull()�ж��Ƿ����������򷵻�false���������
		���㣺
			nextSeqNum = this->nextSeqNum
			nextPacketPosition = this->nextPacketPosition

			 ++this->nextSeqNum;
			this->nextSeqNum %= (this->maxSeqNum + 1)
			++this->nextPacketPosition;
			this->nextPacketPosition %= (this -> windowSzie + 1)
			����true
 */

 bool Window::getNextSeqNumAndNextPacketPosition(int & nSeqNum, int & nPacketPosition)
{
	if (this->isPacketSentWaitingAckArrayFull()) {
		nSeqNum = 0;
		nPacketPosition = 0;
		TDebugFormat("�Ѵﵽ���ȴ���Ŀ����ǰ�ȴ�ack��Ŀ", "%d", this->getNumOfPacketSentWaitingAck());
		return false;
	}
	nSeqNum = this->nextSeqNum;
	nPacketPosition = this->nextPacketPosition;

	++this->nextSeqNum;
	this->nextSeqNum %= (this->maxSeqNum + 1);
	++this->nextPacketPosition;
	this->nextPacketPosition %= (this->windowSize + 1);
	TDebugFormat("��ȡ���������洢λ�ñ��", "�������:%d, �洢λ�ñ��%d.", nSeqNum, nPacketPosition);

	return true;
}

 /*
 void afterRecvAckUpdateBasePositionAndSeqNum(int packet.acknum);
 �÷������ݴ�����յ�ack�ı�����Ÿ���baseSeqNum��basePacketPosition
 a.����packet.acknum��baseSeqNum֮��ľ��롣
 ����:
 int disBetweenPacketSeqNumAndBaseSeqNum = 0;
 if(packet.acknum >= this->baseSeqNum){
 disBetweenPacketSeqNumAndBaseSeqNum = packet.acknum - this->baseSeqNum + 1;
 }
 else{
 disBetweenPacketSeqNumAndBaseSeqNum = this->maxSeqNum - this->baseSeqNum + packet.acknum + 2;
 }
 b.����baseSeqNum
 this->baseSeqNum = (this->baseSeqNum + disBetweenPacketSeqNumAndBaseSeqNum) % (this->maxSeqNum + 1)
 c.����basePacketPosition
 this->basePacketPosition = (this->basePacketPosition + disBetweenPacketSeqNumAndBaseSeqNum) % (this->windowSize + 1);

 */

 void Window::afterRecvAckUpdateBasePositionAndSeqNum(const Packet & packet)
 {
	 /*
	 ������Ҫȷ�ϵķ���������һ��bug��
		���base = 5  �� next = 6 �յ���recv = 3���������ּ��㷽�����ܻ���Ϊ������Ϊthis->maxSeqNum - base + recv
		ʵ���ϵļ��㷽ʽӦ����3����Ӧ

		Ϊ�˽�����bug��Ӧ�������ж��Ƿ��и���ŵķ������ڵȴ�
		�жϵķ����ǣ����next >= base ���յ���recv��Ȼ�������Ĺ�ϵ�� base <= recv < next
						���next < base ���յ���recv��Ȼ��������ϵ  base <= recv <= maxSeqNum  || 0 < recv < next
	 */
	 // a.������Ҫȷ�ϵķ������Ŀ

	 if (this->nextSeqNum >= this->baseSeqNum && this->baseSeqNum <= packet.acknum && this->nextSeqNum > packet.acknum) {
		 ;
	 }
	 else if (((this->nextSeqNum < this->baseSeqNum && ((this->baseSeqNum <= packet.acknum && this->maxSeqNum >= packet.acknum) || (this->nextSeqNum > packet.acknum && packet.acknum >= 0))))) {
		 ;
	 }
	 else {
		 TDebugFormat("�յ���ack�����ڵȴ����У�����", "��ack����Ϊ%d, ��ǰ������Ϊ this->baseSeqNum = %d, this->nextSeqNum = %d", packet.acknum, this->baseSeqNum, this->nextSeqNum);
		 return;
	 }
	 int disBetweenPacketSeqNumAndBaseSeqNum = 0;
	 if (packet.acknum >= this->baseSeqNum) {
		 disBetweenPacketSeqNumAndBaseSeqNum = packet.acknum - this->baseSeqNum + 1;
	 }
	 else {
		 disBetweenPacketSeqNumAndBaseSeqNum = this->maxSeqNum - this->baseSeqNum + packet.acknum + 2;
	 }

	
	// b.����baseSeqNum
	 ; this->baseSeqNum = (this->baseSeqNum + disBetweenPacketSeqNumAndBaseSeqNum) % (this->maxSeqNum + 1);
	// c.����basePacketPosition
	this->basePacketPosition = (this->basePacketPosition + disBetweenPacketSeqNumAndBaseSeqNum) % (this->windowSize + 1);
	TDebugFormat(
		"�յ�ack��ʼȷ�ϣ�",
		"�ܹ�ȷ������:%d, ȷ�Ϻ������Ϊ: this->basePacketPosition = %d, this->nextPacketPosition = %d, this->baseSeqNUm = %d, this->nextSeqNum = %d",
		disBetweenPacketSeqNumAndBaseSeqNum, this->basePacketPosition, this->nextPacketPosition, this->baseSeqNum, this->nextSeqNum);
 }
 
 void Window::timeoutResendAllWaitingPacket()
 {
	 TDebugFormat("��ʱ�ط����еȴ��ķ��飬��ǰ��Ҫ�ط���������:", "%d", this->getNumOfPacketSentWaitingAck());
	 if (this->nextPacketPosition >= this->basePacketPosition) {
		 for (int i = this->basePacketPosition; i < this->nextPacketPosition; ++i) {
			 TDebugFormat("��ʱ�ط����еȴ��ķ��飬�����ط�λ��Ϊ:", "%d�ķ���", i);
			 pns->sendToNetworkLayer(RECEIVER, this->packetsSentWaitingAckArray[i]);	//����ģ�����绷����sendToNetworkLayer��ͨ������㷢�͵��Է�
		 }
	 }
	 else {
		 for (int i = this->basePacketPosition; i < this->windowSize + 1; ++i) {
			 TDebugFormat("��ʱ�ط����еȴ��ķ��飬�����ط�λ��Ϊ:", "%d�ķ���", i);
			 pns->sendToNetworkLayer(RECEIVER, this->packetsSentWaitingAckArray[i]);	//����ģ�����绷����sendToNetworkLayer��ͨ������㷢�͵��Է�
		 }
		 for (int i = 0; i < this->nextPacketPosition; ++i) {
			 TDebugFormat("��ʱ�ط����еȴ��ķ��飬�����ط�λ��Ϊ:", "%d�ķ���", i);
			 pns->sendToNetworkLayer(RECEIVER, this->packetsSentWaitingAckArray[i]);	//����ģ�����绷����sendToNetworkLayer��ͨ������㷢�͵��Է�
		 }
	 }
 }
