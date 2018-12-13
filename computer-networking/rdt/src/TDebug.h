#ifndef T_DEBUG_H
#define T_DEBUG_H
#include<iostream>
#include"Global.h"
#define T_DEBUG
#ifdef T_DEBUG
#define TDebugRecviverNotCorruptChecknumMessage(seqnum)                     (std::cout << "[debug] 接收方收到不正确分组，错误原因：校验和错误， 报文号：" << seqnum << std::endl)
#define TDebugRecviverNotCorruptSeqnumMessage(expectSeqnum, currentSeqnum)  (std::cout << "[debug] 接收方收到不正确分组，错误原因：报文号错误， 期望报文号：" << expectSeqnum << "收到的报文号：" << currentSeqnum << std::endl)
#define TDebugRecviverSendAckPacket(packet)                                 (std::cout << "[debug] 接收方发送确认报文，分组信息：" << packet.acknum << std::endl)
#define TDebugRecviverRecvCorruptMessage(packet)                            (std::cout << "[debug] 接收方接收到正确分组，分组信息：", pUtils->printPacket("", packet), std::cout << std::endl)

#define TDebugRecvivePrintPacket(message, packet)                           (std::cout << "[debug] " << message, pUtils->printPacket("", packet), std::cout << std::endl)

#define TDebugSenderSendMessage(packet)                                     (std::cout << "[debug] 发送方发送分组，", pUtils->printPacket("分组信息：", packet), std::cout << std::endl)
#define TDebugSenderRecvMessage(packet)                                     (std::cout << "[debug] 发送方接收确认分组，确认序号：" << packet.seqnum << std::endl)
#define TDebugSenderMessage(message)                                        (std::cout << "[debug] " << message << std::endl)

#define TDebugMessage(message)            (std::cout << "[debug] infomation: " << message << std::endl)
#define TDebugLog(log)                    (std::cout << "[debug] infomation: " << log  << " at: " << __FILE__ << " function: " << __FUNCTION__<< " line: " << __LINE__ << std::endl)
#define TDebugFormat(info, format, ...)   (std::cout << "[debug] infomation: " << info << " ", printf(format, __VA_ARGS__), std::cout << std::endl)
#define TDebugPrintPacket(packet)         (pUtils->printPacket("[debug] 分组信息", packet))
#define TDebugSenderPrintPacket(packet)   (pUtils->printPacket("[debug] 发送方分组， 分组信息：", packet))
#define TDebugRecviverPrintPacket(packet) (pUtils->printPacket("[debug] 接收方分组， 分组信息：", packet))

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
