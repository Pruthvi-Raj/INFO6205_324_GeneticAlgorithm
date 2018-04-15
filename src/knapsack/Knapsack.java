package knapsack;

import java.io.Console;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;


public class Knapsack {

    private boolean verbose = false;
    private boolean mutation = false;
    private int clone_count = 0;
    private int number_of_items = 0;
    private int population_size = 0;
    private int maximum_generations = 0;
    private double knapsack_capacity = 0;
    private double prob_crossover = 0;
    private double prob_mutation = 0;
    private double total_fitness_of_generation = 0;
    private ArrayList<Double> value_of_items;
    private ArrayList<Double> weight_of_items = new ArrayList<>();
    private ArrayList<Double> fitness;
    private ArrayList<Double> best_fitness_of_generation = new ArrayList<>();
    private ArrayList<Double> mean_fitness_of_generation;
    private ArrayList<String> population;
    private ArrayList<String> breed_population = new ArrayList<>();
    private ArrayList<String> best_solution_of_generation = new ArrayList<>();
    private int generation_counter;


    
    public static void main(String[] args) {

        
        if(args.length == 1) {
            try {
                File file_name = new File(args[0]);
                if(file_name.exists()) {
                    file_name.delete();
                }
                FileOutputStream fos = new FileOutputStream(file_name, true);
                PrintStream ps = new PrintStream(fos);
                System.setOut(ps);
            }
            catch(FileNotFoundException e) {
                System.out.println("Problem with output file");
            }
        }
     
        Knapsack knap = new Knapsack();
     
    }

   
    public Knapsack() {
        this.mean_fitness_of_generation = new ArrayList<>();
        this.value_of_items = new ArrayList<>();

        // Get user input
        this.getInput();

        // Make first generation
        this.buildKnapsackProblem();

        // Output summary
        this.showOptimalList();

    }


    public void buildKnapsackProblem() {

        this.generation_counter = 0;
        
        // Generate initial random population (first generation)
        Population pop = new Population(population_size, number_of_items);
        
        population = pop.makePopulation(); // Generating intial population i.e., is first generation

        

        // Evaluate fitness of initial population members
        Evaluation eval = new Evaluation(population_size,population,number_of_items, weight_of_items, value_of_items, knapsack_capacity);
        fitness = eval.evalPopulation();
        
        // Output fitness summary
        System.out.println("\nFitness:");
        for(int i = 0; i < population_size; i++) {
            System.out.println((i + 1) + " - " + this.fitness.get(i));
        }

        // Find best solution of generation
        this.best_solution_of_generation.add(population.get(eval.getBestSolution()));

        // Output best solution of generation
        System.out.println("\nBest solution of initial generation: " + this.best_solution_of_generation.get(0));

        // Find mean solution of generation
	this.mean_fitness_of_generation.add(eval.getMeanFitness(fitness));

	// Output mean solution of generation
	System.out.println("Mean fitness of initial generation: " + this.mean_fitness_of_generation.get(0));

        // Compute fitness of best solution of generation
        this.best_fitness_of_generation.add(eval.evalGene(population.get(eval.getBestSolution())));

        // Output best fitness of generation
        System.out.println("Fitness score of best solution of initial generation: " + this.best_fitness_of_generation.get(0));

        // If maximum_generations > 1, breed further generations
        if(this.maximum_generations > 1) {
            //MakeFurtherGenerations makeFGen = new MakeFurtherGenerations();
            makeFurtherGenerations(eval);
        }

    }


    /**
     * Makes further generations beyond first, if necessary
     */
    private void makeFurtherGenerations(Evaluation eval) {

        // Breeding loops maximum_generation number of times at most
        for(int i = 1; i < this.maximum_generations; i++) {
            // Increase generation_counter
            this.generation_counter = this.generation_counter + 1;
            
	    // Check for stopping criterion
	    if((this.maximum_generations > 4) && (i > 4)) {

		// Previous 3 generational fitness values
		double a = this.mean_fitness_of_generation.get(i - 1);
		double b = this.mean_fitness_of_generation.get(i - 2);
		double c = this.mean_fitness_of_generation.get(i - 3);

		// If all are 3 equal, stop
		if(a == b && b == c) {
		    System.out.println("\nStop criterion met");
		    maximum_generations = i;
		    break;
		}
	    }

            // Reset some counters
            //this.crossover_count = 0;
            this.clone_count = 0;
            this.mutation = false;
          
            BreedPopulation breedNew = new BreedPopulation();
            // Breed population
            for(int j = 0; j < this.population_size / 2; j++) {
               breed_population = breedNew.breedNewPopulation(eval, fitness, prob_crossover, population_size, number_of_items, population, this.best_solution_of_generation, this.generation_counter);
            }
   
            // Clear fitness values of previous generation
            this.fitness.clear();
            eval.evalPopulation();
            // Evaluate fitness of breed population members
            total_fitness_of_generation = breedNew.evalBreedPopulation(breed_population);

            // Copy breed_population to population
            for(int k = 0; k < this.population_size; k++) {
                this.population.set(k, breed_population.get(k));
            }

            // Output population
            System.out.println("\nGeneration " + (i+1) + ":");
            if((i + 1) < 10) {
                System.out.println("=============");
            }
            System.out.println("Population:");
            for(int l = 0; l < this.population_size; l++) {
                System.out.println((l + 1) + " - " + this.population.get(l));
            }

            //fitness summary
            System.out.println("\nFitness:");
            for(int m = 0; m < this.population_size; m++) {
               System.out.println((m + 1) + " - " + eval.evalBreedFitness().get(m));
            } 

            this.breed_population.clear();
            //breedNew = new BreedPopulation();

            //best solution of generation
            this.best_solution_of_generation.add(this.population.get(eval.getBestSolution()));          
            System.out.println("\nBest solution of generation " + (i + 1) + ": " + this.best_solution_of_generation.get(i));

            //mean solution of generation
	    this.mean_fitness_of_generation.add(eval.getMeanFitness(fitness));
	    System.out.println("Mean fitness of generation: " + this.mean_fitness_of_generation.get(i));

            //fitness of best solution of generation
            this.best_fitness_of_generation.add(eval.evalGene(population.get(eval.getBestSolution())));
            System.out.println("Fitness score of best solution of generation " + (i + 1) + ": " + this.best_fitness_of_generation.get(i));

            // Output crossover/cloning summary
            
            System.out.println("Clonning occurred " + breedNew.getClone_count() + " times");
            System.out.println("Crossover occurred " + breedNew.getCrossover_count() + " times");
            System.out.println("Newly added to the  Generation "+ (i+1)+" is "+ breedNew.getNew_evolved_count());
            System.out.println("Discarded from the generation "+ (population_size - breedNew.getNew_evolved_count()));
            if(this.mutation == false) {
                System.out.println("Mutation did not occur");
            }
            if(this.mutation == true) {
                System.out.println("Mutation did occur");
            }
        }
    }

    /**
     * Output KnapsackProblem summary
     */
    private void showOptimalList() {

        // Output optimal list of items
        System.out.println("\nOptimal list of items to include in knapsack: ");

        double best_fitness = 0;
        int best_gen = 0;

        // First, find best solution out of generational bests
        for(int z = 0; z < this.maximum_generations - 1; z++) {
            if(this.best_fitness_of_generation.get(z) > best_fitness) {
                best_fitness = this.best_fitness_of_generation.get(z);
                best_gen = z;
            }
        }

        // Then, go through that's generation's best solution and output items
        String optimal_list = this.best_solution_of_generation.get(best_gen);
        for(int y = 0; y < this.number_of_items; y++) {
            if(optimal_list.substring(y, y + 1).equals("1")) {
                System.out.print((y + 1) + " ");
            }
        }
        System.out.println("Best generation is " + best_gen);
        System.out.println("Best fitness is " + best_fitness);
    }


     // Collects user input to be used as parameters for knapsack problem
    private void getInput() {

        String input;

        // Number of items
        System.out.println("Enter the number of items: ");
        Scanner sc = new Scanner(System.in);
        input = sc.nextLine();
        
        if (isInteger(input)) {
            number_of_items = Integer.parseInt(input);
        }
        else {
            System.out.println("Not a number. Please try again.");
            System.exit(1);
        }

        // Value and weight of each item
        for(int i = 0; i < number_of_items; i++) {
            System.out.println("Enter the value of item " + (i + 1) + ": ");
            input = sc.nextLine();
            if (isDouble(input)) { 
                value_of_items.add(Double.parseDouble(input));
            }
            else {
                System.out.println("Not a number. Please try again.");
                System.exit(1);
            }
            
            System.out.println("Enter the weight of item " + (i + 1) + ": ");
            input = sc.nextLine();
            if (isDouble(input)) {
                weight_of_items.add(Double.parseDouble(input));
            }
            else {
                System.out.println("Not a number. Please try again.");
                System.exit(1);
            }            
        }

        // Capacity of knapsack
        System.out.println("Enter the knapsack capacity: ");
        input = sc.nextLine();
        if (isInteger(input)) {
            knapsack_capacity = Integer.parseInt(input);
        }
        else {
            System.out.println("Not a number. Please try again.");
            System.exit(1);
        }

        // Population size
        System.out.println("Enter the population size: ");
        input = sc.nextLine();
        if (isInteger(input)) {
            population_size = Integer.parseInt(input);
        }
        else {
            System.out.println("Not a number. Please try again.");
            System.exit(1);
        }

        // Maximum number of generations
        System.out.println("Enter the maximum number of generations: ");
        input = sc.nextLine();
        if (isInteger(input)) {
            maximum_generations = Integer.parseInt(input);
        }
        else {
            System.out.println("Not a number. Please try again.");
            System.exit(1);
        }

        // Crossover probability
        System.out.println("Enter the crossover probability: ");
        input = sc.nextLine();
        if (isDouble(input)) {
            prob_crossover = Double.parseDouble(input);
        }
        else {
            System.out.println("Not a number. Please try again.");
            System.exit(1);
        }

        // Mutation probability
        System.out.println("Enter the mutation probability: ");
        input = sc.nextLine();
        if (isDouble(input)) {
            prob_mutation = Double.parseDouble(input);
        }
        else {
            System.out.println("Not a number. Please try again.");
            System.exit(1);
        }
    }


    public static boolean isInteger(String str) {
        try { 
            Integer.parseInt(str); 
        } 
        catch(NumberFormatException e) { 
            return false; 
        }
        return true;
    }

    public static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
        }
        catch(NumberFormatException e) {
            return false;
        }
        return true;
    }
}