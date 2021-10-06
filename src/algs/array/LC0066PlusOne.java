//给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
//
// 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
//
// 你可以假设除了整数 0 之外，这个整数不会以零开头。
//
//
//
// 示例 1：
//
//
//输入：digits = [1,2,3]
//输出：[1,2,4]
//解释：输入数组表示数字 123。
//
//
// 示例 2：
//
//
//输入：digits = [4,3,2,1]
//输出：[4,3,2,2]
//解释：输入数组表示数字 4321。
//
//
// 示例 3：
//
//
//输入：digits = [0]
//输出：[1]
//
//
//
//
// 提示：
//
//
// 1 <= digits.length <= 100
// 0 <= digits[i] <= 9
//
// Related Topics 数组 数学 👍 763 👎 0
package algs.array;

public class LC0066PlusOne {
    public static void main(String[] args) {

    }

    public static int[] plusOne(int[] digits) {
        int size = digits.length;
        for(int i = size-1; i >= 0; i--){
            if (++digits[i] < 10) break;
            digits[i] = 0;
            if (i == 0){
                digits = new int[size+1];
                digits[0]=1;
            }
        }
        return digits;
    }

    public static int[] plusOne1(int[] digits) {
        int size = digits.length;
        boolean flag = false;
        for(int i = size-1; i >= 0; i--){
            if (digits[i]+1 < 10) {
                digits[i] += 1;
                break;
            }
            digits[i] = 0;
            if (i == 0) flag = true;
        }
        if (!flag) return digits;
        int[] result = new int[size+1];
        result[0] = 1;
        System.arraycopy(digits, 0, result, 1, size);
        return result;
    }

    public int[] plusOne2(int[] digits) {
        int len = digits.length;
        while (--len>=0) {
            if (++digits[len]+1<10) break;
            digits[len] = 0;
            if (len==0) {
                digits = new int[digits.length+1];
                digits[0] = 1;
            }
        }
        return digits;
    }
}
