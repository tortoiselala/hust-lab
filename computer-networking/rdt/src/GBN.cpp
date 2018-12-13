#include "GBN.h"
#include "stdafx.h"
#include "Global.h"
#include "RdtSender.h"
#include "RdtReceiver.h"
#include "GBNRdtSender.h"
#include "GBNRdtRecviver.h"

void run_GBN() {
	RdtSender *ps = new GBNRdtSender();
	RdtReceiver *pr = new GBNRdtRecviver();

	pns->init();
	pns->setRtdSender(ps);
	pns->setRtdReceiver(pr);
	pns->setInputFile("input.txt");
	pns->setOutputFile("output.txt");
	pns->start();
	delete ps;
	delete pr;
	delete pUtils;									//Ö¸ÏòÎ¨Ò»µÄ¹¤¾ßÀàÊµÀý£¬Ö»ÔÚmainº¯Êý½áÊøÇ°delete
	delete pns;										//Ö¸ÏòÎ¨Ò»µÄÄ£ÄâÍøÂç»·¾³ÀàÊµÀý£¬Ö»ÔÚmainº¯Êý½áÊøÇ°delete

	return;
}