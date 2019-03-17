#ifndef T_DEBUG_H
#define T_DEBUG_H
#include<iostream>
#include"Global.h"
#define T_DEBUG
#ifdef T_DEBUG
#define TDebugRecviverNotCorruptChecknumMessage(seqnum)                     (std::cout << "[debug] ���շ��յ�����ȷ���飬����ԭ��У��ʹ��� ���ĺţ�" << seqnum << std::endl)
#define TDebugRecviverNotCorruptSeqnumMessage(expectSeqnum, currentSeqnum)  (std::cout << "[debug] ���շ��յ�����ȷ���飬����ԭ�򣺱��ĺŴ��� �������ĺţ�" << expectSeqnum << "�յ��ı��ĺţ�" << currentSeqnum << std::endl)
#define TDebugRecviverSendAckPacket(packet)                                 (std::cout << "[debug] ���շ�����ȷ�ϱ��ģ�������Ϣ��" << packet.acknum << std::endl)
#define TDebugRecviverRecvCorruptMessage(packet)                            (std::cout << "[debug] ���շ����յ���ȷ���飬������Ϣ��", pUtils->printPacket("", packet), std::cout << std::endl)

#define TDebugRecvivePrintPacket(message, packet)                           (std::cout << "[debug] " << message, pUtils->printPacket("", packet), std::cout << std::endl)

#define TDebugSenderSendMessage(packet)                                     (std::cout << "[debug] ���ͷ����ͷ��飬", pUtils->printPacket("������Ϣ��", packet), std::cout << std::endl)
#define TDebugSenderRecvMessage(packet)                                     (std::cout << "[debug] ���ͷ�����ȷ�Ϸ��飬ȷ����ţ�" << packet.seqnum << std::endl)
#define TDebugSenderMessage(message)                                        (std::cout << "[debug] " << message << std::endl)

#define TDebugMessage(message)            (std::cout << "[debug] infomation: " << message << std::endl)
#define TDebugLog(log)                    (std::cout << "[debug] infomation: " << log  << " at: " << __FILE__ << " function: " << __FUNCTION__<< " line: " << __LINE__ << std::endl)
#define TDebugFormat(info, format, ...)   (std::cout << "[debug] infomation: " << info << " ", printf(format, __VA_ARGS__), std::cout << std::endl)
#define TDebugPrintPacket(packet)         (pUtils->printPacket("[debug] ������Ϣ", packet))
#define TDebugSenderPrintPacket(packet)   (pUtils->printPacket("[debug] ���ͷ����飬 ������Ϣ��", packet))
#define TDebugRecviverPrintPacket(packet) (pUtils->printPacket("[debug] ���շ����飬 ������Ϣ��", packet))

#define TDebugFunction (std::cout << "[debug] in function " << __FUNCTION__ << " line: " << __LINE__ << std::endl);
#else
#define TDebugRecviverNotCorruptChecknumMessage(seqnum)  
#define TDebugRecviverNotCorruptSeqnumMessage(expectSeqnum, currentSeqnum)
#define TDebugRecviverSendAckPacket(packet)
#define TDebugRecviverRecvCorruptMessage(packet)
#define TDebugRecvivePrintPacket(message, packet)
#define TDebugSenderSendMessage(packet)
#define TDebugSenderRecvMessage(packet)
#define TDebugSenderMessage(message)

#define TDebugMessage(message)
#define TDebugLog(log)
#define TDebugFormat(info, format, ...)
#define TDebugPrintPacket(packet)
#define TDebugSenderPrintPacket(packet)
#define TDebugRecviverPrintPacket(packet)

#define TDebugFunction (std::cout << "[debug] in function " << __FUNCTION__ << " line: " << __LINE__ << std::endl);
#endif // T_DEBUG

#endif // !T_DEBUG_H
