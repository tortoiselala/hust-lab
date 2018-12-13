#pragma once
#define _CRT_SECURE_NO_WARNINGS

#ifndef CLOST_POINT_SEARCH
#define CLOST_POINT_SEARCH

#include<limits.h>
#include<stdio.h>
#include<stdlib.h>
#include<math.h>
typedef struct POINT_ARRAY {
	int** point;
	int num;
}point_array;

typedef struct DIS_NEARST {
	int first_point[2];
	int second_point[2];
	double distance;
}nearst_dis;

void get_input();

void merge_sort(int left, int right, point_array* result_array, int sort_methond);

void merge(int left, int right, int mid, point_array* result_array, int sort_methond);

void destructor(point_array * array);

double get_dis(int first_point[2], int sec_point[2]);

void main_logic();

nearst_dis* recursive(point_array* array_sorted_x, point_array* array_sorted_y);

nearst_dis* get_nearst_dis_mid(point_array* sorted_array_x, point_array* sorted_array_y, nearst_dis* nearst_dis_c, int mid);

nearst_dis* min_three(nearst_dis* left, nearst_dis* right, nearst_dis* mid);

nearst_dis* min_two(nearst_dis* left, nearst_dis* right);

nearst_dis* min_three_point(int point_1[2], int point_2[2], int point_3[2]);


#endif // CLOST_POINT_SEARCH
