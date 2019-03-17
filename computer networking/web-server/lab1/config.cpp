#include "config.h"
#include <string>
using namespace std;

config::config(void){}

config::~config(void){}

const string config::SERVERADDRESS = "127.0.0.1";	//服务器IP地址
const int config::MAXCONNECTION = 50;				//最大连接数5
const int config::BUFFERLENGTH = 256;				//缓冲区大小256字节
const int config::PORT = 5050;						//服务器端口5050
const u_long config::BLOCKMODE = 1;					//SOCKET为非阻塞模式
const u_short config::SOCKETFAMILY = AF_INET;		//地址族
const int config::SOCKETTYPE = SOCK_STREAM;			//socket数据传输类型
const int config::SOCKETPROTOCOL = IPPROTO_TCP;	//协议类型

const string config::page_404 = string("/404.html");