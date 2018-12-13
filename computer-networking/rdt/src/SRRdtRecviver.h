#ifndef SR_RDT_RECVIVER_H
#define SR_RDT_RECVIVER_H

#include "RdtReceiver.h"
#include "SRRecviverWindow.h"

class SRRdtRecviver: public RdtReceiver {
	SRRecviverWindow window;
public:
	SRRdtRecviver();
	virtual ~SRRdtRecviver();
	virtual void receive(Packet &packet);
};

#endif // !SR_RDT_RECVIVER_H
