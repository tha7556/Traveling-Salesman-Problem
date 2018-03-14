package Salesman;

import utility.City;
import utility.Route;
import utility.Window;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
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
	protected double mean, sum, sqrSum;
	protected double[][] bins;
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
		sum += route.getDistance();
		sqrSum += Math.pow(route.getDistance(),2.0);
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
		if(bins != null) {
			putIntoBin(route);
		}
		computations++;
	}
	public void createBins(double low, double high, int num) {
		bins = new double[2][num];
		bins[0][0] = low;
		double diff = (high-low)/(double)(num-1);
		for(int i = 0; i < num; i++) {
			if(i > 0)
				bins[0][i] = bins[0][i-1]+diff;
			bins[1][i] = 0.0;
		}
	}
	public void writeBinsToFile(String fileName) {
		try {
			FileWriter fWriter = new FileWriter(new File(fileName));
			PrintWriter pWriter = new PrintWriter(new File(fileName));

			for (int i = 0; i < bins[0].length; i++) {
				pWriter.println(bins[0][i] + "," + bins[1][i]);
			}

			pWriter.close();
			fWriter.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void putIntoBin(Route r) {
		double dist = r.getDistance();
		double max = bins[0][bins[0].length-1];
		double min = bins[0][0];
		double dx = (max-min)/bins[0].length;
		double bin = (dist-min)/dx;
		bins[1][(int)(bin)]++;
	}
	/**
	 * Randomly shuffles an Array of cities
	 * @param array The Array of Cities to shuffle
	 * @return A shuffled version of the original array.
	 */
	public static City[] shuffleArray(City[] array, boolean dontShuffleStart) {
		City[] result = new City[array.length];
		ArrayList<City> list = new ArrayList<City>(array.length);
		int i = 0;
		if(!dontShuffleStart) {
			Collections.addAll(list, array);
			Collections.shuffle(list);
		}
		else {
			Collections.addAll(list, Arrays.copyOfRange(array,1,array.length));
			Collections.shuffle(list);
			result[0] = array[0];
			i++;
		}
		for(; i < array.length; i++) {
			result[i] = list.get(i);
		}
		return result;
	}
	/**
	 * Randomly shuffles a Route
	 * @param r The Route to shuffle
	 * @return A shuffled version of the original route.
	 */
	public static Route shuffleRoute(Route r) {
		return new Route(shuffleArray(r.getCities(),false));
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

}
