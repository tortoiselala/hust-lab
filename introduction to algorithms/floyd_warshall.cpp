#include "floyd_warshall.h"

void floyd_warshall::solution::compute()
{
	std::cout << "start computing..." << std::endl;
	for (int k = 0; k < this->ins_graph.graph_vertices; ++k) {
		for (int i = 0; i < this->ins_graph.graph_vertices; ++i) {
			for (int j = 0; j < this->ins_graph.graph_vertices; ++j) {
				if (add(this->ins_graph.graph_martix[i][k], this->ins_graph.graph_martix[k][j]) < this->ins_graph.graph_martix[i][j]) {
					this->ins_graph.graph_martix[i][j] = add(this->ins_graph.graph_martix[i][k], this->ins_graph.graph_martix[k][j]);
					this->shortest_path.graph_martix[i][j] = k;
				}
			}
		}
	}
	std::cout << "end computing..." << std::endl;
	return;
}

int floyd_warshall::solution::add(int a, int b)
{
	return (a == INT_MAX || b == INT_MAX) ? INT_MAX : a + b;
}

int floyd_warshall::solution::min(int a, int b)
{
	return a < b ? a : b;
}

floyd_warshall::solution::solution()
{
	this->set_graph();
	this->compute();
	return;
}

void floyd_warshall::solution::set_graph()
{
	std::cout << "please enter the number of graph' verticas: ";
	std::cin >> this->ins_graph.graph_vertices;
	getchar();
	this->shortest_path.graph_vertices = this->ins_graph.graph_vertices;
	this->ins_graph.graph_martix = new int*[this->ins_graph.graph_vertices];
	this->shortest_path.graph_martix = new int*[this->shortest_path.graph_vertices];
	for (int i = 0; i < this->ins_graph.graph_vertices; ++i) {
		this->ins_graph.graph_martix[i] = new int[this->ins_graph.graph_vertices];
		this->shortest_path.graph_martix[i] = new int[this->shortest_path.graph_vertices];
		for (int j = 0; j < this->ins_graph.graph_vertices; ++j) {
			this->ins_graph.graph_martix[i][j] = INT_MAX;
			this->shortest_path.graph_martix[i][j] = 0;
		}
		this->ins_graph.graph_martix[i][i] = 0;
	}
	for (int i = 0; i < this->ins_graph.graph_vertices; ++i) {
		for (int j = 0; j < this->ins_graph.graph_vertices; ++j) {
			if (i != j) {
				std::cout << "enter path length of (" << i << ", " << j << ") (If it is infinity, just enter):";
				char tmp[100];
				std::cin.getline(tmp, 100);
				if (strcmp(tmp, "")) {
					this->ins_graph.graph_martix[i][j] = atoi(tmp);
					//getchar();
				}
			}
		}
	}
	std::cout << "end input." << std::endl;
	return;
}

void floyd_warshall::solution::free_graph(graph * g)
{
	if (g == NULL) {
		return;
	}
	for (int i = 0; i < g->graph_vertices; ++i) {
		delete g->graph_martix[i];
	}
	delete g->graph_martix;
	delete g;
	g = NULL;
	return;
}

floyd_warshall::graph* floyd_warshall::solution::get_graph()
{
	graph *result = new graph;
	result->graph_vertices = this->ins_graph.graph_vertices;
	result->graph_martix = new int*[result->graph_vertices];
	for (int i = 0; i < this->ins_graph.graph_vertices; ++i) {
		result->graph_martix[i] = new int[result->graph_vertices];
		for (int j = 0; j < result->graph_vertices; ++j) {
			result->graph_martix[i][j] = this->ins_graph.graph_martix[i][j];
		}
	}
	return result;
}

void floyd_warshall::solution::get_shortest_path(int first, int second)
{
	if (first == second) {
		return;
	}
	else if (this->shortest_path.graph_martix[first][second] == 0) {
		std::cout << "-->" << second;
		return;
	}
	else {
		get_shortest_path(first, this->shortest_path.graph_martix[first][second]);
		get_shortest_path( this->shortest_path.graph_martix[first][second],second);
	}
}

void floyd_warshall::solution::get_path(int first, int second)
{
	if (first >= this->ins_graph.graph_vertices || second >= this->ins_graph.graph_vertices) {
		std::cout << "no such point! Try again." << std::endl;
		return;
	}
	if (this->ins_graph.graph_martix[first][second] == INT_MAX) {
		std::cout << first << " and " << second << " No path" << std::endl;
		return;
	}
	else   if (first == second) {
		std::cout << first << " and " << second << " is " << "the same Node" << std::endl;
		return;
	}
	else
	{
		std::cout << "the distance between " << first << " and " << second << " is " << this->ins_graph.graph_martix[first][second] << std::endl;
		std::cout << "the path: ";
		std::cout << first;
		get_shortest_path(first, second);
		std::cout << std::endl;
	}
}

void floyd_warshall::solution::show_graph(graph * const g)
{
	for (int i = 0; i < g->graph_vertices; ++i) {
		for (int j = 0; j < g->graph_vertices; ++j) {
			if (g->graph_martix[i][j] == INT_MAX) {
				std::cout << " N ";
			}
			else if (g->graph_martix[i][j] >= 0) {

				std::cout << " " << g->graph_martix[i][j] << " ";
			}
			else {
				std::cout << g->graph_martix[i][j] << " ";
			}
		}
		std::cout << std::endl;
	}
}

void floyd_warshall::run()
{
	solution s = solution();
	while (true) {
		std::cout << "which pair point path you want: ";
		int i = 0;
		int j = 0;
		std::cin >> i;
		std::cin >> j;
		s.get_path(i, j);
		
	}
	return;
}
