#ifndef GBN_RDT_RECVIVER_H
#define GBN_RDT_RECVIVER_H

#include "RdtReceiver.h"
#include "DataStructure.h"
class GBNRdtRecviver : public RdtReceiver {
	int maxSeqBits;
	int maxSeqNum;

	int lastSequenceNumberEcvs;
	int expectSequenceNumberEcvs;
public:
	GBNRdtRecviver();
	virtual ~GBNRdtRecviver();
	virtual void receive(Packet &packet);
};
#endif // !GBN_RDT_RECVIVER_H
