#pragma once
#include "City.h"
class Route{
public:
	Route(City *city);
	Route();
	~Route();
private:
	double distance;
	int length;
	City* cities;
public:
	double getDistance();
	City* getCities();
	int size();
	double getFitness();
	void set(int index, City* value);
	Route swap(int indexA, int indexB);
	string toString();
};

