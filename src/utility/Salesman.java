package utility;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
/**
 * An Abstract Salesman Object, intended to be extended for different implementations of TSP
 * @author Tyler Atkinson
 */
public abstract class Salesman {
	protected City[] cities;
	protected Route bestRoute, worstRoute;
	protected double bestFitness, worstFitness;
	public static final int MAX_WIDTH = 1 , MAX_HEIGHT = 1;
	protected Window window;
	protected boolean show;
	protected long startTime, endTime,computations;
	protected double mean;
	/**
	 * Creates a new Salesman based on an Array of Cities, and also creates a window to visualize if show = true
	 * @param cities The Array of Cities
	 * @param show Whether or not the Window should be displayed
	 */
	public Salesman(City[] cities, boolean show) {
		this.cities = cities;
		this.show = show;
		if(show)
			window = new Window(400, 400, cities);
		bestRoute = new Route(cities);
		worstRoute = bestRoute;
		bestFitness = bestRoute.getFitness();
		worstFitness = bestFitness;
	}
	/**
	 * Creates a new Salesman based on an Array of Cities, and also creates a window to visualize
	 * @param cities The Array of Cities
	 */
	public Salesman(City[] cities) {
		this(cities,true);
	}
	/**
	 * Gets the Array of Cities
	 * @return The Array of Cities
	 */
	public City[] getCities() {
		return cities;
	}
	/**
	 * Gets the Window that this Salesman created
	 * @return The Window that this Salesman created
	 */
	public Window getWindow() {
		return window;
	}
	/**
	 * Updates the Displayed Route in the window
	 * @param route The new Route to display
	 */
	public void updateRoute(Route route) {
		if(show) {
			window.updateRoute(route.getCities());
		}
	}

	/**
	 * Abstract method used to compute the shortest path
	 * @return The number of seconds taken to calculate the shortest path
	 */
	public abstract double compute();
	/**
	 * Evaluates each Route and modifies the bestFitness and BestRoute variables
	 * @param route The current Route
	 */
	public void compareRoute(Route route) {
		double fitness = route.getFitness();
		mean += route.getDistance();
		if(fitness > bestFitness) {
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
	 * Randomly shuffles an Array of cities
	 * @param array The Array of Cities to shuffle
	 * @return A shuffled version of the original array.
	 */
	public static City[] shuffleArray(City[] array) {
		City[] result = new City[array.length];
		ArrayList<City> list = new ArrayList<City>(array.length);
        Collections.addAll(list, array);
		Collections.shuffle(list);
		for(int i = 0; i < list.size(); i++)
			result[i] = list.get(i);
		return result;
	}
	/**
	 * Creates a new City Array based on data from a File in the form:
	 * <br>x,y,name separated by tabs, with each City separated by line</br>
	 * @param fileName The name of the File
	 * @return The generated City[] based on the data in the File
	 */
	public static City[] getFromFile(String fileName) {
		ArrayList<City> cities = new ArrayList<City>();
		Scanner scanner;
		try {
			scanner = new Scanner(new File(fileName));
			while(scanner.hasNextLine()) {
				String[] line = scanner.nextLine().split("\t");
				City city = new City(line[2].trim(),Double.parseDouble(line[0].trim()),Double.parseDouble(line[1].trim()));
				cities.add(city);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		City[] result = new City[cities.size()];
		for(int i = 0; i < cities.size(); i++) {
			result[i] = cities.get(i);
		}
		return result;
		
	}
    public static void main(String[] args) {
		System.out.println(new Route(getFromFile("data\\TSP.txt")).getDistance());
	}
}
