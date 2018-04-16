/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knapsack;

import java.util.ArrayList;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author aravind
 */
public class KnapSackTest {
    
    @Test
    public void test0() {
        Knapsack ks= new Knapsack();
        double bf= ks.showOptimalList();
        
        for(int i=0;i<ks.best_fitness_of_generation.size();i++)
        {
            assertTrue(ks.best_fitness_of_generation.get(i)<=bf);


        }
    }
    
   

    @Test
    public void test1() {
        Evaluation eval= new Evaluation();

        assertTrue(eval.evalGene("0110"));
    }

    @Test
    public void test2() {
        BreedPopulation bp= new BreedPopulation();
        int bf= 1;
        ArrayList<String> arr = new ArrayList<>();
        arr.add("0110");
        arr.add("1001");
        double fitness=bp.evalBreedPopulation(arr);
            assertEquals(bf,fitness);
    }

    @Test
    public void test3() {
        BreedPopulation bp= new BreedPopulation();
        int bf= 1;
        ArrayList<String> arr = new ArrayList<>();
        arr.add("0010");
        arr.add("1101");
        double fitness=bp.evalBreedPopulation(arr);
            assertEquals(bf,fitness);
    }

    @Test
    public void test4() {
         Knapsack ks= new Knapsack();
        double bf= ks.showOptimalList();
        
        for(int i=0;i<ks.best_fitness_of_generation.size();i++)
        {
            assertTrue(ks.best_fitness_of_generation.get(i)<=bf);
        }
    }
    
}
