#include "config.h"
#include <string>
using namespace std;

config::config(void){}

config::~config(void){}

const string config::SERVERADDRESS = "127.0.0.1";	//������IP��ַ
const int config::MAXCONNECTION = 50;				//���������5
const int config::BUFFERLENGTH = 256;				//��������С256�ֽ�
const int config::PORT = 5050;						//�������˿�5050
const u_long config::BLOCKMODE = 1;					//SOCKETΪ������ģʽ
const u_short config::SOCKETFAMILY = AF_INET;		//��ַ��
const int config::SOCKETTYPE = SOCK_STREAM;			//socket���ݴ�������
const int config::SOCKETPROTOCOL = IPPROTO_TCP;	//Э������

const string config::page_404 = string("/404.html");