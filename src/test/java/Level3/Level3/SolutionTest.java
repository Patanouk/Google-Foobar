package Level3.Level3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {

    @Test
    void solutionTest() {
        assertEquals(0, Solution.solution("1"));
        assertEquals(1, Solution.solution("2"));
        assertEquals(2, Solution.solution("3"));
        assertEquals(2, Solution.solution("4"));

        assertEquals(3, Solution.solution("5"));
        assertEquals(5, Solution.solution("15"));
        assertEquals(7, Solution.solution("27"));
        assertEquals(6, Solution.solution("31"));
    }
}