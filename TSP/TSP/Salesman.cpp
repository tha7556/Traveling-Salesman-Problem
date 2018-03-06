#include "Salesman.h"

Salesman::Salesman() {
	length = 0;
	bestFitness = 0.0;
	worstFitness = 0.0;
}

//Deconstructor
Salesman::~Salesman() {
}


Salesman::Salesman(vector<City> city) {
	length = city.size();
	cities = city;
	for (int i = 0; i < length; i++) {
		cities[i] = city[i];
	}
	bestRoute = Route(city);
	worstRoute = bestRoute;
	bestFitness = bestRoute.getFitness();
	worstFitness = bestFitness;
}


vector<City> Salesman::getCities() {
	return cities;
}


vector<City> Salesman::getFromFile(string fileName) {
	ifstream file;
	file.open(fileName);
	if (!file) {
		cerr << "Unable to open: \"" << fileName << "\"";
		exit(1);
	}
	string line;
	int len = 0;
	vector<City> cList;
	while (getline(file, line)) {
		double x = atof(line.substr(0, line.find("\t")).c_str());
		line = line.substr(line.find("\t") + 1);
		double y = atof(line.substr(0, line.find("\t") + 1).c_str());
		line = line.substr(line.find("\t") + 1);
		string name = line;
		City city = City(name, x, y);
		cList.push_back(city);
		len++;
	}
	file.close();
	return cList;
}



