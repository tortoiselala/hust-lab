#ifndef GBN_RDT_SENDER_H
#define GBN_RDT_SENDER_H

#include "TDebug.h"
#include "RdtSender.h"
#include "GBNSenderWindow.h"

class GBNRdtSender : public RdtSender {
private:
	void GBNPrintWindow() {

		std::cout << "..........................GBN sender window.........................." << std::endl;
		if (this->window.basePacketPosition <= this->window.nextPacketPosition) {
			for (int i = this->window.basePacketPosition; i < this->window.nextPacketPosition; ++i) {
				TDebugPrintPacket(this->window.packetsSentWaitingAckArray[i]);
			}
		}
		else {
			for (int i = this->window.basePacketPosition; i < this->window.windowSize + 1; ++i) {
				TDebugPrintPacket(this->window.packetsSentWaitingAckArray[i]);
			}
			for (int i = 0; i < this->window.nextPacketPosition; ++i) {
				TDebugPrintPacket(this->window.packetsSentWaitingAckArray[i]);
			}
		}
		std::cout << "..........................GBN sender window end......................" << std::endl;
	}
public:
	Window window;

public:
	GBNRdtSender();
	GBNRdtSender(int seqNumBits, int windowSize);
	~GBNRdtSender();

	virtual bool send(Message &message);
	virtual void receive(Packet &ackPkt);
	virtual void timeoutHandler(int seqNum);
	virtual bool getWaitingState();
	
};


#endif // !GBN_RDT_SENDER_H
