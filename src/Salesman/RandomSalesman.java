package Salesman;

import utility.City;
/**
 * Solution to the TSP which uses a Random search
 * @author Tyler Atkinson
 */
public class RandomSalesman extends Salesman{
    long target;

    /**
     * Creates a new Salesman who attempts a given number of times over an array of Cities
     * @param cities The Array of Cities
     * @param show True if the data should be displayed, false otherwise
     * @param target The number of times that it should be run
     */
    public RandomSalesman(City[] cities, boolean show, long target) {
        super(cities,show);
        this.target = target;
    }
    /**
     * Creates a new Salesman who attempts a given number of times over an array of Cities
     * @param cities The Array of Cities
     * @param show True if the data should be displayed, false otherwise
     */
    public RandomSalesman(City[] cities, boolean show) {
        this(cities,show,1000000);
    }
    /**
     * Creates a new Salesman who attempts a given number of times over an array of Cities
     * @param cities The Array of Cities
    */
    public RandomSalesman(City[] cities) {
        this(cities,true);
    }
    @Override
    public double compute() {
        return 0;
    }

    /**
     * Returns the current Target
     * @return The current Target
     */
    public long getTarget() {
        return target;
    }
}
