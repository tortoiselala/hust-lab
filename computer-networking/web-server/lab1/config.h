#pragma once
#include <string>
#include <winsock2.h>

using namespace std;

//保存配置信息
class config
{
public:
	static const int MAXCONNECTION;			//最大连接数
	static const int BUFFERLENGTH;			//缓冲区大小
	static const string SERVERADDRESS;		//服务器地址
	static const int PORT;					//服务器端口
	static const u_long BLOCKMODE;			//SOCKET阻塞模式
	static const u_short SOCKETFAMILY;		//地址族
	static const int SOCKETTYPE;				//socket类型
	static const int SOCKETPROTOCOL;			//协议类型

	static const string page_404;
private:
	config(void);
	~config(void);
};
