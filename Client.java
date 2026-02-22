// ALEC HUANG
// CSE 123
// 02/22/26
// P2
// TA: ISHTA MUNDRA

import java.util.*;

// A client class for creating scenarios (list of regions) and allocating relief to said regions.
public class Client {
    private static final Random RAND = new Random();

    public static void main(String[] args) throws Exception {
        // List<Region> scenario = createRandomScenario(10, 10, 100, 1000, 100000);
        List<Region> scenario = createSimpleScenario();
        System.out.println(scenario);
        
        double budget = 2000;
        Allocation allocation = allocateRelief(budget, scenario);
        printResult(allocation, budget);
    }

    // BEHAVIOR: Returns the optimal allocation of relief funds across the given region
    //           whilst maximizing the amount of people helped. If regions have the same people
    //           the one with the lower cost is prefered.
    // EXCEPTIONS: IllegalArgumentException if
    //              sites is null
    // RETURNS: Allocation that contains the best set of regions to send relief to.
    // PARAMETERS: double budget: budget to spend for relief
    //             List<Region> sites: the list of sites that need relief.
    public static Allocation allocateRelief(double budget, List<Region> sites) {
        if (sites == null) {
            throw new IllegalArgumentException();
        }

        Allocation currAllocation = new Allocation();
        int index = 0;
        if (sites.isEmpty() || budget == 0) {
            return currAllocation;
        }

        currAllocation = recursiveHelper(sites, index, budget, currAllocation);
        return currAllocation;
    }

    // BEHAVIOR: Recursively explores different possible allocations from the given regions
    //           and returns the most optimal allocation from current position.
    //           returned allocation maximizes people helped with remaining budget.
    // EXCEPTIONS: none
    // RETURNS: returns the best allocation achievable from current index forward.
    // PARAMETERS: List<Region> sites: the list of sites to be considered
    //             int index: current position in list
    //             double remainingBudget: remaining budget to spend for relief
    //             Allocation currAllocation: allocation made so far
    private static Allocation recursiveHelper(List<Region> sites, int index, 
                                            double remainingBudget, Allocation currAllocation) {
        if (index == sites.size()) {
            return currAllocation;
        }

        Region region = sites.get(index);
        Allocation excludeResult = recursiveHelper(sites, index + 1, remainingBudget,
                                                                                 currAllocation);
        Allocation newAllocation = currAllocation.withRegion(region);

        if (region.getCost() <= remainingBudget) {
            Allocation includeResult = recursiveHelper(sites, index + 1, 
                                            remainingBudget - region.getCost(), newAllocation);

            if (excludeResult.totalPeople() > includeResult.totalPeople()) {
                return excludeResult;

            } else if (excludeResult.totalPeople() < includeResult.totalPeople()) {
                return includeResult;

            } else if (excludeResult.totalPeople() == includeResult.totalPeople()) {

                if (excludeResult.totalCost() < includeResult.totalCost()) {
                    return excludeResult;

                } else {
                    return includeResult;
                }
            }

        }
        return excludeResult;
    }

    ///////////////////////////////////////////////////////////////////////////
    // PROVIDED HELPER METHODS - **DO NOT MODIFY ANYTHING BELOW THIS LINE!** //
    ///////////////////////////////////////////////////////////////////////////
    
    /**
    * Prints each allocation in the provided set. Useful for getting a quick overview
    * of all allocations currently in the system.
    * @param allocations Set of allocations to print
    */
    public static void printAllocations(Set<Allocation> allocations) {
        System.out.println("All Allocations:");
        for (Allocation a : allocations) {
            System.out.println("  " + a);
        }
    }

    /**
    * Prints details about a specific allocation result, including the total people
    * helped, total cost, and any leftover budget. Handy for checking if we're
    * within budget limits!
    * @param alloc The allocation to print
    * @param budget The budget to compare against
    */
    public static void printResult(Allocation alloc, double budget) {
        System.out.println("Result: ");
        System.out.println("  " + alloc);
        System.out.println("  People helped: " + alloc.totalPeople());
        System.out.printf("  Cost: $%.2f\n", alloc.totalCost());
        System.out.printf("  Unused budget: $%.2f\n", (budget - alloc.totalCost()));
    }

    /**
    * Creates a scenario with numRegions regions by randomly choosing the population 
    * and cost of each region.
    * @param numRegions Number of regions to create
    * @param minPop Minimum population per region
    * @param maxPop Maximum population per region
    * @param minCostPer Minimum cost per person
    * @param maxCostPer Maximum cost per person
    * @return A list of randomly generated regions
    */
    public static List<Region> createRandomScenario(int numRegions, int minPop, int maxPop,
                                                    double minCostPer, double maxCostPer) {
        List<Region> result = new ArrayList<>();

        for (int i = 0; i < numRegions; i++) {
            int pop = RAND.nextInt(maxPop - minPop + 1) + minPop;
            double cost = (RAND.nextDouble(maxCostPer - minCostPer) + minCostPer) * pop;
            result.add(new Region("Region #" + i, pop, round2(cost)));
        }

        return result;
    }

    /**
    * Manually creates a simple list of regions to represent a known scenario.
    * @return A simple list of regions
    */
    public static List<Region> createSimpleScenario() {
        List<Region> result = new ArrayList<>();

        result.add(new Region("Region #1", 50, 500));
        result.add(new Region("Region #2", 100, 700));
        result.add(new Region("Region #3", 60, 1000));
        result.add(new Region("Region #4", 20, 1000));
        result.add(new Region("Region #5", 200, 900));

        return result;
    }    

    /**
    * Rounds a number to two decimal places.
    * @param num The number to round
    * @return The number rounded to two decimal places
    */
    private static double round2(double num) {
        return Math.round(num * 100) / 100.0;
    }
}
