#ifndef TCP_RECEIVER_H
#define TCP_RECEIVER_H

#include "RdtReceiver.h"

class TCPReceiver :public RdtReceiver {
	int maxSeqNum;
	int lastRecvSeqNum;
	int expectRecvSeqNum;

public:
	void receive(Packet &packet);

public:
	TCPReceiver();
	~TCPReceiver();

};
#endif // !TCP_RECEIVER_H
