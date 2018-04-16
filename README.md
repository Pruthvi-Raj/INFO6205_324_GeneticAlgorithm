<<<<<<< Updated upstream
# Knapsack-GeneticAlgorithm

# Problem statement
Solving Knapsack problem using Genetic Algorithm. Finding the best fitness of all generations and best population


# Description
Knapsack problem is based on the user input for the number of items and their corresponding weights and values. Given the knapsack capacity with the maximum number of generations, population size and cross over probability we can start solving the problem.


# Implementation
Once the required inputs are given,number of population are generated equal to the population size. Once, we have all the population for the initial generation we evaluate all of them by calculating the fitness. 

Fitness function -- The fitness function is something which sums the weights of each member of population and also sums up the value. It compares the total weight of each population with the Knapsack capacity and if knapsack capacity is greater the the sum of the values previously calculated is displayed as the fitness else if the knapsack capacity is less than the total weight of population the fitness is displayed as '0'.

We also made sure that the algorithm doesn't run for multiple instance even when there is no scope for increase in the fitness. That is if three consecutive generations have same mean fitness value we stop the algorithm from running multiple times which is a waste of time and memory.

After the initial generation we move on to make further generations and breed population and again calculate the fitness of that generations new population and get the best FITNESS value and store it in an ArrayList and we repeat this process till the maximum number of generations are reached or if "mean fitness value" is reached, which ever comes first. 

In the process of making the new population in the new generation we cross over two of the parent population with a given cross over probablity. A random number is generated to select random spots in the parent gene to perform the cross over and the resultant new breed is created. And the rest of the steps are repeated to obtain fitness and evaluated.

And finally we get the resultant list of best fitness values from all the generations and of these the best resultant fitness is obtained.

![Output Result](INFO6205_324_GeneticAlgorithm/Output.png)



# Team Mates:
1. Pruthvi Raj Muddapuram
2. Vineeth Kashyap Kosiganti
=======
# Knapsack-GeneticAlgorithm

# Problem statement
Solving Knapsack problem using Genetic Algorithm. Finding the best fitness of all generations and best population


# Description
Knapsack problem is based on the user input for the number of items and their corresponding weights and values. Given the knapsack capacity with the maximum number of generations, population size and cross over probability we can start solving the problem.


# Implementation
Once the required inputs are given,number of population are generated equal to the population size. Once, we have all the population for the initial generation we evaluate all of them by calculating the fitness. 

Fitness function -- The fitness function is something which sums the weights of each member of population and also sums up the value. It compares the total weight of each population with the Knapsack capacity and if knapsack capacity is greater the the sum of the values previously calculated is displayed as the fitness else if the knapsack capacity is less than the total weight of population the fitness is displayed as '0'.

We also made sure that the algorithm doesn't run for multiple instance even when there is no scope for increase in the fitness. That is if three consecutive generations have same mean fitness value we stop the algorithm from running multiple times which is a waste of time and memory.

After the initial generation we move on to make further generations and breed population and again calculate the fitness of that generations new population and get the best FITNESS value and store it in an ArrayList and we repeat this process till the maximum number of generations are reached or if "mean fitness value" is reached, which ever comes first. 

In the process of making the new population in the new generation we cross over two of the parent population with a given cross over probablity. A random number is generated to select random spots in the parent gene to perform the cross over and the resultant new breed is created. And the rest of the steps are repeated to obtain fitness and evaluated.

And finally we get the resultant list of best fitness values from all the generations and of these the best resultant fitness is obtained.




# Team Mates:
1. Pruthvi Raj Muddapuram
2. Vineeth Kashyap Kosiganti

>>>>>>> Stashed changes
