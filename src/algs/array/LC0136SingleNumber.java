//给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
//
// 说明：
//
// 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
//
// 示例 1:
//
// 输入: [2,2,1]
//输出: 1
//
//
// 示例 2:
//
// 输入: [4,1,2,1,2]
//输出: 4
// Related Topics 位运算 数组 👍 2048 👎 0
package algs.array;

import java.util.HashMap;
import java.util.Map;

public class LC0136SingleNumber {
    public static void main(String[] args) {

    }

    public static int singleNumber(int[] nums) {
        int result = 0;
        for(int num: nums){
            result ^= num;
        }
        return result;
    }

    public static int singleNumber1(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int result = nums[0];
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) +1);
        }
        for(int i : map.keySet()) {
            if (map.get(i) == 1) result = i;
        }
        return result;

    }
}
