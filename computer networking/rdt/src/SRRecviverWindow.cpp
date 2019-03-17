#include <cmath>
#include "config.h"
#include "Global.h"
#include "TDebug.h"
#include "SRRecviverWindow.h"

SRRecviverWindow::SRRecviverWindow()
	:recviverWindow({config::defaultWindowSize}),
	maxSeqNum(std::pow(2, config::defaultSeqNumBits)),
	baseSeqNum(0),
	lastSeqNum(0),
	nextSeqNum(0){}

SRRecviverWindow::~SRRecviverWindow(){}

bool SRRecviverWindow::recvPacket(const Packet & packet)
{
	int checksum = pUtils->calculateCheckSum(packet);
	if (checksum != packet.checksum) {
		TDebugRecvivePrintPacket("接收方收到分组校验和错误, 分组内容", packet);
		return false;
	}
	/*
		seqnum 应该满足以下关系，属于[base, base + n + 1]或[base - n, base - 1]
	*/
	int left_a = this->baseSeqNum;
	int right_a = (this->baseSeqNum + this->recviverWindow.windowSize) % (this->maxSeqNum + 1);
	
	int left_b = this->baseSeqNum - this->recviverWindow.windowSize;
	int right_b = this->baseSeqNum;
	if (left_b < 0) {
		left_b += this->maxSeqNum;
	}
	if (right_b < 0) {
		right_b += this->maxSeqNum;
	}
	

	//判断是否属于[base, base + n]
	if (this->isValueInInterval(left_a, right_a, packet.seqnum)) {
		TDebugRecvivePrintPacket("接收方收到分组[base, base + n], 分组内容", packet);
		;
	}
	//如果属于[base - n, base - 1]则返回一个ack
	else if(this->isValueInInterval(left_b, right_b, packet.seqnum)){
		Packet ackPkt;
		ackPkt.acknum = packet.seqnum;
		ackPkt.seqnum = -1;
		ackPkt.checksum = 0;
		for (int i = 0; i < Configuration::PAYLOAD_SIZE; i++) {
			ackPkt.payload[i] = '.';
		}
		ackPkt.checksum = pUtils->calculateCheckSum(ackPkt);
		pns->sendToNetworkLayer(SENDER, ackPkt);
		TDebugRecvivePrintPacket("接收方收到分组[base - n, base - 1], 返回一个ack, 分组内容", packet);
		return true;
	}
	else {
		TDebugRecvivePrintPacket("接收方收到分组不在期望范围", packet);
		return false;
	}

	//寻找相应位置并更改接收状态
	/*
	如果是重发的包，即找到该序号，则发送ack
	*/
	std::cout << "breakpoint 0" << std::endl;
	

	if (!this->findPacket(packet)) {
		
		//如果没有找到该分组，则为新的分组，加进去，并发送ack,加的位置根据seqnum决定，中间可能会有跳跃
 		Packet tmpPacket;
		tmpPacket.acknum = -1;
		tmpPacket.seqnum = 0;
		tmpPacket.checksum = 0;
		for (int i = 0; i < Configuration::PAYLOAD_SIZE; i++) {
			tmpPacket.payload[i] = '.';
		}
		std::cout << "breakpoint 1" << std::endl;
		tmpPacket.checksum = pUtils->calculateCheckSum(tmpPacket);
		if (this->lastSeqNum <= packet.seqnum) {
			std::cout << "breakpoint 2-1" << std::endl;
			for (int i = 0; i < packet.seqnum - this->lastSeqNum - 1; ++i) {
				tmpPacket.seqnum = (this->lastSeqNum + i + 1) % (this->maxSeqNum + 1);
				this->recviverWindow.slideWindow.push_back(tmpPacket);
				this->recviverWindow.recvived.push_back(false);
			}
			std::cout << "breakpoint 2-2" << std::endl;
		}
		else {
			std::cout << "breakpoint 3-3" << std::endl;
			int i = this->maxSeqNum - this->lastSeqNum + packet.seqnum;//   this->recviverWindow.windowSize - this->baseSeqNum + packet.seqnum;
			for (int j = 0; j < i; ++j) {
				tmpPacket.seqnum = (this->lastSeqNum + j + 1) % (this->maxSeqNum + 1);
				this->recviverWindow.slideWindow.push_back(tmpPacket);
				this->recviverWindow.recvived.push_back(false);
			}
			std::cout << "breakpoint 3-2" << std::endl;
		}
		std::cout << "breakpoint 4" << std::endl;
		this->recviverWindow.slideWindow.push_back(packet);
		this->recviverWindow.recvived.push_back(true);
		this->lastSeqNum = packet.seqnum;
		this->nextSeqNum = (this->nextSeqNum + 1) % (this->maxSeqNum + 1);
		TDebugRecvivePrintPacket("接收方新增分组， 分组内容", packet);
	}
	std::cout << "breakpoint 5" << std::endl;
	Packet ackPkt;
	ackPkt.acknum = packet.seqnum;
	ackPkt.seqnum = -1;
	ackPkt.checksum = 0;
	for (int i = 0; i < Configuration::PAYLOAD_SIZE; i++) {
		ackPkt.payload[i] = '.';
	}
	ackPkt.checksum = pUtils->calculateCheckSum(ackPkt);
	pns->sendToNetworkLayer(SENDER, ackPkt);

	/*
	开始删除已经确认的包，并向上层递交，直到遇见一个没有被确认的分组
	*/
	std::vector<bool>::iterator ackIt = this->recviverWindow.recvived.begin();
	std::vector<Packet>::iterator packetIt = this->recviverWindow.slideWindow.begin();
	for (; ackIt != this->recviverWindow.recvived.end() && packetIt != this->recviverWindow.slideWindow.end();) {
		if ((*ackIt) == true) {

			Message msg;
			memcpy(msg.data, (*packetIt).payload, sizeof((*packetIt).payload));
			pns->delivertoAppLayer(RECEIVER, msg);

			packetIt = this->recviverWindow.slideWindow.erase(packetIt);
			ackIt = this->recviverWindow.recvived.erase(ackIt);
			this->baseSeqNum = (this->baseSeqNum + 1) % (this->maxSeqNum + 1);

			TDebugRecvivePrintPacket("接收方清除确认的分组， 分组内容", packet);
		}
		else {
			SRPrintSenderWindow(*this);
			return true;
		}
	}
	SRPrintSenderWindow(*this);
	return true;
}
