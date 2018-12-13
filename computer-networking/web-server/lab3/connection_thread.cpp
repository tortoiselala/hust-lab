#include "connection_thread.h"


void start_thread(p_thread_data data)
{

	char recvbuf[MAX_BYTE_SEBD_PER_TIME];
	struct sockaddr_in peer_info;
	int peer_info_length = sizeof(peer_info);
	memset(recvbuf, '\0', MAX_BYTE_SEBD_PER_TIME);
	getpeername(data->s, (struct sockaddr *)&(peer_info), &peer_info_length);
	int i_result = recv(data->s, recvbuf, MAX_BYTE_SEBD_PER_TIME, 0);
	if (i_result == 0) {
		char buf[1024];
		sprintf(buf, "[Thread %d] connection closed...\n", data->thread_id);
		printf("%s\n", buf);
		return;
	}
	else if (i_result < 0) {
		char buf[1024];
		sprintf(buf, "[Thread %d] recv failed with error: %d\n", data->thread_id, WSAGetLastError());
		printf("%s\n", buf);
		closesocket(data->s);
		WSACleanup();
		return;
	}

	char buf[2048];
	sprintf(buf, "-------------[Thread %d infomation start]------------\n	peer ip  : %s \n	peer port: %d \n	peer request head:\n%s\n--------------[Thread %d infomation end]-------------\n", 
		data->thread_id, inet_ntoa(peer_info.sin_addr), peer_info.sin_port, recvbuf, data->thread_id);
	printf("%s\n", buf);
	
	http_head_parser::http_head_parser* parser = new http_head_parser::http_head_parser(recvbuf);

	string get_name = parser->getMessage(http_head_parser::GET);
	string get_path = *(data->virtual_path) + get_name;
	char buf_debug[1024];
	memset(buf_debug, '\0', 1024);
	sprintf(buf_debug, "%s connection from %s, peer port: %d, required file: %s\n", config::get_local_time().c_str(), inet_ntoa(peer_info.sin_addr), peer_info.sin_port, get_name.c_str());
	config::lock.lock();
	qDebug() << buf_debug;
	config::lock.unlock();
	char buffer_send[MAX_BYTE_SEBD_PER_TIME];
	FILE* file_stream = fopen(get_path.c_str(), "r");
	char tmp[200];
	int response_status_code = file_stream == nullptr ? 404 : 200;
	string response_status_tip = response_status_code == 200 ? string(" OK\r\n") : string(" Not Found\r\n");
	string response_head = string("HTTP/1.1 ") + string(_itoa(response_status_code,tmp,10)) + response_status_tip;
	string content_type = "Content-Type: " + parser->getMessage(http_head_parser::Accept) + "\r\n";
	string content_language = string("Content-Language: en, zh\r\n");
	int content_length = 0;
	vector<char> file_buffer;
	if (file_stream != nullptr) {
		fseek(file_stream, 0, SEEK_END);
		content_length = ftell(file_stream);
		rewind(file_stream);
		file_buffer.resize(content_length);
		size_t file_size = fread(&file_buffer[0], 1, content_length, file_stream);
	}
	string content_length_tip = string("Content - Length:") + string(_itoa(content_length, tmp, 10)) + string("\r\n\r\n");
	sprintf(buffer_send, "%s%s%s%s%s", response_head.c_str(), content_type.c_str(), content_language.c_str(), server_name.c_str(), content_length_tip.c_str());

	send(data->s, buffer_send, strlen(buffer_send), 0);
	if (file_stream != nullptr) {
		string res;
		res.insert(res.begin(), file_buffer.begin(), file_buffer.end());
		send(data->s, res.c_str(), content_length, 0);
	}
	closesocket(data->s);
	return;
}