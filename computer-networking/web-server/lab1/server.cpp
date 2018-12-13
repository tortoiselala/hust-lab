#pragma once
#include "server.h"
server::server(void)
{
	server::server("127.0.0.1", 8080, "F:/index");
}


server::server(string ip, int port, string virtualPath)
{
	this->high = 2;
	this->low = 2;
	this->ip = string(ip);
	this->port = port;
	this->virtualPath = string(virtualPath);
	this->start_pause = false;
	this->serverSocket = INVALID_SOCKET;
}
server::~server(void)
{
}

void server::initWinsock()
{
	int iResult = WSAStartup(this->wVersionRequested, &(this->WSASata));
	if (iResult != 0) {
		printf("[error] WSAStartup failed:  %d\n", iResult);
		exit(iResult);
	}
}

void server::setSockVersion(int high, int low)
{
	this->wVersionRequested = MAKEWORD(high, low);
}

void server::setVirtualPath(string path)
{
	this->virtualPath = string(path);
}
  
void server::setSocketAddr(string ip, int port)
{
	this->socketAdderIn.sin_addr.S_un.S_addr = inet_addr(ip.c_str());
	this->socketAdderIn.sin_family = config::SOCKETFAMILY;
	this->socketAdderIn.sin_port = htons(port);
}

void server::setSocket()
{
	this->serverSocket = socket(this->socketAdderIn.sin_family, config::SOCKETTYPE, config::SOCKETPROTOCOL);
	if (this->serverSocket == INVALID_SOCKET) {
		printf("[error] at socket:  %d\n", WSAGetLastError());
		WSACleanup();
		exit(WSAGetLastError());
	}
}

void server::setSocketBind()
{
	// Setup the TCP listening socket
	int iResult = ::bind(this->serverSocket, (LPSOCKADDR)&(this->socketAdderIn), sizeof(this->socketAdderIn));
	if (iResult == SOCKET_ERROR) {
		printf("[error] bind failed with error: %d\n", WSAGetLastError());
		closesocket(this->serverSocket);
		WSACleanup();
		exit(WSAGetLastError());
	}
}

void server::setSocketListener(int maxConnection)
{
	if (listen(this->serverSocket, maxConnection) == SOCKET_ERROR) {
		printf("Listen failed with error: %ld\n", WSAGetLastError());
		closesocket(this->serverSocket);
		WSACleanup();
		exit(WSAGetLastError());
	}
}

void server::setSocketAccepter()
{
	SOCKET ClientSocket = INVALID_SOCKET;
	while (this->start_pause) {
		// Accept a client socket
		ClientSocket = accept(this->serverSocket, NULL, NULL);
		if (ClientSocket == INVALID_SOCKET) {
			printf("accept failed: %d\n", WSAGetLastError());
			closesocket(this->serverSocket);
			WSACleanup();
			continue;
		}
		this->response(ClientSocket);
		
	}
}

string server::responseStatus(int code)
{
	char buffer[1024];
	return string("HTTP/1.1 ") + string(_itoa(code, buffer, 10)) + string(" OK\r\n");
}

string server::responseContentType(httpHeadParser  * head)
{
	string contentType = head->getMessage(httpHeadParser::keyName[httpHeadParser::Accept]);
	if (contentType == httpHeadParser::error) {
		contentType = string("*/*");
	}
	return string(httpHeadParser::keyName[httpHeadParser::Content_Type]) + string(": ") + string(contentType) + string("\r\n");
}

void server::response(SOCKET socket)
{
	char recvbuf[MAX_BYTE_SEND];
	memset(recvbuf, '\0', MAX_BYTE_SEND);
	struct sockaddr_in peerInformation;
	int peerInformationLength = sizeof(peerInformation);
	getpeername(socket, (struct sockaddr *)&peerInformation, &peerInformationLength);
	printf("[info ] %s connection succeeded, peer port: %d ...\n", inet_ntoa(peerInformation.sin_addr), ntohs(peerInformation.sin_port));
	
	int iResult = recv(socket, recvbuf, MAX_BYTE_SEND, 0);

	if (iResult > 0) {
		printf("[info ] Bytes received: %d\n", iResult);
		printf("[info ] %s response : \n", inet_ntoa(peerInformation.sin_addr));
		httpHeadParser* parser = new httpHeadParser(recvbuf);
		sendData(socket, parser);
		closesocket(socket);
	}
	else if (iResult == 0) {
		printf("[error] Connection closing...\n");
	}
	else {
		printf("[error] recv failed with error: %d\n", WSAGetLastError());
		closesocket(socket);
		WSACleanup();
		return;
	}
}

void server::sendData(SOCKET s, httpHeadParser * parser)
{
	char recvbuf[MAX_BYTE_SEND];
	memset(recvbuf, '\0', MAX_BYTE_SEND);
	string GETName = parser->getMessage(httpHeadParser::keyName[httpHeadParser::GET]);
	printf("[info ] file required: %s\n", GETName.c_str());

	
	string filePath = this->virtualPath + GETName;
	/*ifstream readStream;
	readStream.open(filePath);*/
	FILE* file_stream = fopen(filePath.c_str(), "r");

	if (file_stream != nullptr) {
		
		char buf[1024];
		/* 发送响应给客户端 */
		sprintf(buf,this->responseStatus(200).c_str());
		printf("[info ] response: %s", buf);
		send(s, buf, strlen(buf), 0);
	}
	else {
		char buf[1024];
		/* 发送响应给客户端 */
		sprintf(buf, this->responseStatus(404).c_str());
		printf("[info ] response: %s", buf);
		send(s, buf, strlen(buf), 0);
		string page_404 = this->virtualPath + config::page_404;
		file_stream = fopen(page_404.c_str(), "r");
		GETName = page_404;
	}
	char buf[1024];
	strcpy(buf, SERVER_STRING);
	printf("[info ] response: %s", buf);
	send(s, buf, strlen(buf), 0);
	sprintf(buf, this->responseContentType(parser).c_str());
	printf("[info ] response: %s", buf);
	send(s, buf, strlen(buf), 0);
	sprintf(buf, "Content-Language: en, zh\r\n");
	printf("[info ] response: %s", buf);
	send(s, buf, strlen(buf), 0);

	vector<char> buffer;
	fseek(file_stream, 0, SEEK_END);
	long file_length = ftell(file_stream);
	rewind(file_stream);
	buffer.resize(file_length);
	size_t file_size = fread(&buffer[0], 1, file_length, file_stream);
	sprintf(buf, "Content-Length: %d\r\n\r\n", file_length);
	printf("[info ] response: %s", buf);
	send(s, buf, strlen(buf), 0);

	std::string res;
	res.insert(res.begin(), buffer.begin(), buffer.end());

	if (send(s, res.c_str(), file_length, 0) < 0)
	{
		printf("Send File:\t%s Failed!\n", GETName.c_str());
		return;
	}
	/*strcpy(buf, "\r\n");
	send(s, buf, strlen(buf), 0);*/
	//readStream.close();
	printf("File:\t%s Transfer Finished!\n\n\n", GETName.c_str());
}
void server::start()
{
	this->start_pause = this->start_pause == true ? false : true;
	if (this->start_pause == true) {
		printf("[info ] server is running at ip: %s, port: %d\n", this->ip.c_str(), this->port);
		thread stop_thread(_stop, this);
		stop_thread.detach();
		_start( this);
	}
}

void server::stop()
{
	closesocket(this->serverSocket);
	WSACleanup();
}

void _start(server * s)
{
	s->initWinsock();
	s->setSockVersion(s->high, s->low);
	s->setSocketAddr(s->ip, s->port);
	s->setSocket();
	s->setSocketBind();
	s->setSocketListener(config::MAXCONNECTION);
	s->setSocketAccepter();
}

void _stop(server * s)
{
	while (1) {
		string cmd;
		std::cout << "[info ] If you want to stop server, please enter [stop]:";
		std::cin >> cmd;
		if (!strcmp(cmd.c_str(), string("stop").c_str())) {
			s->stop();
			s->start_pause = false;
			std::cout << "server has been stopped!" << std::endl;
			Sleep(1200);
			return;
		}
		else {
			std::cout << "wrong command! Try again" << std::endl;
		}
	}
}

void print_cmd_usage()
{
	printf("Usage: \n");
	printf("     -h  host    the ip you want other to visit web site, if you\n");
	printf("                 don't know, use -c or check to list the ip you computer support\n");
	printf("     -c  check   list all ip you can use for host ip\n");
	printf("     -p  port    set port your website is listened\n");
	printf("     -t  path    ste path your website's root path\n");
	printf("		if anyone paramater you don't set it will be default\n");
	printf("            ip   :127.0.0.1\n");
	printf("            port :8080\n");
	printf("            path :F:\\index\n");
	printf("        enjoy your self\n");
}

void print_running_usage()
{
	printf("Usage: \n");
	printf("     -h  host    the ip you want other to visit web site, if you\n");
	printf("                 don't know, use -c or check to list the ip you computer support\n");
	printf("     -c  check   list all ip you can use for host ip\n");
	printf("     -p  port    set port your website is listened\n");
	printf("     -t  path    ste path your website's root path\n");
	printf("         start/stop   start/pause the server\n");
	printf("         exit exit program\n");
	printf("		if anyone paramater you don't set it will be default\n");
	printf("            ip   :127.0.0.1\n");
	printf("            port :8080\n");
	printf("            path :F:\\index\n");
	printf("        enjoy your self\n");
}
