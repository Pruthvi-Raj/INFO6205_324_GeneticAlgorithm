/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knapsack;

import java.util.ArrayList;

import knapsack.Population;

/**
 *
 * @author pruth
 */
public class Evaluation {
    
    private ArrayList<Double> fitness = new ArrayList<Double>();
    private ArrayList<String> breed_population = new ArrayList<String>();
     
    private double total_fitness_of_generation = 0;
    private int population_size;
    private ArrayList<String> population;
    int number_of_items;
    private ArrayList<Double> value_of_items;
    private ArrayList<Double> weight_of_items;
    private Double knapsack_capacity;
    
    
    public Evaluation(int population_size,ArrayList<String> population,int number_of_items,
            ArrayList<Double> weight_of_items, ArrayList<Double> value_of_items,Double knapsack_capacity){
        this.population_size = population_size;
        this.population = population;
        this.number_of_items = number_of_items;
        this.weight_of_items = weight_of_items;
        this.value_of_items = value_of_items;
        this.knapsack_capacity = knapsack_capacity;
    }
    
     protected ArrayList<Double> evalPopulation() { 
        this.fitness.clear();
        total_fitness_of_generation = 0;
        for(int i = 0; i < population_size; i++) {
            double temp_fitness = evalGene(population.get(i));
            this.fitness.add(temp_fitness);
            total_fitness_of_generation = total_fitness_of_generation + temp_fitness;
        }
        return this.fitness;
    }
    
     protected double evalGene(String gene) {
        double total_weight = 0;
        double total_value = 0;
        double fitness_value = 0;
        double difference = 0;
        char c = '0';

        // Get total_weight associated with items selected by this gene
        for(int j = 0; j < number_of_items; j ++) {
            c = gene.charAt(j);
            // If chromosome is a '1', add corresponding item position's
            // weight to total weight
            if(c == '1') {
                total_weight = total_weight + weight_of_items.get(j);
                total_value = total_value + value_of_items.get(j);
            }
        }
        // Check if gene's total weight is less than knapsack capacity
        difference = knapsack_capacity - total_weight;
        if(difference >= 0) {
            // This is acceptable; calculate a fitness_value
            // Otherwise, fitness_value remains 0 (default), since knapsack
            // cannot hold all items selected by gene
            // Fitness value is simply total value of acceptable permutation,
            // and for unacceptable permutation is set to '0'
            fitness_value = total_value;
        }
        
        // Return fitness value
        return fitness_value;
    }
     
     
     protected int getBestSolution() {
        int best_position = 0;
        double this_fitness = 0;
        double best_fitness = 0;
        for(int i = 0; i < population_size; i++) {
            this_fitness = evalGene(population.get(i));
            if(this_fitness > best_fitness) {
                best_fitness = this_fitness;
                best_position = i;
            }
        }
        return best_position;
    }
     
     protected double getMeanFitness(ArrayList<Double> fit) {
        double total_fitness = 0;
   	double mean_fitness = 0;
        this.fitness = fit;
        for(int i = 0; i < population_size; i++) {
	    total_fitness = total_fitness + this.fitness.get(i);
        }
	mean_fitness = total_fitness / population_size;
	return mean_fitness;
    }
       
      
          
//     protected Double evalBreedPopulation(ArrayList<String> breed_population) {
//        total_fitness_of_generation = 0;
//        for(int i = 0; i < population_size/2; i++) {
//            double temp_fitness = evalGene(breed_population.get(i));
//            fitness.add(temp_fitness);
//            total_fitness_of_generation = total_fitness_of_generation + temp_fitness;
//        }
//        return total_fitness_of_generation;
//    }
     
     protected ArrayList<Double> evalBreedFitness(){
         return this.fitness;
     }
}
