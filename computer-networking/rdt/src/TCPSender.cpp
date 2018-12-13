
#include <cmath>
#include "config.h"
#include "TDebug.h"
#include "TCPSender.h"

/*
TCPʹ�õ���ѡ��ȷ�Ϸ�����Ϊ�˷���ʵ�֣�һ���յĴ���ʵ����ӵ��һ���յķ���
���иÿյķ����ʾ��һ��Ҫ���͵ķ�����ţ������յ�ackʱ�������㼴�ɵõ���Ҫȷ�ϵķ���
*/
bool TCPSender::send(Message & message)
{
	if (this->getWaitingState() == true) {
		return false;
	}
	std::vector<bool>::iterator ackIt = this->isWaitingAck.begin();
	std::vector<Packet>::iterator packetIt = this->slideWindow.begin();
	while ((ackIt + 1) != this->isWaitingAck.end() && (packetIt + 1) != this->slideWindow.end()) {
		++ackIt;
		++packetIt;
	}
	
	(*ackIt) = true;
	(*packetIt).seqnum = this->nextSeqNum;
	(*packetIt).acknum = -1;
	memcpy((*packetIt).payload, message.data, sizeof(message.data));
	(*packetIt).checksum = pUtils->calculateCheckSum(*packetIt);

	this->nextSeqNum = (this->nextSeqNum + 1) % (this->maxSeqNum + 1);
	
	TDebugSenderSendMessage(*packetIt);
	pns->sendToNetworkLayer(RECEIVER, *packetIt);
	std::cout << "��ǰslide window.size = " << this->slideWindow.size() << std::endl;
	if (this->slideWindow.size() <= 1) {
		pns->startTimer(SENDER, Configuration::TIME_OUT, config::defaultClkSeqnum);
	}
	this->addBlackPacket();
	//������һ����������Ϣ
	
	
	printSlideWindow();
	return true;
}
/*
TCPʹ�õ���ѡ��ȷ�ϣ����Ե�ǰ�յ���ack��ʾ�����õ�����һ��������ţ�
���ԣ����ȷ�ϵĻ�����Ҫ�ȸ��ݵ�ǰ�յ���ack�ƶϳ���Ҫȷ�ϵķ������
���㷽����
	��ʵ�ֹ�����ʹ���˶���Ŀհ�packet��ʾ��һ����
*/
void TCPSender::receive(Packet & ackPkt)
{
	
	TDebugRecvivePrintPacket("���ͷ��յ�ack",ackPkt);
	//����Ƿ��з����ڵȴ�ack
	if (this->slideWindow.size() <= 1) {
		TDebugMessage("���ͷ��յ�ack�������޷������ڵȴ�������");
		return;
	}
	int checkSum = pUtils->calculateCheckSum(ackPkt);
	if (checkSum != ackPkt.checksum) {
		TDebugMessage("���ͷ��յ�ack������У��ʹ��󣬺���");
		return;
	}
	bool resendLastPacket = false;
	if (ackPkt.acknum == this->lastAck) {
		++this->lastAckCount;
		if (this->lastAckCount == 3) {
			TDebugMessage("���ͷ��յ�������ͬ��ack���ط��÷���(�����ش�)");

			pns->stopTimer(SENDER, config::defaultClkSeqnum);
			if (this->slideWindow.size() > 1) {
				pns->startTimer(SENDER, Configuration::TIME_OUT, config::defaultClkSeqnum);
			}
			resendLastPacket = true;
			this->lastAckCount = 0;

			//�յ�3��ack���ط��÷���
			std::vector<Packet>::iterator packetIt = this->slideWindow.begin();
			std::vector<bool>::iterator ackIt = this->isWaitingAck.begin();
			bool isFirst = true;

			while ((packetIt + 1) != this->slideWindow.end()) {
				if (ackPkt.acknum == ((*packetIt)).seqnum) {
					pns->sendToNetworkLayer(RECEIVER, (*packetIt));
					break;
				}
				this->baseSeqNum++;
				ackIt = this->isWaitingAck.erase(ackIt);
				packetIt = this->slideWindow.erase(packetIt);
			}
			this->lastAckCount = 0;
		}
		return;
	}
	else {
		this->lastAck = ackPkt.acknum;
		this->lastAckCount = 1;
	}
	std::vector<bool>::iterator ackIt = this->isWaitingAck.begin();
	std::vector<Packet>::iterator packetIt = this->slideWindow.begin();

	bool isFirst = true;
	//�յ�ack��������ʱ��
	TDebugMessage("�յ�ack��ֹͣ��ʱ��");

	pns->stopTimer(SENDER, config::defaultClkSeqnum);

	
	if ((*packetIt).seqnum == ackPkt.acknum) {
		;
	}
	else {
		while ((ackIt + 1) != this->isWaitingAck.end()) {
			if (ackPkt.acknum == (*(packetIt + 1)).seqnum) {
				if (isFirst) {
					TDebugMessage("ack����ȷ�ϵ�һ������");
					isFirst = false;
				}
				TDebugMessage("�ۼ�ȷ��");
				(*ackIt) = false;
				ackIt = this->isWaitingAck.erase(ackIt);
				packetIt = this->slideWindow.erase(packetIt);
				break;
			}
			else {
				isFirst = false;
				(*ackIt) = false;
				ackIt = this->isWaitingAck.erase(ackIt);
				packetIt = this->slideWindow.erase(packetIt);
			}
		}
	}
	
	std::cout << "breakpoint 1" << std::endl;
	if (this->slideWindow.size() > 1) {
		TDebugMessage("������ʱ��");
		pns->startTimer(SENDER, Configuration::TIME_OUT, config::defaultClkSeqnum);
	}
	//ensureCapacity();
	printSlideWindow();
	return;
}

void TCPSender::timeoutHandler(int seqNum)
{
	TDebugFormat("���ͷ���ʱ���ط�����", "��ţ�%d    ������ʱ��", (*(this->slideWindow.begin())).seqnum);
	pns->stopTimer(SENDER, config::defaultClkSeqnum);
	pns->startTimer(SENDER, Configuration::TIME_OUT, config::defaultClkSeqnum);

	this->lastAckCount = 0;
	pns->sendToNetworkLayer(RECEIVER, *(this->slideWindow.begin()));
}

bool TCPSender::getWaitingState()
{
	if (this->slideWindow.size() >= this->windowSize + 1) {
		return true;
	}
	return false;
}

TCPSender::TCPSender()
{
	this->baseSeqNum = 0;
	this->nextSeqNum = 0;
	this->lastAck = -1;
	this->lastAckCount = 0;
	this->windowSize = config::defaultWindowSize;
	this->maxSeqNum = std::pow(2, config::defaultSeqNumBits);
	this->addBlackPacket();
}

TCPSender::~TCPSender()
{

}
void setPlaceHolder(Packet & packet)
{
	for (int i = 0; i < Configuration::PAYLOAD_SIZE; i++) {
		packet.payload[i] = '.';
	}
}