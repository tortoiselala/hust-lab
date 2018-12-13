#ifndef SR_RECVIVER_WINDOW_H
#define SR_RECVIVER_WINDOW_H

#include <vector>
#include "Global.h"
#include <iostream>
#include "DataStructure.h"


class SRRecviverWindow {
	typedef struct SlideWindow {
		int windowSize;
		std::vector<bool> recvived;
		std::vector<Packet> slideWindow;
	}window;
public:
	int maxSeqNum;
	int baseSeqNum;
	int lastSeqNum;
	int nextSeqNum;
	
	window recviverWindow;

public:
	SRRecviverWindow();
	~SRRecviverWindow();

	virtual bool recvPacket(const Packet & packet);
private:
	//寻找分组，并更改接收状态
	bool findPacket(const Packet & packet) {
		std::vector<bool>::iterator ackIt = this->recviverWindow.recvived.begin();
		std::vector<Packet>::iterator packetIt = this->recviverWindow.slideWindow.begin();
		std::cout << "breakpoint in findPacket 1" << std::endl;
		for (; ackIt != this->recviverWindow.recvived.end() && packetIt != this->recviverWindow.slideWindow.end();) {
			std::cout << "breakpoint in findPacket 2" << std::endl;
			if ((*packetIt).seqnum == packet.seqnum) {
				memcpy((*packetIt).payload, packet.payload, Configuration::PAYLOAD_SIZE);
				(*ackIt) = true;
				SRPrintSenderWindow(*this);
				std::cout << "breakpoint in findPacket 3" << std::endl;
				return true;
			}
			++ackIt;
			++packetIt;
			std::cout << "breakpoint in findPacket 4" << std::endl;
		}
		std::cout << "breakpoint in findPacket 5" << std::endl;
		return false;
	}
	bool isValueInInterval(int left, int right, int seqnum) {
		if (left < right && left <= seqnum && seqnum < right) {
			return true;
		}
		else if (right < left && ((left <=seqnum && seqnum <= this->maxSeqNum) || (0 <= seqnum && seqnum < right))) {
			return true;
		}
		return false;
	}
	void SRPrintSenderWindow(SRRecviverWindow & window) {
		std::cout << "\n\n.........................recviver window.........................\n";
		std::vector<Packet>::iterator packetIt = window.recviverWindow.slideWindow.begin();
		std::vector<bool>::iterator ackIt = window.recviverWindow.recvived.begin();
		while (packetIt != window.recviverWindow.slideWindow.end()) {
			std::cout << "..";
			std::cout << "status  " << ((*ackIt) == true ? "true" : "false");
			pUtils->printPacket("", *packetIt);

			++packetIt;
			++ackIt;
		}
		std::cout << "\n\n.........................recviver window end......................\n";
	}
};

#endif // !SR_RECVIVER_WINDOW_H
