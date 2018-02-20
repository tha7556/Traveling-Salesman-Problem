package ExhaustiveSearch;

import utility.City;
import utility.Salesman;

public class ExhaustedSalesman extends Salesman{

	public ExhaustedSalesman(City[] cities) {
		super(cities);
	}
	public static void main(String[] args) {
		ExhaustedSalesman man = new ExhaustedSalesman(Salesman.getFromFile("data\\TSP test.txt"));
	}

}
