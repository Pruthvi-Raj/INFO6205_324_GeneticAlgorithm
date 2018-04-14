/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knapsack;

import java.util.ArrayList;

/**
 *
 * @author aravind
 */
public class Population {
    private ArrayList<String> population = new ArrayList<String>();
    int population_size;
    int number_of_items;
    
    public Population(int population_size, int number_of_items){
        this.population_size = population_size;
        this.number_of_items = number_of_items;
    }
    
    protected ArrayList<String> makePopulation() {
        for(int i = 0; i < population_size; i++) {
            // Calls makeGene() once for each element position
            population.add(makeGene());      
        }
        
        System.out.println("\nInitial Generation:");
        System.out.println("===================");
        System.out.println("Population:");
        for(int i = 0; i < this.population_size; i++) {
            System.out.println((i + 1) + " - " + this.population.get(i));
        }
        return population;
    }
    
    private String makeGene() {

        // Stringbuilder builds gene, one chromosome (1 or 0) at a time
        StringBuilder gene = new StringBuilder(number_of_items);

        // Each chromosome
        char c;

        // Loop creating gene
        for(int i = 0; i < number_of_items; i++) {
            c = '0';
            double rnd = Math.random(); 
            // If random number is greater than 0.5, chromosome is '1'
            // If random number is less than 0.5, chromosome is '0'
            if(rnd > 0.5) {
                c = '1';
            }
            // Append chromosome to gene
            gene.append(c);
        }
        // Stringbuilder object to string; return
        return gene.toString();
    }
    
    
}
