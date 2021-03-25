package Level4.Level2;

import org.junit.jupiter.api.Test;

import static Level4.Level2.Solution.solution;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {


    @Test
    void testSolution() {
        assertEquals(7, solution(new int[]{3, 2}, new int[]{1, 1}, new int[]{2, 1}, 4));
        assertEquals(9, solution(new int[]{300, 275}, new int[]{150, 150}, new int[]{185, 100}, 500));
        assertEquals(27, solution(new int[]{2, 5}, new int[]{1, 2}, new int[]{1, 4}, 11));
    }
}