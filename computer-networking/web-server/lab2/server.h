#pragma once
/*
make sure sprintf\printf in vs(visual studio) work
*/
#define _CRT_SECURE_NO_WARNINGS
/*
make sure inet_addr\... in vs(visual studio) work
*/
#define _WINSOCK_DEPRECATED_NO_WARNINGS

using namespace std;

#ifndef SERVER
#define SERVER

#include<string>
#include<iostream>
#include<WinSock2.h>
#include<WS2tcpip.h>
#include"connection_thread.h"
#include<thread>
/*
	can't link, linker can't find symbol of WSAStartup and inet_addr
	problem solved:href=https://stackoverflow.com/questions/34384803/c-undefined-reference-to-wsastartup8
*/
#pragma comment(lib,"ws2_32.lib")


namespace server {
	static int thread_id = 0;

	class server {
	private:
		int port;
		int address_family;
		int max_connection;
		int socket_type;
		int socket_protocol;

		u_long block_mode;
		u_short sock_family;

		string ip;
		string host_name;
		SOCKET server_socket;
		string virtual_path;

		WSADATA WSAData;
		

	public:
		/*
		static val in class server
		*/
		static const u_short IPv4;
		static const u_short IPv6;
		/*
			stream: socket 流类型
			dgram:  socket 数据报类型
		*/
		static const int socket_stream;
		static const int socket_dgram;
		/*
		协议类型：
			IPPROTO_UDP
			IPPROTO_TCP
			AUTO   自动选择
		*/
		static const int ipproto_udp = IPPROTO_UDP;
		static const int ipproto_tcp = IPPROTO_TCP;
		static const int ipproto_auto = 0;
		/*
		constructor in class server
		*/
		server(void);
		~server(void);
		/*
		getter for private val
		*/
		virtual int get_port();
		virtual int get_address_family();
		virtual int get_max_connection();
		virtual int get_socket_type();
		virtual int get_socket_protocol();

		virtual string get_ip();
		virtual string get_host_name();
		virtual string get_virtual_path();
		/*
		setter for private val
		*/
		virtual void set_port(int port);
		virtual void set_address_family(int address_family);
		virtual void set_ip(string ip);
		virtual void set_host_name(string host_name);
		virtual void set_max_connection(int max_connection);
		virtual void set_socket_type(int socket_type);
		virtual void set_socket_protocol(int socket_protocol);
		virtual void set_virtual_path(string virtual_path);
		/*
		init socket envirtument
		*/
		virtual void init_sock();
		virtual void set_listener();
		virtual void stop();

	};

	void _stop(server * s);
}


#endif // !SERVER
