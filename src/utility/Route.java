package utility;

import java.util.Arrays;

/**
 * A Route containing an Array of Cities in the order that they are traversed
 * @author Tyler Atkinson
 */
public class Route {
	private City[] cities;
	private double distance = 0.0;
	/**
	 * Creates a new Route based on an Array of Cities in the order that they are traversed
	 * @param cities The Array of cities in the order that they are traversed
	 */
	public Route(City[] cities) {
		this.cities = cities;
		getDistance();
	}
	/**
	 * Gets the array of Cities
	 * @return The array of Cities
	 */
	public City[] getCities() {
		return cities;
	}
	/**
	 * Calculates the total distance of the Route
	 * @return the total distance of the Route
	 */
	public double getDistance() {
		if(distance == 0.0) {
			for(int i = 0; i < cities.length; i++) {
				if(i+1 < cities.length) {
					distance += cities[i].distanceTo(cities[i+1]);
				}
				else {
					distance += cities[i].distanceTo(cities[0]);
				}
			}
		}
		return distance;
	}
	/**
	 * Gets the size of the Array of Cities
	 * @return The size of the city array
	 */
	public int size() {
		return cities.length;
	}
	/**
	 * Calculates the fitness of the root(1/distance)
	 * @return The Route's fitness
	 */
	public double getFitness() {
		return 1.0/distance;
	}
	/**
	 * Swaps two Cities at given indices
	 * @param indexA The index of the first City to swap
	 * @param indexB The index of the second City to swap
	 */
	public Route swap(int indexA, int indexB) {
		Route newRoute = new Route(Arrays.copyOf(cities, cities.length));
		//System.out.println("swap: "+"\n"+cities[indexB]+"\nand:\n"+cities[indexA]);
		//System.out.println("before: "+newRoute);
		newRoute.getCities()[indexA] = cities[indexB];
		newRoute.getCities()[indexB] = cities[indexA];
		//System.out.println("after: "+newRoute);
		return newRoute;
	}
	/**
	 * Returns the Route as a String in the form:<br>A-B-C-D-A</br>
	 */
	public String toString() {
		String result = "";
		for(City city : cities) {
			result += city.getName() + "-";
		}
		result += cities[0].getName();
		return result.trim();
	}
}
