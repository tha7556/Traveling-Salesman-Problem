#pragma once
#include "City.h"
#include "Route.h"
#include <fstream>
#include <vector>
#include <iostream>
//A generic Salesman intended to be extended into different methods for solving TSP 
class Salesman {
public:
	//The main Constructor which creates a new solver based on an array of Cities
	Salesman(vector<City> city);
	//The default Constructor
	Salesman();
	//Deconstructor
	~Salesman();
protected:
	vector<City> cities;
	int length;
	Route bestRoute;
	Route worstRoute;
	double bestFitness;
	double worstFitness;
public:
	//Gets the array of Cities
	vector<City> getCities();
	//Grabs an array of Cities from the given file with lines in the form:
	//x<tab>y<tab>name
	static vector<City> getFromFile(string fileName);
};

