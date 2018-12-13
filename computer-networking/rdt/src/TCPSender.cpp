
#include <cmath>
#include "config.h"
#include "TDebug.h"
#include "TCPSender.h"

/*
TCP使用的是选择确认方法，为了方便实现，一个空的窗口实际上拥有一个空的分组
其中该空的分组表示下一需要发送的分组序号，方便收到ack时不用推算即可得到需要确认的分组
*/
bool TCPSender::send(Message & message)
{
	if (this->getWaitingState() == true) {
		return false;
	}
	std::vector<bool>::iterator ackIt = this->isWaitingAck.begin();
	std::vector<Packet>::iterator packetIt = this->slideWindow.begin();
	while ((ackIt + 1) != this->isWaitingAck.end() && (packetIt + 1) != this->slideWindow.end()) {
		++ackIt;
		++packetIt;
	}
	
	(*ackIt) = true;
	(*packetIt).seqnum = this->nextSeqNum;
	(*packetIt).acknum = -1;
	memcpy((*packetIt).payload, message.data, sizeof(message.data));
	(*packetIt).checksum = pUtils->calculateCheckSum(*packetIt);

	this->nextSeqNum = (this->nextSeqNum + 1) % (this->maxSeqNum + 1);
	
	TDebugSenderSendMessage(*packetIt);
	pns->sendToNetworkLayer(RECEIVER, *packetIt);
	std::cout << "当前slide window.size = " << this->slideWindow.size() << std::endl;
	if (this->slideWindow.size() <= 1) {
		pns->startTimer(SENDER, Configuration::TIME_OUT, config::defaultClkSeqnum);
	}
	this->addBlackPacket();
	//计算下一分组所用信息
	
	
	printSlideWindow();
	return true;
}
/*
TCP使用的是选择确认，所以当前收到的ack表示期望得到的下一个包的序号，
所以，如果确认的话，需要先根据当前收到的ack推断出需要确认的分组序号
推算方法：
	在实现过程中使用了额外的空白packet表示下一分组
*/
void TCPSender::receive(Packet & ackPkt)
{
	
	TDebugRecvivePrintPacket("发送方收到ack",ackPkt);
	//检查是否有分组在等待ack
	if (this->slideWindow.size() <= 1) {
		TDebugMessage("发送方收到ack，但是无分组正在等待，忽略");
		return;
	}
	int checkSum = pUtils->calculateCheckSum(ackPkt);
	if (checkSum != ackPkt.checksum) {
		TDebugMessage("发送方收到ack，但是校验和错误，忽略");
		return;
	}
	bool resendLastPacket = false;
	if (ackPkt.acknum == this->lastAck) {
		++this->lastAckCount;
		if (this->lastAckCount == 3) {
			TDebugMessage("发送方收到三个相同的ack，重发该分组(快速重传)");

			pns->stopTimer(SENDER, config::defaultClkSeqnum);
			if (this->slideWindow.size() > 1) {
				pns->startTimer(SENDER, Configuration::TIME_OUT, config::defaultClkSeqnum);
			}
			resendLastPacket = true;
			this->lastAckCount = 0;

			//收到3个ack，重发该分组
			std::vector<Packet>::iterator packetIt = this->slideWindow.begin();
			std::vector<bool>::iterator ackIt = this->isWaitingAck.begin();
			bool isFirst = true;

			while ((packetIt + 1) != this->slideWindow.end()) {
				if (ackPkt.acknum == ((*packetIt)).seqnum) {
					pns->sendToNetworkLayer(RECEIVER, (*packetIt));
					break;
				}
				this->baseSeqNum++;
				ackIt = this->isWaitingAck.erase(ackIt);
				packetIt = this->slideWindow.erase(packetIt);
			}
			this->lastAckCount = 0;
		}
		return;
	}
	else {
		this->lastAck = ackPkt.acknum;
		this->lastAckCount = 1;
	}
	std::vector<bool>::iterator ackIt = this->isWaitingAck.begin();
	std::vector<Packet>::iterator packetIt = this->slideWindow.begin();

	bool isFirst = true;
	//收到ack，重启定时器
	TDebugMessage("收到ack，停止定时器");

	pns->stopTimer(SENDER, config::defaultClkSeqnum);

	
	if ((*packetIt).seqnum == ackPkt.acknum) {
		;
	}
	else {
		while ((ackIt + 1) != this->isWaitingAck.end()) {
			if (ackPkt.acknum == (*(packetIt + 1)).seqnum) {
				if (isFirst) {
					TDebugMessage("ack用于确认第一个分组");
					isFirst = false;
				}
				TDebugMessage("累计确认");
				(*ackIt) = false;
				ackIt = this->isWaitingAck.erase(ackIt);
				packetIt = this->slideWindow.erase(packetIt);
				break;
			}
			else {
				isFirst = false;
				(*ackIt) = false;
				ackIt = this->isWaitingAck.erase(ackIt);
				packetIt = this->slideWindow.erase(packetIt);
			}
		}
	}
	
	std::cout << "breakpoint 1" << std::endl;
	if (this->slideWindow.size() > 1) {
		TDebugMessage("启动定时器");
		pns->startTimer(SENDER, Configuration::TIME_OUT, config::defaultClkSeqnum);
	}
	//ensureCapacity();
	printSlideWindow();
	return;
}

void TCPSender::timeoutHandler(int seqNum)
{
	TDebugFormat("发送方超时，重发报文", "序号：%d    重启定时器", (*(this->slideWindow.begin())).seqnum);
	pns->stopTimer(SENDER, config::defaultClkSeqnum);
	pns->startTimer(SENDER, Configuration::TIME_OUT, config::defaultClkSeqnum);

	this->lastAckCount = 0;
	pns->sendToNetworkLayer(RECEIVER, *(this->slideWindow.begin()));
}

bool TCPSender::getWaitingState()
{
	if (this->slideWindow.size() >= this->windowSize + 1) {
		return true;
	}
	return false;
}

TCPSender::TCPSender()
{
	this->baseSeqNum = 0;
	this->nextSeqNum = 0;
	this->lastAck = -1;
	this->lastAckCount = 0;
	this->windowSize = config::defaultWindowSize;
	this->maxSeqNum = std::pow(2, config::defaultSeqNumBits);
	this->addBlackPacket();
}

TCPSender::~TCPSender()
{

}
void setPlaceHolder(Packet & packet)
{
	for (int i = 0; i < Configuration::PAYLOAD_SIZE; i++) {
		packet.payload[i] = '.';
	}
}