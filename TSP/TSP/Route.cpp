#include "Route.h"
#include <math.h>

Route::Route(City *city){
	distance = 0.0;
	length = sizeof(city) / sizeof(city[0]);
	cities = new City[length];
	for (int i = 0; i < length; i++) {
		cities[i] = city[i];
	}
	getDistance();
}

Route::Route() {

}
Route::~Route() //destructor
{
}


double Route::getDistance() {
	if (distance == 0.0) {
		for (int i = 0; i < length; i++) {
			if (i + 1 < length) {
				distance += cities[i].distanceTo(&cities[i + 1]);
			}
			else {
				distance += cities[i].distanceTo(&cities[0]);
			}
		}
	}
	return distance;
}


City* Route::getCities()
{
	return cities;
}


int Route::size()
{
	return length;
}


double Route::getFitness()
{
	return 1.0 / distance;
}


void Route::set(int index, City* value)
{
	cities[index] = *value;
}


Route Route::swap(int indexA, int indexB)
{
	Route newRoute = Route(cities);
	newRoute.set(indexA, &cities[indexB]);
	newRoute.set(indexB, &cities[indexA]);
	return newRoute;
}


string Route::toString()
{
	string result = "";
	for (int i = 0; i < length; i++) {
		result += cities[i].getName() + "-";
	}
	result += cities[0].getName();
	return result;
}
