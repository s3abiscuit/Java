package algs.array.sort;

import java.util.Arrays;

public class Merge {
    public static void main(String[] args) {
        int[] t1 = {3, 2, 1};
        mergeSortRecursion(t1);
        System.out.println(Arrays.toString(t1));

        int[] t2 = {4, 5, 6};
        mergeSortIteration(t2);
        System.out.println(Arrays.toString(t2));
    }

    public static void mergeSortIteration(int[] arr) {
        int[] aux = new int[arr.length];
        //使用非递归的方式来实现归并排序
        int len = arr.length;
        int k = 1;
        // k = 1, 2, 4, 8...
        while (k < len) {
            MergePass(arr, aux, k, len);
            k *= 2;
        }
    }

    //MergePass方法负责将数组中的相邻的有k个元素的字序列进行归并
    private static void MergePass(int[] arr, int[] aux, int k, int n) {
        int i = 0;
        int j;

        //从前往后,将2个长度为k的子序列合并为1个
        while (i + 2 * k - 1 < n) { //下标未越界
            merge(arr, aux, i, i + k - 1, i + 2 * k - 1);
            i += 2 * k;
        }

        //这段代码保证了，将那些“落单的”长度不足两两merge的部分和前面merge起来。
        if (i + k < n) { // 剩余 2 部分 [0,i+k-1] [i+k,n-1]
            merge(arr, aux, i, i + k - 1, n - 1);
        }

    }

    public static void mergeSortRecursion(int[] a) {
        int[] aux = new int[a.length];
        mergeSortRecursion(a, aux, 0, a.length - 1);
    }

    private static void mergeSortRecursion(int[] a, int[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        mergeSortRecursion(a, aux, lo, mid);
        mergeSortRecursion(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    private static void merge(int[] a, int[] aux, int lo, int mid, int hi) {
        // precondition: a[lo .. mid] and a[mid+1 .. hi] are sorted subarrays

        // copy to aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        // merge back to a[]
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (aux[j] < aux[i]) a[k] = aux[j++];
            else a[k] = aux[i++];
        }

    }

}
