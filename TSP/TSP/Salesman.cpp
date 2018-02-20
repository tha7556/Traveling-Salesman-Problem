#include "Salesman.h"



Salesman::Salesman()
{
	length = 0;
	bestFitness = 0.0;
	worstFitness = 0.0;
}


Salesman::~Salesman()
{
}


Salesman::Salesman(City* city) {
	length = sizeof(*city) / sizeof(city[0]);
	cities = new City[length];
	for (int i = 0; i < length; i++) {
		cities[i] = city[i];
	}
	bestRoute = Route(city);
	worstRoute = bestRoute;
	bestFitness = bestRoute.getFitness();
	worstFitness = bestFitness;
}


City* Salesman::getCities() {
	return cities;
}


City* Salesman::getFromFile(string fileName)
{
	ifstream file;
	file.open(fileName);
	if (!file) {
		cerr << "Unable to open: " << fileName;
		exit(1);
	}
	string line;
	int len = 0;
	vector<City> cList;
	while (getline(file, line)) {
		double x = atof(line.substr(0, line.find("\t")).c_str());
		line = line.substr(line.find("\t")+1);
		double y = atof(line.substr(0, line.find("\t")+1).c_str());
		line = line.substr(line.find("\t")+1);
		string name = line;
		cout << name << endl;
		City city = City(name, x, y);
		cList.push_back(city);
		len++;
	}
	file.close();
	City *result = new City[len];
	for (int i = 0; i < len; i++) {
		result[i] = cList[i];
	}
	cout << len;
	return result;
}
int main() {
	City* c = Salesman::getFromFile("TSP test.txt");
	Salesman man = Salesman(c);
	cout << "finita!";
	while (true) {
		continue;
	}
}
