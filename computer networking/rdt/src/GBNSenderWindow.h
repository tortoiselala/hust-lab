#ifndef GBN_SENDER_WINDOW_H
#define GBN_SENDER_WINDOW_H

#include <iostream>
#include "DataStructure.h"
class Window {
private:
	// max sequence num
	int maxSeqNum;
	
public:
	// size of slide window
	int windowSize;
	//base sequence num
	int baseSeqNum;
	// next seq num
	//if there is no packet sent waiting ack, nextSeqNum == baseSeqnum
	int nextSeqNum;
	//the position of earliest packet sent waiting ack
	int basePacketPosition;
	//next packet position
	int nextPacketPosition;
	// packet sent waiting ack array
	Packet *packetsSentWaitingAckArray;
public:
	Window(int wSize, int maxSeqNumBits);
	~Window();

	virtual int getNumOfPacketSentWaitingAck();
	virtual bool isPacketSentWaitingAckArrayNone();
	virtual bool isPacketSentWaitingAckArrayFull();
	virtual bool getNextSeqNumAndNextPacketPosition(int & nextSeqNum, int & nextPacketPosition);
	virtual void afterRecvAckUpdateBasePositionAndSeqNum(const Packet & ackPkt);
	virtual void timeoutResendAllWaitingPacket();
};

#endif // !GBN_SENDER_WINDOW_H
