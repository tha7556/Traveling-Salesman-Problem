package utility;
/**
 * A City class which is an (x,y) Point representing a City in the TSP problem
 * @author Tyler Atkinson
 */
public class City {
	public int x,y;
	String name;
	/**
	 * Creates a new City named: name with coordinates (x,y) 
	 * @param name The name of the City
	 * @param x The x coordinate
	 * @param y The y coordinate
	 */
	public City(String name, int x, int y) {
		this.name = name;
		this.x = x;
		this.y = y;
	}
	/**
	 * Gets the X value of the City
	 * @return The X coordinate of the City
	 */
	public int getX() {
		return x;
	}
	/**
	 * Gets the Y value of the City
	 * @return The Y coordinate of the City
	 */
	public int getY() {
		return y;
	}
	/**
	 * Gets the Name of the City
	 * @return The Name of the City
	 */
	public String getName() {
		return name;
	}
	/**
	 * Calculates the Manhattan Distance to another city
	 * @param city The other City
	 * @return The Manhattan Distance to the other city
	 */
	public double distanceTo(City city) {
		return Math.sqrt((double)Math.pow(x-city.getX(), 2.0) + (double)Math.pow(y-city.getY(), 2.0));
	}
	/**
	 * Returns the City as a String in the form:<br>Name: (x,y)</br>
	 */
	public String toString() {
		return name + ": (" + x + "," + y + ")";
	}
}
