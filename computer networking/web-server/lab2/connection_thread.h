#pragma once

#define _CRT_SECURE_NO_WARNINGS
#define _WINSOCK_DEPRECATED_NO_WARNINGS

using namespace std;

#ifndef CONNECTION_THREAD
#define CONNECTION_THREAD

#include<iostream>
#include<WinSock2.h>
#include"http_head_parser.h"
#include"server.h"
#define MAX_BYTE_SEBD_PER_TIME 1024

/*
线程传参结构体
*/
typedef struct THREAD_DATA {
	string *virtual_path;
	int thread_id;
	SOCKET s;
}thread_data, *p_thread_data;

static const string server_name = string("Server: tortoiserlala\r\n");

void start(p_thread_data data);

#endif // !CONNECTION_THREAD
