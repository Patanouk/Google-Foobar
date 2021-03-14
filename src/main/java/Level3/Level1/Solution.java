package Level3.Level1;

import java.math.BigInteger;

public class Solution {

    /**
     *
     * Assuming starting coordinates of (a,b) for a given generation
     * Each generation, we can either increment a by b or b by a
     * E.g. next generation is either (a + b, b) or (a, b + a)
     *
     * Now that we saw this, we can now see how to backtrack
     * Given generation n + 1 is either (a + b, b) or (a, b + a) (doesn't matter anyway, it's symmetrical)
     * We can go back from gen n + 1 to gen n by removing the smaller number from the biggest one
     *
     * We can optimise the backtracking as well. Instead of backtracking only once
     * Assuming (4,17) as position
     * We go (4, 17) -> (4, 13) -> (4, 9) -> (4, 5) -> (4, 1)
     * Instead we can do :
     * (4, 17) -> (4, 17 % 4) with number of generations equal to quotient(17, 4)
     *
     * We can then keep backtracking
     * If we reach (1,1) -> The position can be reached
     * If we backtrack too much (e.g. 0 or negative numbers) -> The position cannot be reached
     *
     * @param x The coordinate x we want to attain. Up to 10^50
     * @param y The coordinate y we want to attain. Up to 10^50
     * @return Return the fewest number of generations (as a string) that need to pass before we reach the coordinates
     */
    public static String solution(String x, String y) {
        //Biginteger, as input goes up to 10^50
        BigInteger xInt = new BigInteger(x);
        BigInteger yInt = new BigInteger(y);

        BigInteger numberGenerations = BigInteger.valueOf(0);

        while (xInt.compareTo(BigInteger.ONE) > 0 && yInt.compareTo(BigInteger.ONE) > 0) {

            if (xInt.compareTo(yInt) > 0) {
                final BigInteger[] divideAndRemainder = xInt.divideAndRemainder(yInt);

                numberGenerations = numberGenerations.add(divideAndRemainder[0]);
                xInt = divideAndRemainder[1];
            } else {
                BigInteger[] divideAndRemainder = yInt.divideAndRemainder(xInt);

                numberGenerations = numberGenerations.add(divideAndRemainder[0]);
                yInt = divideAndRemainder[1];
            }
        }

        //If one of the number is 1, we need to backtrack (other_number - 1) generations, as we substract 1 each time
        if (xInt.compareTo(BigInteger.ONE) == 0 || yInt.compareTo(BigInteger.ONE) == 0) {
            return numberGenerations.add(xInt.max(yInt))
                    .add(BigInteger.valueOf(-1))
                    .toString();
        }

        return "impossible";
    }
}
