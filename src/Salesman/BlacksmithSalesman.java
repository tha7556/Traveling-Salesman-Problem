package Salesman;

import utility.City;
import utility.Route;

import java.util.Random;

public class BlacksmithSalesman extends Salesman {
    private Route currentRoute, prevRoute;
    private double temperature = 100.0, coolingRate = 0.00001;
    private Random rand;
    public BlacksmithSalesman(City[] cities,boolean show) {
        super(cities,show);
        prevRoute = new Route(cities);
        currentRoute = prevRoute;
        rand = new Random();
    }
    public BlacksmithSalesman(City[] cities) {
        this(cities,false);
    }
    public double compute() {
        startTime = System.nanoTime();
        while(temperature > 0.00000001) {
            //System.out.println(temperature);
            Route newRoute = currentRoute;
            int swapIndex1 = (int)(rand.nextDouble()*(cities.length-1)+1);
            int swapIndex2 = (int)(rand.nextDouble()*(cities.length-1)+1);
            while(swapIndex1 == swapIndex2) {
                swapIndex1 = (int)(rand.nextDouble()*(cities.length-1)+1);
                swapIndex2 = (int)(rand.nextDouble()*(cities.length-1)+1);
            }
            newRoute = newRoute.swap(swapIndex1,swapIndex2);
            double currentEnergy = currentRoute.getDistance();
            double newEnergy = newRoute.getDistance();
            if(acceptanceProbability(currentEnergy,newEnergy) > rand.nextDouble()) {
                prevRoute = currentRoute;
                currentRoute = newRoute;
                compareRoute(currentRoute);
            }
            temperature *= 1.0 - coolingRate;
        }
        endTime = System.nanoTime();
        System.out.println("Mean: "+mean/computations);
        double stdDeviation = Math.sqrt((sqrSum-(Math.pow(sum,2.0)/(double)computations))/(double)computations);
        System.out.println("STD Deviation: " + stdDeviation);
        return (endTime-startTime)/1000000000.0;
    }
    public double acceptanceProbability(double currentEnergy, double newEnergy) {
        if(newEnergy < currentEnergy)
            return 1.0;
        return Math.exp((currentEnergy - newEnergy) / temperature);
    }
    public static void main(String[] args) {
        String fileName = "data\\TSP.txt";
        if(args.length == 1) {
            fileName = args[0].trim();
        }
        BlacksmithSalesman man = new BlacksmithSalesman(Salesman.getFromFile(fileName));
        man.updateRoute(man.bestRoute);
        System.out.println("Took: "+man.compute() + " seconds");
        System.out.println("\t   "+man.computations + " loops");
        man.updateRoute(man.bestRoute);
        System.out.println("Best Route: "+man.bestRoute + " " + man.bestRoute.getDistance());
        System.out.println("Worst Route: "+man.worstRoute + " " + man.worstRoute.getDistance());
    }

}
