#ifndef SR_SENDER_WINDOW_H
#define SR_SENDER_WINDOW_H
#include <vector>
#include <iostream>
#include "Global.h"
#include "DataStructure.h"



class SRSenderWindow {
	typedef struct SlideWindow {
		int windowSize;
		//if current packet is waiting ack value is true, else false;
		std::vector<bool> isWaitingAck;
		//packet sent array
		std::vector<Packet> slideWindow;

		~SlideWindow() {}
	}window;
public:
	int maxSeqNum;
	int baseSeqNum;
	int nextSeqNum;

	window senderWindow;
public:
	SRSenderWindow();
	~SRSenderWindow();

	virtual int getSeqNum();
	virtual bool isWindowFull();
	virtual int getNumOfPacketWaitingAck();
	virtual bool recvAck(const Packet & packet);
	virtual bool timeOutResendTargetPacket(int seqnum);
	void SRPrintSenderWindow(SRSenderWindow & window) {

		std::cout << "\n\n.........................sender window.........................\n";
		std::vector<Packet>::iterator packetIt = window.senderWindow.slideWindow.begin();
		std::vector<bool>::iterator ackIt = window.senderWindow.isWaitingAck.begin();
		while (packetIt != window.senderWindow.slideWindow.end()) {
			std::cout << "..";
			std::cout << "status  " << ((*ackIt) == true ? "true" : "false");
			pUtils->printPacket("", *packetIt);
			
			++packetIt;
			++ackIt;
		}
		std::cout << "\n\n.........................sender window end.....................\n";
	}
};

#endif // !SR_SENDER_WINDOW_H
