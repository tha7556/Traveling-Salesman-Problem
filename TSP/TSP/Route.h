#pragma once
#include "City.h"
//A Route containing an array of Cities in the order that they are traversed
class Route {
public:
	//The Main Constructor which takes an array of Cities in the order they are traversed
	Route(City *city);
	//Default Constructor
	Route();
	//Deconstructor
	~Route();
private:
	double distance;
	int length;
	City* cities;
public:
	//Calculates the total distance of the Route
	double getDistance();
	//Gets the array of Cities
	City* getCities();
	//Gets the number of Cities in the Route
	int size();
	//Calculates the fitness based on 1/distance
	double getFitness();
	//Allows modifications to the Route, setting the City at the index to the new City 
	void set(int index, City* value);
	//Gets a new Route in which the two indices are swapped
	Route swap(int indexA, int indexB);
	//Creates a string representation of the Route
	string toString();
};

