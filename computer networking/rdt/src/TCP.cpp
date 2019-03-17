#include "TCP.h"
#include "RdtSender.h"
#include "TCPSender.h"
#include "RdtReceiver.h"
#include "TCPReceiver.h"



void run_TCP()
{
	RdtSender *ps = new TCPSender();
	RdtReceiver *pr = new TCPReceiver();

	pns->init();
	pns->setRtdSender(ps);
	pns->setRtdReceiver(pr);
	pns->setInputFile("input.txt");
	pns->setOutputFile("output.txt");
	pns->start();
	delete ps;
	delete pr;
	delete pUtils;
	delete pns;

	return;
}
