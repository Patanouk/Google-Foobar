package Level3.Level2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {

    @Test
    void testNumberPossibleStairs() {
        assertEquals(1, Solution.numberPossibleStairs(3, 2));
        assertEquals(1, Solution.numberPossibleStairs(4, 2));
        assertEquals(2, Solution.numberPossibleStairs(5, 2));
        assertEquals(9, Solution.numberPossibleStairs(20, 2));
        assertEquals(10, Solution.numberPossibleStairs(21, 2));

        assertEquals(1, Solution.numberPossibleStairs(6, 3));
        assertEquals(1, Solution.numberPossibleStairs(7, 3));

        assertEquals(1, Solution.numberPossibleStairs(7, 3));


        assertEquals(4, Solution.numberPossibleStairs(10, 2));
        assertEquals(4, Solution.numberPossibleStairs(10, 3));
        assertEquals(1, Solution.numberPossibleStairs(10, 4));
    }

    @Test
    void testMaxSteps() {
        assertEquals(2, Solution.maxSteps(3));
        assertEquals(2, Solution.maxSteps(4));
        assertEquals(2, Solution.maxSteps(5));
        assertEquals(3, Solution.maxSteps(6));

        assertEquals(3, Solution.maxSteps(9));
        assertEquals(4, Solution.maxSteps(10));
        assertEquals(4, Solution.maxSteps(11));
    }

    @Test
    void testSolution() {
        assertEquals(1, Solution.solution(3));
        assertEquals(2, Solution.solution(5));
        assertEquals(3, Solution.solution(6));

        assertEquals(9, Solution.solution(10));

        assertEquals(487067745, Solution.solution(200));
    }
}