package algs.hash;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LC0347TopKFrequent {
    private static final int[] nums = {1, 1, 1, 2, 2, 3};
    private static final int k = 2;

    public static void main(String[] args) {
        System.out.println(Arrays.toString(topKFrequent(nums, k)));
    }

    private static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
//            if (!map.containsKey(num)) map.put(num, 1);
//            else map.put(num, map.get(num) + 1);
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            int max = -1, key = -1;
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                int value = entry.getValue();
                if (value > max) {
                    max = value;
                    key = entry.getKey();
                }
            }
            result[i] = key;
            map.remove(key);
        }
        return result;
    }
}
