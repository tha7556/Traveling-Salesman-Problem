#include "Route.h"
#include <math.h>

Route::Route(vector<City> city) {
	distance = 0.0;
	length = city.size();
	cities = city;
	getDistance();
}

Route::Route() {

}
//deconstructor
Route::~Route() {
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


vector<City> Route::getCities() {
	return cities;
}


int Route::size() {
	return length;
}


double Route::getFitness() {
	return 1.0 / distance;
}


void Route::set(int index, City *value) {
	cities[index] = *value;
}


Route Route::swap(int indexA, int indexB) {
	Route result = Route(cities);
	result.set(indexA, &cities[indexB]);
	result.set(indexB, &cities[indexA]);
	return result;
}


string Route::toString() {
	string result = "";
	for (int i = 0; i < length; i++) {
		result += cities[i].getName() + "-";
	}
	result += cities[0].getName();
	return result;
}
