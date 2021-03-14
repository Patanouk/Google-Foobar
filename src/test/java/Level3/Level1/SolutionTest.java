package Level3.Level1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {

    @Test
    void solutionTest() {
        assertEquals("1", Solution.solution("2", "1"));
        assertEquals("4", Solution.solution("4", "7"));

        assertEquals("7", Solution.solution("4", "17"));

        assertEquals("999999999999", Solution.solution("1", "1000000000000"));

        assertEquals("impossible", Solution.solution("0", "0"));
        assertEquals("impossible", Solution.solution("2", "4"));
    }
}