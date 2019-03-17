#include "SRRdtSender.h"
#include "Global.h"
SRRdtSender::SRRdtSender(){}

SRRdtSender::~SRRdtSender(){}

bool SRRdtSender::send(Message & message)
{
	if (this->window.isWindowFull()) {
		return false;
	}
	Packet packet;
	packet.seqnum = this->window.getSeqNum();
	packet.acknum = -1;
	packet.checksum = 0;
	memcpy(packet.payload, message.data, sizeof(message.data));
	packet.checksum = pUtils->calculateCheckSum(packet);
	this->window.senderWindow.slideWindow.push_back(packet);
	this->window.senderWindow.isWaitingAck.push_back(true);
	pns->startTimer(SENDER, Configuration::TIME_OUT, packet.seqnum);
	pns->sendToNetworkLayer(RECEIVER, packet);	//调用模拟网络环境的sendToNetworkLayer，通过网络层发送到对方
	return true;
}

void SRRdtSender::receive(Packet & ackPkt)
{
	this->window.recvAck(ackPkt);
}

void SRRdtSender::timeoutHandler(int seqNum)
{
	this->window.timeOutResendTargetPacket(seqNum);
}

bool SRRdtSender::getWaitingState()
{
	if(this->window.isWindowFull()){
		return true;
	}
	return false;
}
