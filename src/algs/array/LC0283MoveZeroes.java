//给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
//
// 示例:
//
// 输入: [0,1,0,3,12]
//输出: [1,3,12,0,0]
//
// 说明:
//
//
// 必须在原数组上操作，不能拷贝额外的数组。
// 尽量减少操作次数。
//
// Related Topics 数组 双指针 👍 1253 👎 0
package algs.array;

public class LC0283MoveZeroes {
    public static void main(String[] args) {

    }

    public void moveZeroes(int[] nums) {
        int tail = 0;
        for(int i=0; i<nums.length; i++){
            if (nums[i] != 0) nums[tail++] = nums[i];
        }
        for(int i=tail; i<nums.length;i++) {
            nums[i]=0;
        }
    }

    // 冒泡排序的思想
    public void moveZeroes1(int[] nums) {
        int size = nums.length;
        for(int i = size-2; i>=0; i--) {
            if (nums[i] != 0) continue;
            for (int j = i; j< size-1; j++){
                if(nums[j+1]==0) break;
                int tmp = nums[j];
                nums[j] = nums[j+1];
                nums[j+1] = tmp;
            }
        }
    }
}
