/*
5
0.15
0.10
0.05
0.10
0.20
0.05
0.10
0.05
0.05
0.05
0.10


*/

#pragma once
#define _CRT_SECURE_NO_WARNINGS

#ifndef OPTIMAL_BINARY_SEARCH_TREE
#define OPTIMAL_BINARY_SEARCH_TREE

#include <iostream>
#include <vector>

using namespace std;

int run_OST();

void optimal_bst(
	vector<double>&p,
	vector<double>&q,
	vector<vector<double>>&e,
	vector<vector<double>>&w,
	vector<vector<int>>&root);

#endif // !OPTIMAL_BINARY_SEARCH_TREE
