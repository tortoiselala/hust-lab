#pragma once
#include"server.h"

const u_short server::server::IPv4 = AF_INET;
const u_short server::server::IPv6 = AF_INET6;

const int server::server::socket_stream = SOCK_STREAM;
const int server::server::socket_dgram = SOCK_DGRAM;

server::server::server(void){}

server::server::~server(void){}

int server::server::get_port()
{
	return this->port;
}

int server::server::get_address_family()
{
	return this->address_family;
}

void server::server::set_port(int port)
{
	this->port = port;
}

void server::server::set_address_family(int address_family)
{
	this->address_family = address_family;
}

string server::server::get_ip()
{
	return string(this->ip);
}

string server::server::get_host_name()
{
	return string(this->host_name);
}

string server::server::get_virtual_path()
{
	return this->virtual_path;
}

void server::server::set_ip(string ip)
{
	this->ip = string(ip);
}

void server::server::set_host_name(string host_name)
{
	this->host_name = string(host_name);
}

int server::server::get_max_connection()
{
	return this->max_connection;
}

int server::server::get_socket_type()
{
	return this->socket_type;
}

int server::server::get_socket_protocol()
{
	return this->socket_protocol;
}

void server::server::set_max_connection(int max_connection)
{
	this->max_connection = max_connection;
}

void server::server::set_socket_type(int socket_type)
{
	this->socket_type = socket_type;
}

void server::server::set_socket_protocol(int socket_protocol)
{
	this->socket_protocol = socket_protocol;
}

void server::server::set_virtual_path(string virtual_path)
{
	this->virtual_path = virtual_path;
}

void server::server::init_sock()
{
	WORD w_version_requested = MAKEWORD(2, 2);
	int i_result = WSAStartup(w_version_requested, &(this->WSAData));
	if (i_result != 0) {
		char buff[100];
		sprintf(buff, "WSAStartup failed: %d", i_result);
		throw string(buff);
	}
	sockaddr_in sockaddr_in_data;
	unsigned char buf[sizeof(struct in6_addr)];
	i_result = inet_pton(this->get_address_family(), this->get_ip().c_str(), buf);
	if (i_result < 0) {
		char buff[100];
		sprintf(buff, "Not in presentation format: %d", i_result);
		throw string(buff);
	}
	else if (i_result == 0) {
		perror("inet_pton");
	}
	else {
		sockaddr_in_data.sin_addr.S_un.S_addr = inet_addr(this->get_ip().c_str());
		sockaddr_in_data.sin_family = this->get_address_family();
		sockaddr_in_data.sin_port = htons(this->get_port());
	}

	this->server_socket = socket(sockaddr_in_data.sin_family, this->get_socket_type(), this->get_socket_protocol());
	if (this->server_socket == INVALID_SOCKET) {
		char buff[100];
		sprintf(buff, "socket create failed, wrong code from WSAGetLastError(): %d\n", WSAGetLastError());
		closesocket(this->server_socket);
		WSACleanup();
		throw string(buff);
	}
	/*
	bind
	*/
	i_result = ::bind(this->server_socket, (LPSOCKADDR)&sockaddr_in_data, sizeof(sockaddr_in_data));
	if (i_result == SOCKET_ERROR) {
		char buff[100];
		sprintf(buff, "socket bind failed, wrong code from WSAGetLastError(): %d\n", WSAGetLastError());
		closesocket(this->server_socket);
		WSACleanup();
		throw string(buff);
	}
}

void server::server::set_listener()
{
	if (listen(this->server_socket, this->get_max_connection()) == SOCKET_ERROR) {
		char buff[100];
		sprintf(buff, "socket listen failed, wrong code from WSAGetLastError(): %d\n", WSAGetLastError());
		closesocket(this->server_socket);
		WSACleanup();
		throw string(buff);
	}

	while (true) {
		SOCKET client_connect = INVALID_SOCKET;
		client_connect = accept(this->server_socket, NULL, NULL);
		if (client_connect == INVALID_SOCKET) {
			return;
		}
		p_thread_data data = (p_thread_data)malloc(sizeof(thread_data));
		data->s = client_connect;
		data->thread_id = thread_id++;
		data->virtual_path = new string(this->virtual_path);

		thread thread_res(
			start,
			data
		);
		thread_res.detach();
	}
}

void server::server::stop()
{
	closesocket(this->server_socket);
	WSACleanup();
}

void server::_stop(server * s)
{
	while (1) {
		string cmd;
		std::cout << "[info ] Server is running, If you want to stop server, please enter [stop]:";
		std::cin >> cmd;
		if (!strcmp(cmd.c_str(), string("stop").c_str())) {
			s->stop();
			std::cout << "server has been stopped!" << std::endl;
			return;
		}
		else {
			std::cout << "wrong command! Try again" << std::endl;
		}
	}
}
