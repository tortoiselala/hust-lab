#pragma once
#define _WINSOCK_DEPRECATED_NO_WARNINGS
#define _CRT_SECURE_NO_WARNINGS


#ifndef SERVER
#define SERVER

#define MAX_BYTE_SEND 1024
#define SERVER_STRING "Server: tortoiselala/0.1.0\r\n"

#include<string>
#include<iostream>
#include<WinSock2.h>
#include<Ws2tcpip.h>

#include<thread>
#include<sstream>
#include"config.h"
#include<fstream>
#include"httpHeadParser.h"


using namespace std;
#pragma comment(lib,"ws2_32.lib")
//服务器
class server {
private:
	WORD wVersionRequested;						//使用的socket版本号
	WSADATA WSASata;							//WSA资料
	SOCKET serverSocket;						//服务器socket
	
	sockaddr_in socketAdderIn;



public:
	int port;
	int high;
	int low;
	string ip;
	string virtualPath;							//虚拟路径
	bool start_pause;

	server(void);
	server(string ip, int port, string virtualPath);
	~server(void);
	virtual void initWinsock();
	virtual void setSockVersion(int high, int low);
	virtual void setVirtualPath(string path);
	virtual void setSocket();
	virtual void setSocketBind();
	virtual void setSocketAddr(string ip, int port);
	virtual void setSocketListener(int maxConnection);
	virtual void setSocketAccepter();
	virtual string responseStatus(int code);
	virtual string responseContentType(httpHeadParser  * head);
	virtual void response(SOCKET socket);
	virtual void sendData(SOCKET socket, httpHeadParser * parser);
	virtual void start();
	virtual void stop();
};

void _start(server *s);
void _stop(server * s);
void print_cmd_usage();
void print_running_usage();

#endif // !SERVER
