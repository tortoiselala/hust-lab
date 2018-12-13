#include <cmath>
#include "Tool.h"
#include "stdafx.h"
#include "Global.h"
#include "TDebug.h"
#include "config.h"
#include "GBNRdtRecviver.h"

GBNRdtRecviver::GBNRdtRecviver()
{
	this->maxSeqBits = config::defaultSeqNumBits;
	this->lastSequenceNumberEcvs = -1;
	this->expectSequenceNumberEcvs = 0;
	this->maxSeqNum = pow(2, this->maxSeqBits);
}

GBNRdtRecviver::~GBNRdtRecviver()
{}

void GBNRdtRecviver::receive(Packet & packet)
{
	//compute cheacksum
	int checkSum = pUtils->calculateCheckSum(packet);
	//if checksum is not corrupt or seqnum is not expect seq num, do nothing
	if (checkSum != packet.checksum || packet.seqnum != this->expectSequenceNumberEcvs) {
		if (checkSum != packet.checksum) {
			TDebugFormat("�յ�����ȷ����󣬸�������ֵ", ":this->expectSeqnum = %d, this->lastSeqNum = %d ", this->expectSequenceNumberEcvs, this->lastSequenceNumberEcvs);
			TDebugRecviverNotCorruptChecknumMessage(packet.seqnum);
		}
		else {
			TDebugRecviverNotCorruptSeqnumMessage(this->expectSequenceNumberEcvs, packet.seqnum);
			Packet ackPacket;
			ackPacket.seqnum = -1;
			ackPacket.acknum = this->lastSequenceNumberEcvs;
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
	this->lastSequenceNumberEcvs = this->expectSequenceNumberEcvs;
	this->expectSequenceNumberEcvs = (this->expectSequenceNumberEcvs + 1) % (this->maxSeqNum + 1);

	Packet ackPacket;
	ackPacket.seqnum = -1;
	ackPacket.acknum = packet.seqnum;
	for (int i = 0; i < Configuration::PAYLOAD_SIZE; i++) {
		ackPacket.payload[i] = '.';
	}
	ackPacket.checksum = pUtils->calculateCheckSum(ackPacket);
	TDebugRecviverSendAckPacket(ackPacket);
	pns->sendToNetworkLayer(SENDER, ackPacket);
	TDebugFormat("�յ���ȷ����󣬸�������ֵ����������ֵΪ", ":this->expectSeqnum = %d, this->lastSeqNum = %d ", this->expectSequenceNumberEcvs, this->lastSequenceNumberEcvs);
}
