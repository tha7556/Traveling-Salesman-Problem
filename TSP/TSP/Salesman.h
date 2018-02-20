#pragma once
#include "City.h"
#include "Route.h"
#include <fstream>
#include <vector>
#include <iostream>
class Salesman
{
public:
	Salesman();
	~Salesman();
private:
	City * cities;
	int length;
	Route bestRoute;
	Route worstRoute;
	double bestFitness;
	double worstFitness;
public:
	Salesman(City* city);
	City* getCities();
	static City* getFromFile(string fileName);
};

