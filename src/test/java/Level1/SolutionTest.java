package Level1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class SolutionTest {

    @Test
    void solutionTest() {
        assertArrayEquals(new int[]{}, Solution.solution(new int[]{1, 2, 3}, 0));
        assertArrayEquals(new int[]{}, Solution.solution(null, 0));
        assertArrayEquals(new int[]{1, 4}, Solution.solution(new int[]{1, 2, 2, 3, 3, 3, 4, 5, 5}, 1));
        assertArrayEquals(new int[]{5, 15, 7}, Solution.solution(new int[]{5, 10, 15, 10, 7}, 1));
    }
}