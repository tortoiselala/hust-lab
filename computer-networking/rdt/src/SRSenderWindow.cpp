#include <cmath>
#include "config.h"
#include "TDebug.h"
#include "SRSenderWindow.h"

SRSenderWindow::SRSenderWindow()
	:senderWindow({ config::defaultWindowSize}),
	maxSeqNum(std::pow(2, config::defaultSeqNumBits)),
	baseSeqNum(0),
	nextSeqNum(0)
{}

SRSenderWindow::~SRSenderWindow(){}


int SRSenderWindow::getSeqNum()
{
	int seqnum = this->nextSeqNum;
	this->nextSeqNum = (this->nextSeqNum + 1) % (this->maxSeqNum + 1);
	return seqnum;
}

bool SRSenderWindow::isWindowFull()
{
	if (this->senderWindow.slideWindow.size() >= this->senderWindow.windowSize) {
		return true;
	}
	return false;
}

int SRSenderWindow::getNumOfPacketWaitingAck()
{
	int num = 0;
	std::vector<bool>::iterator it = this->senderWindow.isWaitingAck.begin();
	while (it != this->senderWindow.isWaitingAck.end()) {
		if (*it == true) {
			++num;
		}
	}
	TDebugFormat("get num of packet waiting ack num = ", "%d", num);
	return num;
}

bool SRSenderWindow::recvAck(const Packet & packet)
{
	TDebugFormat("���շ��յ�ackpacket", "packet.acknum == %d", packet.acknum);
	std::vector<Packet>::iterator packetIt = this->senderWindow.slideWindow.begin();
	std::vector<bool>::iterator ackIt = this->senderWindow.isWaitingAck.begin();
	/*
	����Ѱ�ҷ���
	*/
	for (; packetIt != this->senderWindow.slideWindow.end() && ackIt != this->senderWindow.isWaitingAck.end();) {
		if ((*packetIt).seqnum == packet.acknum) {
			(*ackIt) = false;
			pns->stopTimer(SENDER, packet.acknum);
			TDebugFormat("�յ�ack", "���:%d", packet.acknum);
			break;
		}
		++packetIt;
		++ackIt;
	}
	packetIt = this->senderWindow.slideWindow.begin();
	ackIt = this->senderWindow.isWaitingAck.begin();

	for (; packetIt != this->senderWindow.slideWindow.end() && ackIt != this->senderWindow.isWaitingAck.end();) {
		if (*ackIt == false) {
			TDebugFormat("�Ӷ������Ƴ��Ѿ�ȷ�ϵİ�", "���:%d", (*packetIt).seqnum);
			ackIt = this->senderWindow.isWaitingAck.erase(ackIt);
			packetIt = this->senderWindow.slideWindow.erase(packetIt);
			this->baseSeqNum = (this->baseSeqNum + 1) % (this->maxSeqNum + 1);
			
		}
		else {
			SRPrintSenderWindow(*this);
			return true;
		}
	}
	SRPrintSenderWindow(*this);
	return true;
}

bool SRSenderWindow::timeOutResendTargetPacket(int seqnum)
{
	SRPrintSenderWindow(*this);
	std::vector<Packet>::iterator packetIt = this->senderWindow.slideWindow.begin();
	for (; packetIt < this->senderWindow.slideWindow.end(); ++packetIt) {
		if ((*packetIt).seqnum == seqnum) {
			TDebugFormat("��ʱ", "�ط�:%d", seqnum);
			pns->stopTimer(SENDER, seqnum);
			pns->startTimer(SENDER, Configuration::TIME_OUT, seqnum);
			pns->sendToNetworkLayer(RECEIVER, *packetIt);	
			return true;
		}
	}
	TDebugMessage("��ʱ�����޷��������İ�");
	return false;
}
