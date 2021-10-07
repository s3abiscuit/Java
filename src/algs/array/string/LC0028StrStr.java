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
