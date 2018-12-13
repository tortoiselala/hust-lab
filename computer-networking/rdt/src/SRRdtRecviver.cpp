#include "SRRdtRecviver.h"

SRRdtRecviver::SRRdtRecviver(){}

SRRdtRecviver::~SRRdtRecviver(){}

void SRRdtRecviver::receive(Packet & packet)
{
	this->window.recvPacket(packet);
}
