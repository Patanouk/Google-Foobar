package Level2.Level1;

public class Solution {

    /**
     * We need to find a way to maximize and minimize the number of henchman
     * @param total_lambs The total number of lambs to be distributed
     * @return The difference between the maximum and minimum number of henchmen who can share the LAMBs
     */
    public static int solution(int total_lambs) {
        if (total_lambs == 0) {
            return 0;
        }

        return maxHenchman(total_lambs) - minHenchman(total_lambs);
    }

    /**
     * The minimum number of lambs we need to give is the sum of the last two henchmans
     * E.g. 1 - 1 - 2 - 3 - 5 - 8 - 13 - 21
     * This is the Fibonacci Sequence
     *
     * So we want the the sum of Fibonacci sequence up to n such that the sum is smaller than total lambs
     * The sum of the n first Fibonacci numbers is fibonacci(n + 2) - 1
     * @param total_lambs The number of lambs to distribute
     * @return
     */
    static int maxHenchman(int total_lambs) {
        final int closestFibonacci = closestFibonacci(total_lambs + 1);
        if (fibonacci(closestFibonacci) <= total_lambs + 1) {
            return closestFibonacci - 2;
        } else {
            return closestFibonacci - 2 - 1;
        }
    }

    /**
     * https://stackoverflow.com/questions/7843048/finding-the-closest-fibonacci-numbers
     * We still need to check if the fibonacci number is below or above the target
     *
     * @param target The target number
     * @return The nth number such that fibonacci(n) is the closest fibonacci number to the target
     */
    static int closestFibonacci(int target) {
        if (target < 2) {
            return target;
        }

        final double phi = (1 + Math.sqrt(5)) / 2;
        return (int) (Math.round(Math.log(target * Math.sqrt(5)) / Math.log(phi)));
    }

    static int fibonacci(int n) {
        if (n == 0) {
            return 0;
        }

        if (n == 1 || n == 2) {
            return 1;
        }

        if (n % 2 == 0) {
            int k = n / 2;
            return (2 * fibonacci(k - 1) + fibonacci(k)) * fibonacci(k);
        } else {
            int k = (n + 1) / 2;
            return fibonacci(k) * fibonacci(k) + fibonacci(k - 1) * fibonacci(k - 1);
        }
    }

    /**
     * We can at most double the number of coins each times
     * E.g. 1 - 2 - 4 - 8 - 16 = sum(2^n)
     * Assuming 18 coins :
     * 1 + 2 + 4 + 8 = 15 -> 3 coins left. Cannot pay another
     *
     * Let's find n such as sum(2^p) for p in [0..n] <= total_lambs
     * We know that such a sum is equal to 2^(n+1) - 1
     *
     * We then need to see if the reminder of lambs is enough to add another henchman
     *
     * @param total_lambs The number of lambs to distribute
     * @return The minimum number of Henchman we can distribute the coin to (e.g. as generous as possible)
     */
    static int minHenchman(int total_lambs) {
        //The max sum of power of 2 below the number of lambs
        final int maxPowerof2 = highestPowerof2Factor(total_lambs + 1) - 1;
        return maxPowerof2 + 1;


// The code below was catering to the case were we cannot double the amount of coin, but can still pay
// an additional henchman if we have more than the sum of last two henchmans left
// Was that corner case overlooked, or did I miss something here?

//        //We can have an additional henchman, if we have enough to give the sum of last two henchman
//        final int lambRemainder = total_lambs - (int) Math.pow(2, maxPowerof2 + 1) + 1;
//        final int lastTwoHenchmanCoins = (int) Math.pow(2, maxPowerof2) + (int) Math.pow(2, maxPowerof2 - 1);
//
//        if (lambRemainder >= lastTwoHenchmanCoins) {
//            return minNumberOfHenchman + 1;
//        } else {
//            return minNumberOfHenchman;
//        }
    }

    /**
     *
     * @param n An integer
     * @return An integer such that 2^p <= n && 2^(p+1) > n
     */
    static int highestPowerof2Factor(int n) {
        return (int) (Math.log(n) / Math.log(2));
    }
}
