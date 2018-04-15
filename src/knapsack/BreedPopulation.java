/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knapsack;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author aravind
 */
public class BreedPopulation {
    

    private int population_size = 0;
    private double total_fitness_of_generation;
    private ArrayList<String> breed_population = new ArrayList<String>();
    private ArrayList<Double> fitness;
    private Evaluation eval;
    private int crossover_count = 0;
    private int clone_count = 0;
    
    protected ArrayList<String> breedNewPopulation(Evaluation eval, ArrayList<Double> fitness, Double prob_crossover, int population_size, 
            int number_of_items, ArrayList<String> population, ArrayList<String> best_solution_of_generation, int generation_counter) {

        // 2 genes for breeding
        this.eval = eval;
        this.fitness = fitness;
        this.population_size = population_size;
        int gene_1;
        int gene_2;

        

        // If population_size is odd #, use elitism to clone best solution of previous generation
        if(population_size % 2 == 1) {
            this.breed_population.add(best_solution_of_generation.get(generation_counter - 1));
        }

        // Get positions of pair of genes for breeding
        gene_1 = selectGene(eval,fitness, population);
        gene_2 = selectGene(eval,fitness, population);
        
        // Crossover or cloning
        crossoverGenes(gene_1, gene_2, prob_crossover, number_of_items, population);
        
        return this.breed_population;
    }
    
    private int selectGene(Evaluation eval, ArrayList<Double> fitness, ArrayList<String> population) {
        
        // Generate random number between 0 and total_fitness_of_generation
        double rand = Math.random() * evalBreedPopulation(population);
        
        // Use random number to select gene based on fitness level
        for(int i = 0; i < population_size; i++) {
            if(rand <= fitness.get(i)) {
                return i;
            }
            rand = rand - fitness.get(i);
        }

	// Not reachable; default return value
	return 0;
    }
    
    private void crossoverGenes(int gene_1, int gene_2,Double prob_crossover, int number_of_items, ArrayList<String> population) {
      
        // Strings to hold new genes
        String new_gene_1;
        String new_gene_2;

        // Decide if crossover is to be used
        double rand_crossover = Math.random();
        if(rand_crossover <= prob_crossover) {
            // Perform crossover
            crossover_count = crossover_count + 1;
            Random generator = new Random(); 
            int cross_point = generator.nextInt(number_of_items-1) + 1;

            // Cross genes at random spot in strings
            new_gene_1 = population.get(gene_1).substring(0, cross_point) + population.get(gene_2).substring(cross_point);
            new_gene_2 = population.get(gene_2).substring(0, cross_point) + population.get(gene_1).substring(cross_point);

            // Add new genes to breed_population
            this.breed_population.add(new_gene_1);
            this.breed_population.add(new_gene_2);
        }
        else {
            // Otherwise, perform cloning
            clone_count = clone_count + 1;
            this.breed_population.add(population.get(gene_1));
            this.breed_population.add(population.get(gene_2));
        }

        // Check if mutation is to be performed
        //mutateGene();
    }
    
    protected int cloning(){
        return clone_count;
    }
    
    //Evaluation eval = new Evaluation(population_size, breed_population, clone_count, fitness, fitness, total_fitness_of_generation);
    
    protected Double evalBreedPopulation(ArrayList<String> population) {
        total_fitness_of_generation = 0;
        for(int i = 0; i < population_size; i++) {
            double temp_fitness = eval.evalGene(population.get(i));
            fitness.add(temp_fitness);
            total_fitness_of_generation = total_fitness_of_generation + temp_fitness;
        }
        return total_fitness_of_generation;
    }
}
