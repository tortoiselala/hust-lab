#pragma once
#include <string>
#include <winsock2.h>

using namespace std;

//����������Ϣ
class config
{
public:
	static const int MAXCONNECTION;			//���������
	static const int BUFFERLENGTH;			//��������С
	static const string SERVERADDRESS;		//��������ַ
	static const int PORT;					//�������˿�
	static const u_long BLOCKMODE;			//SOCKET����ģʽ
	static const u_short SOCKETFAMILY;		//��ַ��
	static const int SOCKETTYPE;				//socket����
	static const int SOCKETPROTOCOL;			//Э������

	static const string page_404;
private:
	config(void);
	~config(void);
};
