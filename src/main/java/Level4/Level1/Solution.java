package Level4.Level1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    /**
     * We know that if we take (num_required - 1) bunnies, we cannot open the door
     * We know that if we take num_required bunnies, we can open the door
     * <p>
     * Hence, if we take (num_required - 1) bunnies, the remaining (num_buns - num_required + 1) must all have an
     * unique key
     * <p>
     * This holds true for all the possible groups of (num_required - 1 bunnies)
     * Hence, each possible (num_buns - num_required + 1) group of bunnies have a unique key
     * <p>
     * We can generate all the possible groups of size (num_buns - num_required + 1) with num_buns max bunnies
     * We can then add a unique key to each group, starting from 0 and increasing the key number by 1 each time
     * Assuming the combinations are sorted correctly, we will get the correct order for the keys
     *
     * @param num_buns The number of available bunnies
     * @param num_required The minimum number of needed bunnies to open the door
     * @return The lexicographically least such key distribution
     */
    public static int[][] solution(int num_buns, int num_required) {
        if (num_required > num_buns || num_required == 0) {
            return new int[0][0];
        }

        Map<Integer, List<Integer>> bunnyToListOfKey = new HashMap<>();

        int keyNumber = 0;
        //As said above, each combination group must have a unique key
        for (List<Integer> combinations : combinations(num_buns, num_buns - num_required + 1)) {
            for (Integer bunnyNumber : combinations) {
                bunnyToListOfKey.computeIfAbsent(bunnyNumber, key -> new ArrayList<>())
                        .add(keyNumber);
            }

            keyNumber++;
        }

        int[][] result = new int[num_buns][];

        bunnyToListOfKey.forEach((bunnyNumber, listKey) ->
                result[bunnyNumber] = listKey.stream().mapToInt(i -> i).toArray());
        return result;
    }

    /**
     * @param n An integer
     * @param k An integer < n
     * @return The list of all possible combinations of k elements chosen from n
     */
    private static List<List<Integer>> combinations(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        combinations(result, n, k, 0, new Integer[k]);
        return result;
    }

    private static List<List<Integer>> combinations(List<List<Integer>> result, int n, int k, int startPosition, Integer[] temp) {
        if (k == 0) {
            result.add(Arrays.asList(temp.clone()));
            return result;
        }

        for (int i = startPosition; i <= n - k; i++) {
            temp[temp.length - k] = i;
            result = combinations(result, n, k - 1, i + 1, temp);
        }

        return result;
    }
}
