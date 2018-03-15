package Salesman;

import utility.City;
import utility.Route;

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
    /**
     * Begins evaluating random permutations to find the shortest Route
     * @return The number of seconds taken to calculate the shortest Route
     */
    public double compute() {
        Route current = new Route(cities);
        createBins(); //low and high found from previous runs
        mean = 0;
        startTime = System.nanoTime();

        for(computations = 0; computations < target;) {
            current = shuffleRoute(current);
            compareRoute(current);
        }

        endTime = System.nanoTime();
        System.out.println(Math.round((double)computations/(double)target*1000000.0)/10000.0+ "%   "+(System.nanoTime()-startTime)/1000000000.0 + " seconds");
        double stdDeviation = Math.sqrt((sqrSum-(Math.pow(sum,2.0)/(double)computations))/(double)computations);
        writeBinsToFile("data\\RandomHistogram.csv");
        System.out.println("STD Deviation: " + stdDeviation);
        System.out.println("Mean: "+mean/target);
        return (endTime-startTime)/1000000000.0;
    }
    /**
     * Overrides the Salesman.compareRoute so that it displays a percentage based on the progress
     * @param route The current Route
     */
    public void compareRoute(Route route) {
        super.compareRoute(route);
        if(computations % 100 == 0) {
            System.out.println(Math.round((double) computations / (double) target * 1000000.0) / 10000.0 + "%   " + (System.nanoTime() - startTime) / 1000000000.0 + " seconds    " + route + "\t" + route.getDistance());
        }
    }
    /**
     * Returns the current Target
     * @return The current Target
     */
    public long getTarget() {
        return target;
    }
    public static void main(String[] args) {
        String fileName = "data\\TSP.txt";
        if(args.length == 1) {
            fileName = args[0].trim();
        }
        RandomSalesman man = new RandomSalesman(Salesman.getFromFile(fileName),false);
        man.updateRoute(man.bestRoute);
        System.out.println("Took: "+man.compute() + " seconds");
        man.updateRoute(man.bestRoute);
        System.out.println("Best Route: "+man.bestRoute + " " + man.bestRoute.getDistance());
        System.out.println("Worst Route: "+man.worstRoute + " " + man.worstRoute.getDistance());
    }
}
