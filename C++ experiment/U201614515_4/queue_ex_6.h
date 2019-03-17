#pragma once
#define QUEUE_EX_6

#ifndef STACK_OPERATOR_OVERLOADING_H

#include"stack_operator_overloading.h"

#endif // !STACK_OPERATOR_OVERLOADING_H

using namespace std;

class QUEUE: public STACK {
	STACK s2;
public:
	QUEUE(int m);		//��ʼ�����У�ÿ��ջ���m��Ԫ��
	QUEUE(const QUEUE&s); 		//�ö���s������ʼ������
	operator int() const;			//���ض��е�ʵ��Ԫ�ظ���
	QUEUE& operator<<(int e); 		//��e�����,�����ض���
	QUEUE& operator>>(int &e);	//�����е�e,�����ض���
	int operator[](int x);
	QUEUE& operator=(const QUEUE&s); //��s������,�����ر���ֵ�Ķ���
	void print() const;			//��ӡ����
	~QUEUE();					//���ٶ���
};
void run_queue_ex_6(int argc, char * argv[]);
bool isPrime(int n)
{
	for (int i = 2; i <= sqrt((double)n); i++) {
		if (n%i == 0) {
			return false;//��������
		}
	}
	return true;//������
}

