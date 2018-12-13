
/*
one test case

5
3
8

-4


1
7

4


2

-5




6




*/

#ifndef FLOYD_WARSHALL
#define FLOYD_WARSHALL
#define _CRT_SECURE_NO_WARNINGS

#include<string>
#include<iostream>

namespace floyd_warshall {
	typedef struct GRAPH {
		int graph_vertices;
		int **graph_martix;
	}graph;
	class solution {
	private:
		graph ins_graph;
		graph shortest_path;
		void compute();
		int add(int a, int b);
		int min(int a, int b);
		void get_shortest_path(int first, int second);
		void free_graph(graph * g);
	public:
		solution();
		virtual void set_graph();
		virtual graph* get_graph();
		virtual void get_path(int first, int second);
		virtual void show_graph(graph * const g);
	};
	void run();
}
#endif // !FLOYD_WARSHALL
