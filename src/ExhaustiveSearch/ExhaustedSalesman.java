package ExhaustiveSearch;


import java.text.NumberFormat;
import java.util.Locale;

import utility.City;
import utility.Route;
import utility.Salesman;
/**
 * Solution to the TSP which uses a recursive Exhaustive Search
 * @author Tyler Atkinson
 */
public class ExhaustedSalesman extends Salesman{
	public long computations = 0;
	private NumberFormat format;
	private long startTime, endTime;
	/**
	 * Creates a new Salesman out of an Array of Cities
	 * @param cities The Array of Cities
	 */
	public ExhaustedSalesman(City[] cities) {
		this(cities,true);
	}
	public ExhaustedSalesman(City[] cities, boolean show) {
		super(cities,show);
		format = NumberFormat.getNumberInstance(Locale.US);
	}
	/**
	 * Begins recursively evaluating every permutation of the array of Cities
	 */
	public double compute() {
		Route r = new Route(cities);
		computations = 0;
		startTime = System.nanoTime();
		next(1,r);
		endTime = System.nanoTime();
		return (endTime-startTime)/1000000000.0;
		
	}
	/**
	 * Private recursive method to evaluate each permutation
	 * @param current The current index in the recursion
	 * @param route The current permutation of the Route
	 */
	private void next(int current, Route route) {
		if(current == route.size()-1) {
			compareRoute(route.swap(current,current-1));
			compareRoute(route);
			return;
		}
		for(int i = 1; i < route.size(); i++) {
			if(current < i) {
				if(current == 2) {
					//System.out.println(route+"\t"+route.swap(i, current)+"\t"+i);
				}
				next(current+1,route.swap(i, current));
			}
		}
		if(current < route.size()-2)
			next(current+1,route);
		
	}
	/**
	 * Evaluates each Route and modifies the bestFitness and BestRoute variables 
	 * @param route The current Route
	 */
	private void compareRoute(Route route) {
		//System.out.println(route);
		double fitness = route.getFitness();
		if(fitness > bestFitness) {
			System.out.println(Math.round((double)computations/(double)factorial(route.size())*1000000.0)/10000.0+ "%   "+(System.nanoTime()-startTime)/1000000000.0 + " seconds");
			bestFitness = fitness;
			bestRoute = route;
			if(show)
				updateRoute(bestRoute);
		}
		if(fitness < worstFitness) {
			worstFitness = fitness;
			worstRoute = route;
		}
		computations++;
	}
	/**
	 * Method for calculating factorials
	 * @param n The index of the factorial
	 * @return The factorial of n
	 */
	public static long factorial(long n) {
		if(n < 0) {
			return n * factorial(Math.abs(n)-1);
		}
		if(n <= 1) {
			return 1;
		}
		return n * factorial(n-1);
	}
	public static void main(String[] args) {
		//Possibly ~17.7 hours?
		//Possible to go under 7 minutes???
		ExhaustedSalesman man = new ExhaustedSalesman(Salesman.getFromFile("data\\TSP.txt"),false);
		man.updateRoute(man.bestRoute);
		System.out.println("start\n");
		System.out.println("Took: "+man.compute() + " seconds");
		System.out.println("\t   "+man.computations + " loops");
		System.out.println("Should be: " +factorial(man.getCities().length-1) + " loops");
		man.updateRoute(man.bestRoute);
		System.out.println(man.bestRoute);
	}

}
