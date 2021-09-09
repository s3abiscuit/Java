package algs.dp;

public class Fibonacci {
    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        // 递归
        System.out.println(fibWithRecursion(10));
        // 递归 + memo
        System.out.println(fibWithRecursionAndMemo(47 - 1));
        // 迭代
        System.out.println(fibWithIteration1(47 - 1));
        // 迭代
        System.out.println(fibWithIteration2(47 - 1));
    }

    // 动态规划, 自顶向下, 递归
    public static int fibWithRecursion(int n) {
        if (n == 1 || n == 2) return 1;
        return fibWithRecursion(n - 1) + fibWithRecursion(n - 2);
    }

    // 动态规划, 自顶向下,
    public static int fibWithRecursionAndMemo(int n) {
        int[] memo = new int[n + 1];
        memo[0] = 0;
        memo[1] = memo[2] = 1;
        return helper(memo, n);
    }

    // 迭代, 自底向上
    public static int fibWithIteration1(int n) {
        int[] memo = new int[n + 1];
        memo[1] = 1;
        memo[2] = 1;
        for (int i = 3; i <= n; i++) {
            memo[i] = memo[i - 1] + memo[i - 2];
        }
        return memo[n];
    }

    // 迭代, 自底向上, 状态压缩
    public static int fibWithIteration2(int n) {
        if (n == 1 || n == 2) return 1;
        int pre = 1, cur = 1;
        for (int i = 3; i <= n; i++) {
            int sum = pre + cur;
            pre = cur;
            cur = sum;
        }
        return cur;
    }

    private static int helper(int[] memo, int n) {
//        if (n == 1 || n == 2) return 1;
        if (memo[n] != 0) return memo[n];
        memo[n] = helper(memo, n - 1) + helper(memo, n - 2);
//        System.out.println(n + ": " + memo[n]);
        return memo[n];
    }
}
