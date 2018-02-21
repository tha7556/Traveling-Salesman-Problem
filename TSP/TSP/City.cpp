#include "City.h"

City::City(string name, double x, double y) {
	this->x = x;
	this->y = y;
	this->name = name;
}

City::City() {
}

City::~City(){
}

double City::getX() {
	return x;
}
double City::getY() {
	return y;
}

string City::getName() {
	return name;
}

double City::distanceTo(City *city) {
	return sqrt((double)pow(x - city->getX(), 2.0) + (double)pow(y - city->getY(), 2.0));
}

string City::toString() {
	return name;
}
