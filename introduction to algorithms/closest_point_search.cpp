#pragma once
#include"closest_point_search.h"

point_array* input_point;
point_array* sort_x;
point_array* sort_y;


void get_input() {
	input_point = (point_array *)malloc(sizeof(input_point));
	sort_x = (point_array *)malloc(sizeof(input_point));
	sort_y = (point_array *)malloc(sizeof(input_point));
	input_point->num = 0;
	sort_x->num = 0;
	sort_y->num = 0;
	int num = 0;
	printf("[input]  please input number of points:");
	scanf("%d", &num);
	if (num <= 0) {
		printf("[error]  wrong number");
		return;
	}
	else {
		input_point->num = num;
		input_point->point = (int **)malloc(sizeof(int*) * input_point->num);
		sort_x->num = num;
		sort_x->point = (int **)malloc(sizeof(int*) * sort_x->num);
		sort_y->num = num;
		sort_y->point = (int **)malloc(sizeof(int*) * sort_y->num);
	}
	printf("[input]  input points(one point pair/one line)\n");
	for (int i = 0; i < num; ++i) {
		input_point->point[i] = (int *)malloc(sizeof(int) * 2);
		sort_x->point[i] = (int *)malloc(sizeof(int) * 2);
		sort_y->point[i] = (int *)malloc(sizeof(int) * 2);
		scanf("%d %d", &input_point->point[i][0], &input_point->point[i][1]);
		sort_x->point[i][0] = input_point->point[i][0];
		sort_x->point[i][1] = input_point->point[i][1];

		sort_y->point[i][0] = input_point->point[i][0];
		sort_y->point[i][1] = input_point->point[i][1];
	}
	printf("[info ]  complete input point pairs\n");
	main_logic();
	system("pause");
	return;
}

void merge_sort(int left, int right, point_array* result_array, int sort_methond) {
	if (left < right - 1) {
		int mid = (left + right) / 2;
		merge_sort(left, mid, result_array, sort_methond);
		merge_sort(mid, right, result_array, sort_methond);
		merge(left, right, mid, result_array, sort_methond);
	}
}

void merge(int left, int right, int mid, point_array* result_array, int sort_methond) {
	int front = mid - left;
	int back = right - mid;
	int** left_array = (int **)malloc(sizeof(int *) * (front + 1));
	int** right_array = (int **)malloc(sizeof(int *) * (back + 1));
	for (int i = 0; i < front; ++i) {
		left_array[i] = (int *)malloc(sizeof(int) * 2);
		left_array[i][0] = result_array->point[left + i][0];
		left_array[i][1] = result_array->point[left + i][1];
	}
	left_array[front] = (int *)malloc(sizeof(int) * 2);
	left_array[front][0] = INT_MAX;
	left_array[front][1] = INT_MAX;
	for (int i = 0; i < back; ++i) {
		right_array[i] = (int *)malloc(sizeof(int) * 2);
		right_array[i][0] = result_array->point[mid + i][0];
		right_array[i][1] = result_array->point[mid + i][1];
	}
	right_array[back] = (int *)malloc(sizeof(int) * 2);
	right_array[back][0] = INT_MAX;
	right_array[back][1] = INT_MAX;
	for (int i = 0, j = 0; i <= front && j <= back && left < right; ++left) {
		if (left_array[i][sort_methond] <= right_array[j][sort_methond]) {
			result_array->point[left][0] = left_array[i][0];
			result_array->point[left][1] = left_array[i][1];
			++i;
		}
		else {
			result_array->point[left][0] = right_array[j][0];
			result_array->point[left][1] = right_array[j][1];
			++j;
		}
	}

	for (int i = 0; i <= front; ++i) {
		free(left_array[i]);
	}
	for (int i = 0; i <= back; ++i) {
		free(right_array[i]);
	}
}

void destructor(point_array * array) {
	for (int i = 0; i < array->num; ++i) {
		free(array->point[i]);
	}
	free(array->point);
	free(array);
}

double get_dis(int first_point[2], int sec_point[2]) {
	return sqrt(pow(abs(first_point[0] - sec_point[0]), 2) + pow(abs(first_point[1] - sec_point[1]), 2));
}

void main_logic() {
	/*
	归并排序，x，y
	*/
	merge_sort(0, sort_x->num, sort_x, 0);
	merge_sort(0, sort_y->num, sort_y, 1);
	printf("[info ]  complete merge sort\n");
	printf("[info ]  merge sort result(by x value): ");
	for (int i = 0; i < sort_x->num; ++i) {
		printf("(%d, %d) ", sort_x->point[i][0], sort_x->point[i][1]);
	}
	printf("\n");
	printf("[info ]  merge sort result(by y value): ");
	for (int i = 0; i < sort_y->num; ++i) {
		printf("(%d, %d) ", sort_y->point[i][0], sort_y->point[i][1]);
	}
	printf("\n");



	nearst_dis* result = recursive(sort_x, sort_y);
	printf("[info ]  nearst point pair is:(%d, %d) (%d, %d)  distance: %.5lf\n", 
		result->first_point[0], 
		result->first_point[1], 
		result->second_point[0], 
		result->second_point[1], 
		result->distance);
	return;
}

nearst_dis* recursive(point_array* array_sorted_x, point_array* array_sorted_y) {
	if (array_sorted_x->num > 3) {
		int mid = array_sorted_x->num / 2;
		point_array* left_array_sorted_x_next = (point_array *)malloc(sizeof(point_array));
		left_array_sorted_x_next->point = (int **)malloc(sizeof(int)*(mid));
		left_array_sorted_x_next->num = 0;

		point_array* right_array_sorted_x_next = (point_array *)malloc(sizeof(point_array));
		right_array_sorted_x_next->point = (int **)malloc(sizeof(int)*(array_sorted_x->num - mid));
		right_array_sorted_x_next->num = 0;
		/*
		构建传递给下一次递归调用的数组参数表，即左右分割数组
			a.以x排序数组
		*/
		for (int i = 0, j = 0, k = 0; i < array_sorted_x->num; ++i) {
			if (i < mid) {
				left_array_sorted_x_next->point[j] = (int *)malloc(sizeof(int) * 2);
				left_array_sorted_x_next->point[j][0] = array_sorted_x->point[i][0];
				left_array_sorted_x_next->point[j][1] = array_sorted_x->point[i][1];
				++left_array_sorted_x_next->num;
				++j;
			}
			else {
				right_array_sorted_x_next->point[k] = (int *)malloc(sizeof(int) * 2);
				right_array_sorted_x_next->point[k][0] = array_sorted_x->point[i][0];
				right_array_sorted_x_next->point[k][1] = array_sorted_x->point[i][1];
				++right_array_sorted_x_next->num;
				++k;
			}
		}
		/*
			b.以y排序数组
		*/
		point_array* left_array_sorted_y_next = (point_array *)malloc(sizeof(point_array));
		left_array_sorted_y_next->point = (int **)malloc(sizeof(int)*(mid));
		left_array_sorted_y_next->num = 0;
		point_array* right_array_sorted_y_next = (point_array *)malloc(sizeof(point_array));
		right_array_sorted_y_next->point = (int **)malloc(sizeof(int)*(array_sorted_y->num - mid));
		right_array_sorted_y_next->num = 0;
		for (int i = 0, j = 0, k = 0; i < array_sorted_y->num; ++i) {
			if (array_sorted_y->point[i][0] >= left_array_sorted_x_next->point[0][0]
				&&
				array_sorted_y->point[i][0] <= left_array_sorted_x_next->point[left_array_sorted_x_next->num - 1][0]) {
				left_array_sorted_y_next->point[j] = (int *)malloc(sizeof(int) * 2);
				left_array_sorted_y_next->point[j][0] = array_sorted_y->point[i][0];
				left_array_sorted_y_next->point[j][1] = array_sorted_y->point[i][1];
				++left_array_sorted_y_next->num;
				++j;
			}
			else {
				right_array_sorted_y_next->point[k] = (int *)malloc(sizeof(int) * 2);
				right_array_sorted_y_next->point[k][0] = array_sorted_y->point[i][0];
				right_array_sorted_y_next->point[k][1] = array_sorted_y->point[i][1];
				++right_array_sorted_y_next->num;
				++k;
			}
		}


		nearst_dis* nearst_dis_left = recursive(left_array_sorted_x_next, left_array_sorted_y_next);
		nearst_dis* nearst_dis_right = recursive(right_array_sorted_x_next, right_array_sorted_y_next);
		nearst_dis* nearst_dis_mid = get_nearst_dis_mid(array_sorted_x, array_sorted_y, min_two(nearst_dis_left, nearst_dis_right), mid);
		return min_three(nearst_dis_left, nearst_dis_right, nearst_dis_mid);
	}
	else if (array_sorted_x->num == 2) {
		nearst_dis* result = (nearst_dis *)malloc(sizeof(nearst_dis));
		result->first_point[0] = array_sorted_x->point[0][0];
		result->first_point[1] = array_sorted_x->point[0][1];
		result->second_point[0] = array_sorted_x->point[1][0];
		result->second_point[1] = array_sorted_x->point[1][1];
		result->distance = get_dis(array_sorted_x->point[0], array_sorted_x->point[1]);
		return result;
	}
	else if (array_sorted_x->num == 3) {
		return min_three_point(array_sorted_x->point[0], array_sorted_x->point[1], array_sorted_x->point[2]);
	}
	else {
		nearst_dis* result = (nearst_dis *)malloc(sizeof(nearst_dis));
		result->distance = INT_MAX;
		result->first_point[0] = INT_MAX;
		result->first_point[1] = INT_MAX;
		result->second_point[0] = INT_MAX;
		result->second_point[1] = INT_MAX;
		return result;
	}
}

nearst_dis* get_nearst_dis_mid(point_array* sorted_array_x, point_array* sorted_array_y, nearst_dis* nearst_dis_c, int mid) {
	nearst_dis* result = (nearst_dis *)malloc(sizeof(nearst_dis));
	result->distance = nearst_dis_c->distance;
	result->first_point[0] = nearst_dis_c->first_point[0];
	result->first_point[1] = nearst_dis_c->first_point[1];
	result->second_point[0] = nearst_dis_c->second_point[0];
	result->second_point[1] = nearst_dis_c->second_point[1];
	for (int i = mid + 1; i < sorted_array_x->num; ++i) {
		if (sorted_array_x->point[i][0] - sorted_array_x->point[mid][0] < result->distance
			&& abs(sorted_array_x->point[i][1] - sorted_array_x->point[mid][1]) < result->distance
			&& get_dis(sorted_array_x->point[i], sorted_array_x->point[mid])) {
			result->distance = get_dis(sorted_array_x->point[i], sorted_array_x->point[mid]);
			result->first_point[0] = sorted_array_x->point[i][0];
			result->first_point[1] = sorted_array_x->point[i][1];
			result->second_point[0] = sorted_array_x->point[mid][0];
			result->second_point[1] = sorted_array_x->point[mid][1];
		}
		else {
			break;
		}
	}
	for (int i = mid; i >= 0; --i) {
		if (sorted_array_x->point[mid][0] - sorted_array_x->point[i][0] < result->distance
			&& abs(sorted_array_x->point[i][1] - sorted_array_x->point[mid][1]) < result->distance
			&& get_dis(sorted_array_x->point[i], sorted_array_x->point[mid])) {
			result->distance = get_dis(sorted_array_x->point[i], sorted_array_x->point[mid]);
			result->first_point[0] = sorted_array_x->point[i][0];
			result->first_point[1] = sorted_array_x->point[i][1];
			result->second_point[0] = sorted_array_x->point[mid][0];
			result->second_point[1] = sorted_array_x->point[mid][1];
		}
		else {
			break;
		}
	}
	return result;
}
nearst_dis* min_three(nearst_dis* left, nearst_dis* right, nearst_dis* mid) {
	nearst_dis* min_two_result = min_two(left, right);
	return min_two_result->distance < mid->distance ? min_two_result : mid;
}
nearst_dis* min_two(nearst_dis* left, nearst_dis* right) {
	return left->distance < right->distance ? left : right;
}

nearst_dis* min_three_point(int point_1[2], int point_2[2], int point_3[2]) {
	nearst_dis* result = (nearst_dis *)malloc(sizeof(nearst_dis));
	double dis[3];
	dis[0] = get_dis(point_1, point_2);
	dis[1] = get_dis(point_2, point_3);
	dis[2] = get_dis(point_3, point_1);
	if (dis[0] < dis[1]) {
		result->distance = dis[0];
		result->first_point[0] = point_1[0];
		result->first_point[1] = point_1[1];
		result->second_point[0] = point_2[0];
		result->second_point[1] = point_2[1];
	}
	else {
		result->distance = dis[1];
		result->first_point[0] = point_2[0];
		result->first_point[1] = point_2[1];
		result->second_point[0] = point_3[0];
		result->second_point[1] = point_3[1];
	}

	if (dis[2] < result->distance) {
		result->distance = dis[2];
		result->first_point[0] = point_3[0];
		result->first_point[1] = point_3[1];
		result->second_point[0] = point_1[0];
		result->second_point[1] = point_1[1];
	}
	return result;
}