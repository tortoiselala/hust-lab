#define _CRT_SECURE_NO_WARNINGS
#pragma once
#define RUN_EX_1
//#define RUN_EX_2
//#define RUN_EX_3
//#define RUN_EX_6

#ifndef STACK_EX_1_H
	#ifdef RUN_EX_1
		#include"stack_ex_1.h"
	#endif // RUN_EX_1
#endif // !STACK_EX_1_H


#ifndef STACK_OPERATOR_OVERLOADING_H
	#ifdef RUN_EX_2
		#include"stack_operator_overloading.h"
	#endif // RUN_EX_2
#endif // !STACK_OPERATOR_OVERLOADING_H


#ifndef QUEUE_EX_3_H
	#ifdef RUN_EX_3
		#include"queue_ex_3.h"
	#endif // RUN_EX_3
#endif // !QUEUE_EX_3_H

#ifndef QUEUE_EX_6
	#ifdef RUN_EX_6
		#include"queue_ex_6.h"
	#endif // RUN_EX_6
#endif // !QUEUE_EX_6

using namespace std;