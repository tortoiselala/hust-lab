#pragma once
#include<iostream>

#ifndef COMMAND_LINE_PARSER_H

#include"command_line_parser.h"

#endif // !COMMAND_LINE_PARSER_H

#ifndef FILE_WRITER_H

#include"file_writer.h"

#endif // !FILE_WRITER_H

#define STACK_OPERATOR_OVERLOADING_H

using namespace std;
class STACK {
	int  *const elems;	//申请内存用于存放栈的元素
	const int   max;	//栈能存放的最大元素个数
	int   pos;			//栈实际已有元素个数，栈空时pos=0;
public:
	FILE_WRITER * file_writer;
	bool error_flag;
	STACK(int m);		//初始化栈：最多存m个元素
	STACK(const STACK&s); 			//用栈s拷贝初始化栈
	virtual int  size() const;			//返回栈的最大元素个数max
	virtual operator int() const;		//返回栈的实际元素个数pos
	virtual int operator[] (int x) const;	//取下标x处的栈元素，第1个元素x=0
	virtual STACK& operator<<(int e); 	//将e入栈,并返回栈
	virtual STACK& operator>>(int &e);	//出栈到e,并返回栈
	virtual STACK& operator=(const STACK&s); //赋s给栈,并返回被赋值的栈
	virtual void print() const;			//打印栈
	virtual ~STACK();					//销毁栈
};

void run_stack_2(int argc, char* argv[]);


