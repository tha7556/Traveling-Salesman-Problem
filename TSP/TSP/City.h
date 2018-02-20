#pragma once
#include <string>
using namespace std;
class City
{
public:
	City(string name, double x, double y);
	City();
	~City();
private:
	double x;
	double y;
	string name;
public:
	double getX();
	double getY();
	string getName();
	double distanceTo(City *city);
	string toString();
};

