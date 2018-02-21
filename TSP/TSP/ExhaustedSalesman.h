#pragma once
#include "Salesman.h"
#include <time.h>
class ExhaustedSalesman :
	public Salesman
{
public:
	ExhaustedSalesman();
	~ExhaustedSalesman();
private:
	long startTime;
	long endTime;
	long computations;
public:
	ExhaustedSalesman(City* city);
	double compute();
	void next(int current, Route* route);
	static long factorial(int n);
	void compareRoute(Route* route);
	int getComputations();
	int getLength();
};

