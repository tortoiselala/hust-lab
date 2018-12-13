#include "SR.h"
#include "stdafx.h"
#include "Global.h"
#include "RdtSender.h"
#include "RdtReceiver.h"
#include "SRRdtSender.h"
#include "SRRdtRecviver.h"


void run_SR()
{
	RdtSender *ps = new SRRdtSender();
	RdtReceiver *pr = new SRRdtRecviver();

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
