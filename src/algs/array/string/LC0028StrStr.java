//实现 strStr() 函数。
//
// 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如
//果不存在，则返回 -1 。
//
//
//
// 说明：
//
// 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
//
// 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与 C 语言的 strstr() 以及 Java 的 indexOf() 定义相符。
//
//
//
// 示例 1：
//
//
//输入：haystack = "hello", needle = "ll"
//输出：2
//
//
// 示例 2：
//
//
//输入：haystack = "aaaaa", needle = "bba"
//输出：-1
//
//
// 示例 3：
//
//
//输入：haystack = "", needle = ""
//输出：0
//
//
//
//
// 提示：
//
//
// 0 <= haystack.length, needle.length <= 5 * 10⁴
// haystack 和 needle 仅由小写英文字符组成
//
// Related Topics 双指针 字符串 字符串匹配 👍 1060 👎 0
package algs.array.string;

import java.util.ArrayList;
import java.util.List;

public class LC0028StrStr {
    public static void main(String[] args) {

    }

    public static int strStr(String haystack, String needle) {
        int lh = haystack.length(), ln = needle.length();
        if (ln == 0) return 0;
        if (lh == 0 || ln > lh) return -1;
        char c = needle.charAt(0);
        // haystack 中找到可能的初始位置并记录
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < lh; i++) {
            if (haystack.charAt(i) == c && (i + ln <= lh))
                list.add(i);
        }
        if (list.isEmpty()) return -1;
        for (int i : list) {
            for (int j = 0; j < ln; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) break;
                if (j == ln - 1) return i;
            }
        }
        return -1;
    }

    public int strStr1(String haystack, String needle) {
        int len_H = haystack.length();
        int len_N = needle.length();
        if (len_N == 0) return 0;
        int i = 0;
        int j = 0;
        while (i < len_H && j < len_N) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }

        }
        if (j == len_N) return (i - j);
        return -1;
    }

}
