#pragma once
#define STACK_EX_1_H

#include<stdio.h>
#include<stdlib.h>
#include<string>

#ifndef COMMAND_LINE_PARSER

#include"command_line_parser.h"

#endif // !COMMAND_LINE_PARSER

#ifndef FILE_WRITER_H

#include"file_writer.h"

#endif // !FILE_WRITER_H



#define max_cmd_num 100
typedef struct stack {
	int  *elems;	//�����ڴ����ڴ��ջ��Ԫ��
	int   max;	//ջ�ܴ�ŵ����Ԫ�ظ���
	int   pos;	//ջʵ������Ԫ�ظ�����ջ��ʱpos=0;
}STACK;

void run_stack(int argc, char *argv[]);//���Գ������
void initSTACK(STACK *const p, int m);	//��ʼ��pָ���ջ�����m��Ԫ��
void initSTACK(STACK *const p, const STACK & s); //��ջs��ʼ��pָ���ջ
int  size(const STACK *const p);		//����pָ���ջ�����Ԫ�ظ���max
int  howMany(const STACK *const p);	//����pָ���ջ��ʵ��	Ԫ�ظ���pos
int  getelem(const STACK *const p, int x);	//ȡ�±�x����ջԪ��
STACK *const push(STACK *const p, int e); 	//��e��ջ��������p
STACK *const pop(STACK *const p, int &e); 	//��ջ��e��������p
STACK *const assign(STACK*const p, const STACK&s); //��s��pָ��ջ,������p
void print(const STACK*const p);			//��ӡpָ���ջ
void destroySTACK(STACK*const p);		//����pָ���ջ