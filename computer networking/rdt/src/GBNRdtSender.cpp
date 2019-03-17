#include "config.h"
#include "TDebug.h"
#include "GBNRdtSender.h"

GBNRdtSender::GBNRdtSender():GBNRdtSender(config::defaultWindowSize, config::defaultSeqNumBits){}

GBNRdtSender::GBNRdtSender(int windowSize, int seqNumBits):window(windowSize, seqNumBits){}

GBNRdtSender::~GBNRdtSender(){}

bool GBNRdtSender::send(Message & message)
{
	int seqNum = 0;
	int position = 0;
	if (this->window.isPacketSentWaitingAckArrayNone() == true) {
		TDebugSenderMessage("发送方无正在等待ack的分组，启动计时器");
		pns->startTimer(SENDER, Configuration::TIME_OUT, 0);			//启动发送方定时器
	}
	if (this->window.getNextSeqNumAndNextPacketPosition(seqNum, position) == false) {
		return false;
	}
	

	this->window.packetsSentWaitingAckArray[position].acknum = -1;
	this->window.packetsSentWaitingAckArray[position].seqnum = seqNum;
	this->window.packetsSentWaitingAckArray[position].checksum = 0;
	memcpy(this->window.packetsSentWaitingAckArray[position].payload, message.data, sizeof(message.data));
	this->window.packetsSentWaitingAckArray[position].checksum = pUtils->calculateCheckSum(this->window.packetsSentWaitingAckArray[position]);
	TDebugSenderPrintPacket(this->window.packetsSentWaitingAckArray[position]);
	pns->sendToNetworkLayer(RECEIVER, window.packetsSentWaitingAckArray[position]);	//调用模拟网络环境的sendToNetworkLayer，通过网络层发送到对方
	GBNPrintWindow();
	return true;
}

void GBNRdtSender::receive(Packet & ackPkt)
{
	TDebugSenderMessage("发送方收到一个ack， 内容为：");
	TDebugPrintPacket(ackPkt);
	if (this->window.isPacketSentWaitingAckArrayNone() == true) {
		TDebugSenderMessage("发送方无正在等待ack的分组，但是收到了一个ack，忽略");
		return;
	}
	TDebugSenderMessage("发送方因为收到ack，停止计时器");
	pns->stopTimer(SENDER, 0);
	this->window.afterRecvAckUpdateBasePositionAndSeqNum(ackPkt);
	if (this->window.isPacketSentWaitingAckArrayNone() == false) {
		TDebugSenderMessage("发送方依然有等待ack的分组，启动定时器");
		pns->startTimer(SENDER, Configuration::TIME_OUT, 0);			//启动发送方定时器
	}
	GBNPrintWindow();

}

void GBNRdtSender::timeoutHandler(int seqNum)
{
	TDebugSenderMessage("发送方因为超时，停止计时器");
	pns->stopTimer(SENDER, 0);										//首先关闭定时器
	if (this->window.isPacketSentWaitingAckArrayNone() == false) {
		TDebugSenderMessage("发送方因为超时且依然有等待ack的分组，重启定时器");
		pns->startTimer(SENDER, Configuration::TIME_OUT, 0);			//重新启动发送方定时器
	}
	
	this->window.timeoutResendAllWaitingPacket();
	
}

bool GBNRdtSender::getWaitingState()
{
	return this->window.isPacketSentWaitingAckArrayFull();
}

