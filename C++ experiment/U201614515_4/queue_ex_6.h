#pragma once
#define QUEUE_EX_6

#ifndef STACK_OPERATOR_OVERLOADING_H

#include"stack_operator_overloading.h"

#endif // !STACK_OPERATOR_OVERLOADING_H

using namespace std;

class QUEUE: public STACK {
	STACK s2;
public:
	QUEUE(int m);		//初始化队列：每个栈最多m个元素
	QUEUE(const QUEUE&s); 		//用队列s拷贝初始化队列
	operator int() const;			//返回队列的实际元素个数
	QUEUE& operator<<(int e); 		//将e入队列,并返回队列
	QUEUE& operator>>(int &e);	//出队列到e,并返回队列
	int operator[](int x);
	QUEUE& operator=(const QUEUE&s); //赋s给队列,并返回被赋值的队列
	void print() const;			//打印队列
	~QUEUE();					//销毁队列
};
void run_queue_ex_6(int argc, char * argv[]);
bool isPrime(int n)
{
	for (int i = 2; i <= sqrt((double)n); i++) {
		if (n%i == 0) {
			return false;//不是素数
		}
	}
	return true;//是素数
}

