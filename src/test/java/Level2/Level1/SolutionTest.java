package Level2.Level1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {

    @Test
    void highestPowerof2Test() {
        //1 - 1 - 2 - 4 - 8
        // 13 <- Min number of slaves
        //1 - 2 - 4 - 6

        assertEquals(0, Solution.highestPowerof2Factor(1));
        assertEquals(1, Solution.highestPowerof2Factor(2));
        assertEquals(2, Solution.highestPowerof2Factor(4));
        assertEquals(3, Solution.highestPowerof2Factor(8));
        assertEquals(4, Solution.highestPowerof2Factor(16));

        assertEquals(3, Solution.highestPowerof2Factor(9));
        assertEquals(3, Solution.highestPowerof2Factor(10));
        assertEquals(4, Solution.highestPowerof2Factor(20));
    }

    @Test
    void minHenchmanTest() {
        assertEquals(1, Solution.minHenchman(1));
        assertEquals(2, Solution.minHenchman(2));
        assertEquals(3, Solution.minHenchman(7));
        assertEquals(3, Solution.minHenchman(10));
        assertEquals(4, Solution.minHenchman(15));
        assertEquals(5, Solution.minHenchman(31));
        assertEquals(5, Solution.minHenchman(32));
        assertEquals(5, Solution.minHenchman(54));
        assertEquals(6, Solution.minHenchman(55));

        assertEquals(15, Solution.minHenchman(32766));
        assertEquals(15, Solution.minHenchman(32767));
    }

    @Test
    void name() {
        int sum = 0;
        for (int i = 0; i < 15; i++) {
            sum += Math.pow(2, i);
        }
        System.out.println(sum);
    }

    @Test
    void fibonnaciTest() {
        assertEquals(0, Solution.fibonacci(0));
        assertEquals(1, Solution.fibonacci(1));
        assertEquals(1, Solution.fibonacci(2));
        assertEquals(2, Solution.fibonacci(3));
        assertEquals(3, Solution.fibonacci(4));
        assertEquals(5, Solution.fibonacci(5));
        assertEquals(8, Solution.fibonacci(6));
        assertEquals(13, Solution.fibonacci(7));
        assertEquals(21, Solution.fibonacci(8));
        assertEquals(34, Solution.fibonacci(9));

        assertEquals(832040, Solution.fibonacci(30));
        assertEquals(701408733, Solution.fibonacci(44));
    }

    @Test
    void closestFibonnaciTest() {
        assertEquals(3, Solution.closestFibonacci(2));
        assertEquals(4, Solution.closestFibonacci(3));
        assertEquals(7, Solution.closestFibonacci(16));
        assertEquals(9, Solution.closestFibonacci(33));
        assertEquals(9, Solution.closestFibonacci(34));
        assertEquals(9, Solution.closestFibonacci(35));
    }

    @Test
    void maxHenchmanTest() {
        assertEquals(1, Solution.maxHenchman(1));
        assertEquals(2, Solution.maxHenchman(2));
        assertEquals(2, Solution.maxHenchman(3));
        assertEquals(3, Solution.maxHenchman(4));
        assertEquals(3, Solution.maxHenchman(6));
        assertEquals(4, Solution.maxHenchman(7));
        assertEquals(4, Solution.maxHenchman(8));
        assertEquals(4, Solution.maxHenchman(10));
        assertEquals(4, Solution.maxHenchman(11));
        assertEquals(5, Solution.maxHenchman(12));
        assertEquals(5, Solution.maxHenchman(13));
        assertEquals(7, Solution.maxHenchman(34));

        assertEquals(28, Solution.maxHenchman(1000000));
        assertEquals(42, Solution.maxHenchman(1000000000));
    }

    @Test
    void solutionTest() {
        assertEquals(3, Solution.solution(143));
        assertEquals(1, Solution.solution(10));
        assertEquals(0, Solution.solution(1));

        assertEquals(2, Solution.solution(34));
    }
}
