#pragma once
#include <string>
using namespace std;
//A City class which is an (x,y) Point representing a City in the TSP problem
class City {
public:
	//Main constructor which creates a new City with name: name and coordinates (x,y)
	City(string name, double x, double y);
	//Default constructor
	City();
	//Deconstructor
	~City();
private:
	double x;
	double y;
	string name;
public:
	//Gets the x coordinate of the City
	double getX();
	//Gets the y coordinate of the City
	double getY();
	//Gets the name of the City
	string getName();
	//Calculates the Manhattan distance to another City
	double distanceTo(City *city);
	//Creates a string representation of the City
	string toString();
};

