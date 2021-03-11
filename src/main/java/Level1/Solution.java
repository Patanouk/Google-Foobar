package Level1;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Solution {
    
    public static int[] solution(int[] data, int n) {
        if (data == null || n == 0) {
            return new int[]{};
        }

        final Map<Integer, Long> numberToCount = Arrays.stream(data)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return Arrays.stream(data)
                .filter(number -> numberToCount.get(number) <= n)
                .toArray();
    }
}
