//#pragma once

#define _CRT_SECURE_NO_WARNINGS
#define _WINSOCK_DEPRECATED_NO_WARNINGS



#ifndef CONNECTION_THREAD
#define CONNECTION_THREAD


#include<mutex>
#include<QDebug>
#include<iostream>
#include<WinSock2.h>
#include"server.h"
#include"config.h"
#include"http_head_parser.h"



#define MAX_BYTE_SEBD_PER_TIME 1024

using namespace std;
/*
线程传参结构体
*/
typedef struct THREAD_DATA {
	string *virtual_path;
	int thread_id;
	SOCKET s;
}thread_data, *p_thread_data;

static const string server_name = string("Server: tortoiserlala\r\n");

void start_thread(p_thread_data data);

#endif // !CONNECTION_THREAD
