package Level2.Level2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {

    @Test
    void sumIntegerUpToTest() {
        assertEquals(3, Solution.sumIntegerUpTo(2));
        assertEquals(6, Solution.sumIntegerUpTo(3));
        assertEquals(10, Solution.sumIntegerUpTo(4));
        assertEquals(5000050000L, Solution.sumIntegerUpTo(100000));
    }

    @Test
    void solutionTest() {
        assertEquals("9", Solution.solution(3, 2));
        assertEquals("96", Solution.solution(5, 10));
    }
}