#include <cmath>
#include "config.h"
#include "Global.h"
#include "TDebug.h"
#include "TCPReceiver.h"

void TCPReceiver::receive(Packet & packet)
{
	//compute cheacksum
	TDebugRecvivePrintPacket("���շ��յ����� ������Ϊ��", packet);
	int checkSum = pUtils->calculateCheckSum(packet);
	//if checksum is not corrupt or seqnum is not expect seq num, do nothing
	if (checkSum != packet.checksum || packet.seqnum != this->expectRecvSeqNum) {
		if (checkSum != packet.checksum) {
			printf("compare checksum %d : %d", pUtils->calculateCheckSum(packet), packet.checksum);
			TDebugFormat("�յ�����ȷ����󣬸�������ֵ", ":this->expectSeqnum = %d, this->lastSeqNum = %d ", this->expectRecvSeqNum, this->lastRecvSeqNum);
			TDebugRecviverNotCorruptChecknumMessage(packet.seqnum);
		}
		else {
			TDebugRecviverNotCorruptSeqnumMessage(this->expectRecvSeqNum, packet.seqnum);
			Packet ackPacket;
			ackPacket.seqnum = -1;
			ackPacket.acknum = this->expectRecvSeqNum;
			for (int i = 0; i < Configuration::PAYLOAD_SIZE; i++) {
				ackPacket.payload[i] = '.';
			}
			ackPacket.checksum = pUtils->calculateCheckSum(ackPacket);
			TDebugRecviverSendAckPacket(ackPacket);
			pns->sendToNetworkLayer(SENDER, ackPacket);
		}
		return;
	}
	TDebugRecviverRecvCorruptMessage(packet);
	Message msg;
	memcpy(msg.data, packet.payload, sizeof(packet.payload));
	pns->delivertoAppLayer(RECEIVER, msg);
	this->lastRecvSeqNum = packet.seqnum;
	this->expectRecvSeqNum = (this->lastRecvSeqNum + 1) % (this->maxSeqNum + 1);

	Packet ackPacket;
	ackPacket.seqnum = -1;
	ackPacket.acknum = this->expectRecvSeqNum;
	for (int i = 0; i < Configuration::PAYLOAD_SIZE; i++) {
		ackPacket.payload[i] = '.';
	}
	ackPacket.checksum = pUtils->calculateCheckSum(ackPacket);
	TDebugRecviverSendAckPacket(ackPacket);
	pns->sendToNetworkLayer(SENDER, ackPacket);
	TDebugFormat("���շ��յ���ȷ����󣬸�������ֵ����������ֵΪ", ":this->expectSeqnum = %d, this->lastSeqNum = %d ", this->expectRecvSeqNum, this->lastRecvSeqNum);

}

TCPReceiver::TCPReceiver()
{
	this->maxSeqNum = std::pow(2, config::defaultSeqNumBits);
	this->lastRecvSeqNum = -1;
	this->expectRecvSeqNum = 0;
}

TCPReceiver::~TCPReceiver()
{}
