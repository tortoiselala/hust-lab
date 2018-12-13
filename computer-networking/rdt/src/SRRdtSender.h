#ifndef SR_RDT_SENDER_H
#define SR_RDT_SENDER_H

#include "RdtSender.h"
#include "SRSenderWindow.h"
class SRRdtSender : public RdtSender {

	SRSenderWindow window;
public:
	SRRdtSender();
	virtual~SRRdtSender();


	virtual bool send(Message &message);
	virtual void receive(Packet &ackPkt);
	virtual void timeoutHandler(int seqNum);
	virtual bool getWaitingState();
};

#endif // !SR_RDT_SENDER_H
