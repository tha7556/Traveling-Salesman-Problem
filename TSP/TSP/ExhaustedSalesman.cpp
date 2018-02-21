#include "ExhaustedSalesman.h"



ExhaustedSalesman::ExhaustedSalesman() {
	startTime = 0;
	endTime = 0;
	computations = 0;
}


ExhaustedSalesman::~ExhaustedSalesman() {
}


ExhaustedSalesman::ExhaustedSalesman(City* city) : Salesman(city) {

}


double ExhaustedSalesman::compute() {
	computations = 0;
	time_t start = time(NULL);
	next(1, &bestRoute);
	time_t end = time(NULL);
	return difftime(end, start);
}


void ExhaustedSalesman::next(int current, Route* route) {
	if (current == length) {
		compareRoute(route);
		compareRoute(&route->swap(current, current - 1));
		return;
	}
	for (int i = 1; i < length; i++) {
		if (current < i) {
			next(current + 1, &route->swap(i, current));
		}
	}
	if (current < length - 2) {
		next(current + 1, route);
	}
}


long ExhaustedSalesman::factorial(int n) {
	if (n < 0)
		return n * factorial(abs(n) - 1);
	if (n <= 1)
		return 1;
	return n * factorial(n - 1);
}


void ExhaustedSalesman::compareRoute(Route* route) {
	cout << computations << "computations" << endl;
	double fitness = route->getFitness();
	if (fitness > bestFitness) {
		bestFitness = fitness;
		bestRoute = *route;
	}
	if (fitness < worstFitness) {
		worstFitness = fitness;
		worstRoute = *route;
	}
	computations++;
}
int ExhaustedSalesman::getComputations() {
	return computations;
}
int ExhaustedSalesman::getLength() {
	return length;
}
int main() {
	City *arr = Salesman::getFromFile("TSP test.txt");
	ExhaustedSalesman man = ExhaustedSalesman(arr);
	cout << man.getComputations() << " computations" << endl;
	cout << "vs exhausted: " << endl << ExhaustedSalesman::factorial(man.getLength()) << "computations";
	cout << man.compute() << " seconds";
}
