package Level3.Level3;

import java.math.BigInteger;

public class Solution {

    /**
     * We want to divide by 2 as much as possible, as it is the fastest way to 'decrease' the number
     * So, how can we minimize the number of +1 and -1 we need to make?
     *
     * Assuming 2^n <= x < 2^(n+1)
     * We need at minimum n steps to reach
     * n is the (number of bits - 1) of x
     *
     * Let's take the binary representation of the number
     * Assuming the least significant bit is 0, we can divide by 2
     * What if the least significant bit is 1? -> It depends on the second least significant bit
     *
     * 00 -> we can divide by 2
     * 10 -> We can divide by 2
     * 01 -> We can substract one and then divide by 2 twice. If we add, we need to add one twice to be able to divide by 2 twice. Hence faster to subtract
     * 11 -> We can add one and then divide by 2 twice. If we subtract, we can divide once -> we need to substract twice to be able to divide twice. Hence adding 1 is faster
     *
     * 00 -> number % 2 == 0
     * 10 -> number % 2 == 0
     * 01 -> number % 4 == 1
     * 11 -> number % 4 == 3
     *
     * @param x A positive integer as a string (up to 309 digits)
     * @return The minimum number of operations needed to transform the number of pellets to 1
     */
    public static int solution(String x) {
        int count = 0;
        BigInteger xBigInt = new BigInteger(x);

        while (xBigInt.compareTo(BigInteger.ONE) != 0) {
            if (xBigInt.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
                xBigInt = xBigInt.divide(BigInteger.valueOf(2));
            } else if (xBigInt.mod(BigInteger.valueOf(4)).equals(BigInteger.ONE) || xBigInt.equals(BigInteger.valueOf(3))) {
                xBigInt = xBigInt.subtract(BigInteger.ONE);
            } else { // xBigInt.mod(4) == 3
                xBigInt = xBigInt.add(BigInteger.ONE);
            }

            count++;
        }
        return count;
    }
}
