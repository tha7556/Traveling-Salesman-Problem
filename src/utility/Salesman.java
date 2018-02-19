package utility;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
/**
 * An Abstract Salesman Object, intended to be extended for different implementations of TSP
 * @author Tyler Atkinson
 */
public abstract class Salesman {
	protected City[] cities;
	public static final int MAX_WIDTH = 200 , MAX_HEIGHT = 200;
	protected Window window;
	/**
	 * Creates a new Salesman based on an Array of Cities, and also creates a window to visualize
	 * @param cities The Array of Cities
	 */
	public Salesman(City[] cities) {
		this.cities = cities;
		window = new Window(400, 400, cities);
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
	 * Randomly shuffles an Array of cities
	 * @param array The Array of Cities to shuffle
	 * @return A shuffled version of the original array.
	 */
	public static City[] shuffleArray(City[] array) {
		City[] result = new City[array.length];
		ArrayList<City> list = new ArrayList<City>(array.length);
		for(City c : array)
			list.add(c);
		Collections.shuffle(list);
		for(int i = 0; i < list.size(); i++)
			result[i] = list.get(i);
		return result;
	}
	/**
	 * Creates a new City Array based on data from a File in the form:
	 * <br>x,y,name seperated by tabs, with each City seperated by line</br>
	 * @param fileName The name of the File
	 * @return The generated City[] based on the data in the File
	 */
	public static City[] getFromFile(String fileName) {
		ArrayList<City> cities = new ArrayList<City>();
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(fileName));
			while(scanner.hasNextLine()) {
				String[] line = scanner.nextLine().split("\t");
				City city = new City(line[2].trim(),Integer.parseInt(line[0].trim()),Integer.parseInt(line[1].trim()));
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
