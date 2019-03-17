#include"server.h"

const u_short server::server::IPv4 = AF_INET;
const u_short server::server::IPv6 = AF_INET6;

const int server::server::socket_stream = SOCK_STREAM;
const int server::server::socket_dgram = SOCK_DGRAM;

server::server::server(void) {
	this->server_start_pause = false;
}

server::server::~server(void) {}

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

	while (this->server_start_pause) {
		SOCKET client_connect = INVALID_SOCKET;
		client_connect = accept(this->server_socket, NULL, NULL);
		if (client_connect == INVALID_SOCKET) {
			perror("some thing wrong with one of connections while connection\n");
			continue;
		}
		p_thread_data data = (p_thread_data)malloc(sizeof(thread_data));
		data->s = client_connect;
		data->thread_id = thread_id++;
		data->virtual_path = new string(this->virtual_path);
		thread thread_res(
			start_thread,
			data
		);
		thread_res.detach();
	}
	closesocket(this->server_socket);
	WSACleanup();
}

void server::server::_start() {
	try{
		this->init_sock();
		this->set_listener();
	}
	catch (string ex) {
		char buf[1000];
		sprintf(buf, "%s error: %s\n", config::get_local_time().c_str(), ex.c_str());

		config::lock.lock();
		qDebug() << buf;
		config::lock.unlock();
		this->server_start_pause = false;
	}
}

void server::server::init_config() {
	int socket_stream = server::server::socket_stream;
	string str_socket_stream = this->load_config(config::config::key_socket_stream, config::config::value_socket_stream_SOCKET_STREAM);
	if (str_socket_stream == config::config::value_socket_stream_SOCKET_DGRAM) {
		socket_stream = server::server::socket_stream;
	}
	int ipproto = server::server::ipproto_auto;
	string str_ipproto = this->load_config(config::config::key_ipproto, config::config::value_IPPROTO_AUTO);
	if (str_ipproto == config::config::value_IPPROTO_TCP) {
		ipproto = server::server::ipproto_tcp;
	}
	else if (str_ipproto == config::config::value_IPPROTO_UDP) {
		ipproto = server::server::ipproto_udp;
	}
	int port = atoi(this->load_config(config::config::key_port, string("port")).c_str());
	int max_connetcion = atoi(this->load_config(config::config::key_max_connection, string("10")).c_str());
	string ip = this->load_config(config::config::key_ip, string("127.0.0.1"));
	string virtual_path = this->load_config(config::config::key_port, string(""));
	string str_address_family = this->load_config(config::config::key_address_family, config::config::value_address_family_AF_INET);
	u_short address_family = str_address_family == config::config::value_address_family_AF_INET ? server::server::IPv4 : server::server::IPv6;
}

string server::server::load_config(string key, string default_value) {
	return config::config::get(config::config::config_name, key, default_value);
}

void server::server::save_config(string key, string value) {
	config::config::set(config::config::config_name, key, value);
}

void server::server::launch_pause()
{
	this->server_start_pause = this->server_start_pause == true ? false : true;
	if (this->server_start_pause == true) {
		char buf[100];
		sprintf(buf, "%s server start. ip: %s, port: %d\n", config::get_local_time().c_str(), this->get_ip().c_str(), this->get_port());
		config::lock.lock();
		qDebug() << buf;
		config::lock.unlock();
		thread main_thread(_thread, this);
		main_thread.detach();
		
		//config::write_info(string(asctime(local) + string("server start")));
	}
	else {
		closesocket(this->server_socket);
		WSACleanup();
		char buf[100];
		sprintf(buf, "%s server stop.\n", config::get_local_time().c_str());
		config::lock.lock();
		qDebug() << buf;
		config::lock.unlock();
	}
}

void server::_thread(server * s)
{
	s->_start();
}
