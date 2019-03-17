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
	int  *const elems;	//�����ڴ����ڴ��ջ��Ԫ��
	const int   max;	//ջ�ܴ�ŵ����Ԫ�ظ���
	int   pos;			//ջʵ������Ԫ�ظ�����ջ��ʱpos=0;
public:
	FILE_WRITER * file_writer;
	bool error_flag;
	STACK(int m);		//��ʼ��ջ������m��Ԫ��
	STACK(const STACK&s); 			//��ջs������ʼ��ջ
	virtual int  size() const;			//����ջ�����Ԫ�ظ���max
	virtual operator int() const;		//����ջ��ʵ��Ԫ�ظ���pos
	virtual int operator[] (int x) const;	//ȡ�±�x����ջԪ�أ���1��Ԫ��x=0
	virtual STACK& operator<<(int e); 	//��e��ջ,������ջ
	virtual STACK& operator>>(int &e);	//��ջ��e,������ջ
	virtual STACK& operator=(const STACK&s); //��s��ջ,�����ر���ֵ��ջ
	virtual void print() const;			//��ӡջ
	virtual ~STACK();					//����ջ
};

void run_stack_2(int argc, char* argv[]);


