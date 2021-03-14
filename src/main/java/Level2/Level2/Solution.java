package Level2.Level2;

public class Solution {

    /**
     * | 7
     * | 4 8
     * | 2 5 9
     * | 1 3 6 10
     *
     * We can easily calculate (x, 1) for any x -> It's the xth term of the sum of all the integers
     * E.g. (4,1) = 1 + 2 + 3 + 4
     *
     * From this, we can calculate any (x, y) as :
     * (x, y) = (x + 1, y - 1) - 1
     *
     * Repeating this, we have (x, y) = (x + y - 1, 1) - (y - 1)
     *
     * @param x The distance from the vertical wall
     * @param y The height from the ground
     * @return The worker ID at position (x, y) as a string
     */
    public static String solution(long x, long y) {
        return Long.toString(sumIntegerUpTo(x + y - 1) - (y - 1));
    }

    /**
     * n is up to 100,000, so we're fine with a long
     *
     * @param n The integer up to which we sum
     * @return The sum of integers up to n
     */
    static long sumIntegerUpTo(long n) {
        return (n * (n + 1)) / 2;
    }
}
