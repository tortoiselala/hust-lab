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
	int  size() const;			//����ջ�����Ԫ�ظ���max
	int  howMany ( ) const;	//����ջ��ʵ��Ԫ�ظ���pos
	int  getElem (int x) const;	//ȡ�±�x����ջԪ��
	STACK& push(int e); 	//��e��ջ,������ջ
	STACK& pop(int &e); 	//��ջ��e,������ջ
    STACK& assign(const STACK&s); //��s��ջ,�����ر���ֵ��ջ
	void print() const;			//��ӡջ
	~STACK();					//����ջ
};

void run_stack_2(int argc, char* argv[]);


