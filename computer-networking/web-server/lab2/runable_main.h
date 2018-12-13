#pragma once
#define _CRT_SECURE_NO_WARNINGS
using namespace std;


#ifndef RUNABLE_MAIN
#define RUNABLE_MAIN

#include<iostream>
#include"server.h"
#include<string>
#include"IPAdapterAddressesChecker.h"

void print_usage(
	string default_ip,
	int default_port,
	u_short default_address_family,
	string default_virtual_path,
	int default_max_connetcion,
	int default_socket_stream,
	int default_ipproto);

#endif // !RUNABLE_MAIN
