package Salesman;

import utility.City;
import utility.Route;

import java.util.Arrays;
import java.util.Random;

public class GeneticSalesman extends Salesman {
    private final int POPULATION_SIZE = 200;
    private final double MUTATION_RATE = 0.01;
    private double target;
    private int generations,lastChanged;
    private Random rand;
    private Route[] routes;
    public GeneticSalesman(City[] cities, boolean show,double target) {
        super(cities,show);
        generateInitialRoutes();
        this.target = target;
        rand = new Random();
    }
    public GeneticSalesman(City[] cities,boolean show) {
        this(cities,show,.27);
    }
    public GeneticSalesman(City[] cities,double target) {
        this(cities,true,target);
    }
    public GeneticSalesman(City[] cities) {
        this(cities,true);
    }
    private void generateInitialRoutes() {
        routes = new Route[POPULATION_SIZE];
        for(int i = 0; i < POPULATION_SIZE; i++) {
            Route r = new Route(shuffleArray(cities,false));
            routes[i] = r;
        }
    }
    public double compute() {
        computations = 0;
        mean = 0;
        sum = 0;
        sqrSum = 0;
        generations = 0;
        startTime = System.nanoTime();
        while(bestFitness < target) {
            evolve();
        }
        endTime = System.nanoTime();
        double stdDeviation = Math.sqrt((sqrSum-(Math.pow(sum,2.0)/(double)computations))/(double)computations);
        System.out.println("STD Deviation: " + stdDeviation);
        System.out.println("Computations: "+computations);
        System.out.println("Generations: "+generations);
        return (endTime-startTime)/1000000000.0;
    }
    private void analyzeRoutes() {
        for(Route route : routes)
            compareRoute(route);
    }
    public void updateRoute(Route route) {
        super.updateRoute(route);
        lastChanged = generations;
    }
    private void evolve() {
        generations++;
        Route[] newRoutes = new Route[routes.length];
        newRoutes[0] = bestRoute;
        for(int i = 1; i < routes.length; i++) {
            Route[] parents = selectParents();
            Route child = createChild(parents[0],parents[1]);
            newRoutes[i] = mutate(child);
        }
        routes = newRoutes;
        analyzeRoutes();
    }
    private Route[] selectParents() {
        Route father;
        Route mother;
        double total = 0.0;
        for(Route r : routes) {
            total += r.getFitness();
        }
        //father
        double target = rand.nextDouble()*total;
        double current = 0.0;
        int i = 0;
        while(current < target) {
            if(i == routes.length)
                i = 0;
            current += routes[i].getFitness();
            i++;
        }
        i--;
        if(i < 0)
            i = routes.length-1;
        father = routes[i];
        //mother
        target = rand.nextDouble()*total;
        current = 0.0;
        i = 0;
        while(current < target) {
            if(i == routes.length)
                i = 0;
            current += routes[i].getFitness();
            i++;
        }
        i--;
        if(i < 0)
            i = routes.length-1;
        mother = routes[i];
        return new Route[] {father, mother};
    }
    private Route createChild(Route father, Route mother) {
        City[] arr = new City[cities.length];
        int start = (int)(rand.nextDouble()*cities.length);
        int end = (int)(rand.nextDouble()*cities.length);
        while(end <= start) {
            end = (int)(rand.nextDouble()*cities.length);
            start = (int)(rand.nextDouble()*cities.length);
        }
        for(int i = 0; i < cities.length; i++) {
            if(i > start && i < end) {
                arr[i] = father.getCities()[i];
            }
        }
        for(City c : mother.getCities()) {
            if(!Arrays.asList(arr).contains(c)) {
                for(int i = 0; i < arr.length; i++) {
                    if(arr[i] == null) {
                        arr[i] = c;
                        break;
                    }
                }
            }
        }
        return new Route(arr);
    }
    private Route mutate(Route route) {
        for(int i = 0; i < route.getCities().length; i++) {
            if(rand.nextDouble() < MUTATION_RATE) {
                int y = (int)(rand.nextDouble()*route.getCities().length);
                City temp = route.getCities()[i];
                route.getCities()[i] = route.getCities()[y];
                route.getCities()[y] = temp;
            }
        }
        return route;
    }
    public Route getBestRoute() {
        Route result = routes[0];
        for(Route r : routes) {
            if(r.getDistance() < result.getDistance()) {
                result = r;
            }
        }
        return result;
    }
    public Route getWorstRoute() {
        Route result = routes[0];
        for(Route r : routes) {
            if(r.getDistance() > result.getDistance()) {
                result = r;
            }
        }
        return result;
    }
    public double getAverageFitness() {
        double avg = 0.0;
        for(Route r : routes) {
            avg += r.getFitness();
        }
        return avg / routes.length;
    }
    public double getAverageDistance() {
        double avg = 0.0;
        for(Route r : routes) {
            avg += r.getDistance();
        }
        return avg / routes.length;
    }
    public static void main(String[] args) {
        String fileName = "data\\TSP.txt";
        if(args.length == 1) {
            fileName = args[0].trim();
        }
        GeneticSalesman man = new GeneticSalesman(Salesman.getFromFile(fileName), false);
        man.updateRoute(man.bestRoute);
        System.out.println("Took: " + man.compute() + " seconds");
        man.updateRoute(man.bestRoute);
        System.out.println("Best Route: " + man.bestRoute + " " + man.bestRoute.getDistance());
        System.out.println("Worst Route: " + man.worstRoute + " " + man.worstRoute.getDistance());
    }
}