package Level3.Level2;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Solution {

    //A map containing the previously computed number of possible stairs
    //Useful, as we compute the same values a lot of time
    //The code is running single threaded, hence a normal map is enough
    private static final Map<NumberOfStairs, Integer> numberOfStairsCache= new HashMap<>();

    /**
     *
     * We can build at most maxSteps(n) steps
     * We just sum up the number of stairs we can build with 1, 2, .. , maxSteps(n) steps
     *
     * @param n The number of bricks
     * @return The number of possible stairs we can build with n bricks
     */
    public static int solution(int n) {
        int totalPossibleStairs = 0;
        //We start at 2 steps minimum
        for (int i = 2; i <= maxSteps(n); i++) {
            totalPossibleStairs += numberPossibleStairs(n, i);
        }
        return totalPossibleStairs;
    }

    /**
     *
     * For a stair of nSteps steps, we need at minimum :
     * 1 + 2 + ... + nSteps = (nSteps * (nSteps + 1)) / 2
     *
     * We want nSteps such as
     *
     * (nSteps * (nSteps + 1)) / 2 < n        -> We can build a staircase of size nSteps
     * ((nSteps + 1) * (nSteps + 2)) / 2 > n  -> We cannot build a staircase of size nSteps + 1
     *
     * Let's solve (x * (x + 1)) / 2 = n (quadratic equation)
     * We can take the whole part of the positive solution to have our max nSteps
     *
     * We have two solutions :
     *
     * (-1 + sqrt(1+ 8n)) / 2 && (-1 - sqrt(1+ 8n)) / 2
     * The first solution is the positive one
     *
     * @param n The number of bricks
     * @return The maximum number of steps we can have with n bricks
     */
    static int maxSteps(int n) {
        return (int) ((-1 + Math.sqrt(1 + 8 * n)) / 2);
    }

    /**
     * Assuming n bricks and nSteps steps
     * Each time we add a brick on the first step, we need to add at least a brick on each step
     * So we can have at most n / nSteps bricks for the first step
     *
     * Assuming 0 < k < (n / nSteps)
     * We put k bricks on the first step
     * We then have n - (k * nSteps) bricks left for the remaining (n -1) steps
     *
     * So, the number of stairs with nSteps, n bricks total and k bricks on the first step is :
     *      the number of stairs with (nSteps - 1) steps and n - (k * nSteps) bricks
     *
     * We can them sum up
     *
     * numberPossibleStairs(n, nSteps) =
     *  sum(numberPossibleStairs(n - k * nsteps, nSteps - 1)) for 0 < k < (n / nSteps)
     *
     *
     * @param n The number of bricks we have
     * @param nSteps The number of steps we want to build
     * @return The number of possible staircases with n bricks and nSteps
     */
    static int numberPossibleStairs(int n, int nSteps) {
        if (numberOfStairsCache.containsKey(NumberOfStairs.of(n, nSteps))) {
            return numberOfStairsCache.get(NumberOfStairs.of(n, nSteps));
        }

        int numberPossibleStairs = 0;
        //If we have less brick than the minimum we need for a stair of size nSteps, we stop the recursion
        if (minNumberBricks(nSteps) > n) {
            return 0;
        }

        // We can have at most (n -1) / 2 bricks for the first step
        if (nSteps == 2) {
            return (n - 1) / 2;
        }

        for (int i = 1; i <= n / nSteps; i++) {
            numberPossibleStairs += numberPossibleStairs(n - i * nSteps, nSteps - 1);
        }

        numberOfStairsCache.put(NumberOfStairs.of(n, nSteps), numberPossibleStairs);
        return numberPossibleStairs;
    }

    /**
     * For a stair of nSteps steps, we need at minimum :
     * 1 + 2 + ... + nSteps = (nSteps * (nSteps + 1)) / 2
     *
     * @param nSteps the number of steps for the stair
     * @return The minimum number of bricks to build the stair
     */
    private static int minNumberBricks(int nSteps) {
        return (nSteps * (nSteps + 1)) / 2;
    }

    private static class NumberOfStairs {
        private final int n;
        private final int nSteps;

        public static NumberOfStairs of(int n, int nSteps) {
            return new NumberOfStairs(n, nSteps);
        }

        private NumberOfStairs(int n, int nSteps) {
            this.n = n;
            this.nSteps = nSteps;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            NumberOfStairs that = (NumberOfStairs) o;
            return n == that.n && nSteps == that.nSteps;
        }

        @Override
        public int hashCode() {
            return Objects.hash(n, nSteps);
        }
    }
}
