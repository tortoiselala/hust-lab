#pragma once
#define _CRT_SECURE_NO_WARNINGS

#ifndef BIG_INTEGER
#define BIG_INTEGER

#define max_value 300
#include<iostream>
#include<algorithm>
#include<string>
#include<cmath>
class BigInteger {
	

public:
	std::string num1;
	std::string num2;
	std::string multiply(std::string n1, std::string n2) {
		int n1Length = n1.length();
		int n2Length = n2.length();
		if (n1Length == 0 || n2Length == 0 || n1 == "0" || n2 == "0") {
			return std::string("0");
		}

		if (n1Length < 5 && n2Length < 5) {
			char r[max_value];
			return std::string(_itoa(atoi(n1.c_str()) * atoi(n2.c_str()), r, 10));
		}

		std::string a(n1.substr(0, n1Length / 2));
		std::string b(n1.substr(n1Length / 2, n1Length - n1Length / 2));
		std::string c(n2.substr(0, n2Length / 2));
		std::string d(n2.substr(n2Length / 2, n2Length - n2Length / 2));

		std::string t1 = multiply(a, c) + std::string(d.length() + b.length(), '0');
		std::string t2 = multiply(b, c) + std::string(d.length(), '0');
		std::string t3 = multiply(a, d) + std::string(b.length(), '0');
		std::string t4 = multiply(b, d);
		return add(add(add(t1, t2), t3), t4);

	}
	std::string add(std::string s1, std::string s2) {
		if (s1.length() == 0 && s2.length() == 0) {
			return std::string("0");
		}
		else if (s1.length() == 0) {
			return s2;
		}
		else if (s2.length() == 0) {
			return s1;
		}
		if (s1 == "0") {
			return s2;
		}
		if (s2 == "0") {
			return s1;
		}
		//较短的字符串放在第二位。
		if (s1.size() < s2.size())
		{
			std::string temp = s1;
			s1 = s2;
			s2 = temp;
		}
		std::string sum;

		s2 = std::string(s1.size() - s2.size(), '0') + s2;
		int a = 0;
		for (int i = s1.size() - 1; i >= 0; i--)
		{
			int b = (s1[i] - '0') + (s2[i] - '0') + a;
			if (b > 9) {
				sum.push_back((b - 10) + '0');
				a = 1;
			}
			else {
				sum.push_back(b + '0');
				a = 0;
			}

		}
		sum.push_back(a + '0');
		
		std::string::iterator it = sum.end() - 1;
		while (it != sum.begin()) {
			if ((*it) == '0') {
				it = sum.erase(it);
				--it;
			}
			else {
				break;
			}
		}
		std::reverse(sum.begin(), sum.end());
		return sum;
	}
	std::string	subtract(std::string n1, std::string n2) {

		int sign = 1;//n1-n2大于等于0    sign = 1否则sign = -1；
		int n1Length = n1.length();
		int n2Length = n2.length();
		if (n1Length < n2Length) {
			std::string tmp = n1;
			n1 = n2;
			n2 = tmp;
			sign = -1;
		}
		//n1与n2长度相等时，从高位比较，找出较大的一个
		else if( n1Length == n2Length) {
			int i = 0;
			for (; i < n1Length; ++i){
				int subResult = n1[i] - n2[i];
				if (subResult< 0) {
					std::string tmp = n1;
					n1 = n2;
					n2 = tmp;
					sign = -1;
					break;
				}
				else if (subResult == 0) {
					continue;
				}
				else {
					break;
				}
			}
		}
		n2 = std::string(n1.length() - n2.length(), '0') + n2;

		std::string result;

		int borrow = 0;
		for (int i = n1.length()-1; i >= 0; --i) {
			int tmp = n1[i] - n2[i] - borrow;
			if (tmp < 0) {
				tmp = 10 + n1[i] - n2[i] - borrow;
				borrow = 1;
			}
			else {
				borrow = 0;
			}
			result.push_back(tmp + '0');
		}
		std::string::iterator it = result.end() - 1;
		while (it != result.begin()) {
			if ((*it) == '0') {
				it = result.erase(it);
				--it;
			}
			else {
				break;
			}
		}
		if (sign == -1) {
			result.push_back('-');
		}
		std::reverse(result.begin(), result.end());
		return result;
	}

public:
	BigInteger(std::string n1, std::string n2) {
		this->num1 = n1;
		this->num2 = n2;
	}
	~BigInteger() {}

};

void run_bigInteger() {

	std::cout << "...............test multiply.............." << std::endl;
	std::string testSetMultiply[2][3] = 
	{ 
		{ "9223372036854775807" ,"1234567891111", "11386878964471969137416693151577"},
		{ "12021000000000010", "12021000000000010", "144504441000000240420000000000100" }
	};
	for (int i = 0; i < 2; ++i) {
		std::cout << "multiply case: " << i << std::endl;
		BigInteger test(testSetMultiply[i][0], testSetMultiply[i][1]);
		std::cout
			<< "      " << testSetMultiply[i][0] << " * " << testSetMultiply[i][1] << std::endl
			<< "      Theoretical value: " << testSetMultiply[i][2] << std::endl
			<< "      Actual      value: " << test.multiply(test.num1, test.num2) << std::endl
			<< "             is correct:" << (test.multiply(test.num1, test.num2) == testSetMultiply[i][2] ? "true" : "false")
			<< std::endl;
		
	}
	std::cout << "...............test multiply end........" << std::endl << std::endl;


	std::cout << "...............test addition.............." << std::endl;
	std::string testSetAddition[1][3] =
	{
		{ "1476434256335201019505915952536059041319057206399521214104693657122061789167297053187930937033" ,"1476434256335201019505915952536059041319057206399521214104693657122061789167297053187930937033", "2952868512670402039011831905072118082638114412799042428209387314244123578334594106375861874066" },
	
	};
	for (int i = 0; i < 1; ++i) {
		std::cout << "addition case: " << i << std::endl;
		BigInteger test(testSetAddition[i][0], testSetAddition[i][1]);
		std::cout
			<< "      " << testSetAddition[i][0] << " + " << testSetAddition[i][1] << std::endl
			<< "      Theoretical value: " << testSetAddition[i][2] << std::endl
			<< "      Actual      value: " << test.add(test.num1, test.num2) << std::endl
			<< "             is correct:" << (test.add(test.num1, test.num2) == testSetAddition[i][2] ? "true" : "false")
			<< std::endl;

	}
	std::cout << "...............test addition end........" << std::endl << std::endl;


	std::cout << "...............test Sub.............." << std::endl;
	std::string testSetSub[1][3] =
	{
		{"10245689546", "265657887878", "-255412198332"}
	};
	for (int i = 0; i < 1; ++i) {
		std::cout << "sub case: " << i << std::endl;
		BigInteger test(testSetSub[i][0], testSetSub[i][1]);
		std::cout
			<< "      " << testSetSub[i][0] << " - " << testSetSub[i][1] << std::endl
			<< "      Theoretical value: " << testSetSub[i][2] << std::endl
			<< "      Actual      value: " << test.subtract(test.num1, test.num2) << std::endl
			<< "             is correct:" << (test.subtract(test.num1, test.num2) == testSetSub[i][2] ? "true" : "false")
			<< std::endl;

	}
	std::cout << "...............test Sub end........" << std::endl << std::endl;

	system("pause");
}
#endif // !BIG_INTEGER
