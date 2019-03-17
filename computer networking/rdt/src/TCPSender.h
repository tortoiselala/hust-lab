#ifndef TCP_SENDER_H
#define TCP_SENDER_H

#include <map>
#include <vector>
#include <iostream>
#include "Global.h"
#include "RdtSender.h"

void setPlaceHolder(Packet & packet);

class TCPSender :public RdtSender {
	int maxSeqNum;
	int baseSeqNum;
	int nextSeqNum;

	int lastAck;
	int lastAckCount;

	int windowSize;
	std::vector<bool> isWaitingAck;
	std::vector<Packet> slideWindow;
public:
	virtual bool send(Message &message);
	virtual void receive(Packet &ackPkt);
	virtual void timeoutHandler(int seqNum);
	virtual bool getWaitingState();

public:
	TCPSender();
	~TCPSender();

private:
	void ensureCapacity() {
		std::vector<bool>::iterator ackIt = this->isWaitingAck.begin();
		std::vector<Packet>::iterator packetIt = this->slideWindow.begin();
		while (ackIt != this->isWaitingAck.end()) {
			if ((*ackIt) == false && (*packetIt).seqnum != this->nextSeqNum) {
				++this->baseSeqNum;
				ackIt = this->isWaitingAck.erase(ackIt);
				packetIt = this->slideWindow.erase(packetIt);
			}
			else {
				return;
			}
		}
	}
	void printSlideWindow() {
		std::vector<bool>::iterator ackIt = this->isWaitingAck.begin();
		std::vector<Packet>::iterator packetIt = this->slideWindow.begin();
		std::cout << std::endl << ".....................TCP sender slide window....................." << std::endl;
		while (ackIt != this->isWaitingAck.end() && packetIt != this->slideWindow.end()) {
			if ((*packetIt).seqnum != this->nextSeqNum) {
				std::cout << "status£º" << ((*ackIt) == true) ? true : false;
				pUtils->printPacket(" ", *packetIt);
			}
			++ackIt;
			++packetIt;
		}
		std::cout << std::endl << ".....................TCP sender slide window end................." << std::endl;
	}


	void addBlackPacket() {
		Packet packet;
		packet.seqnum = this->nextSeqNum;
		packet.acknum = -1;
		packet.checksum = 0;
		setPlaceHolder(packet);
		packet.checksum = pUtils->calculateCheckSum(packet);
		this->slideWindow.push_back(packet);
		this->isWaitingAck.push_back(false);
	}
};


#endif // !TCP_SENDER_H
