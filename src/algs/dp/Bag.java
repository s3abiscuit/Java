/*
给你一个可装载重量为 W 的背包和 N 个物品，
每个物品有重量和价值两个属性。其中第 i 个物品的重量为 wt[i]，价值为 val[i]，
现在让你用这个背包装物品，最多能装的价值是多少？
 */
package algs.dp;

public class Bag {
    public static void main(String[] args) {
        int N = 3, W = 4;
        int[] wt = {2, 1, 3}, val = {4, 2, 3};
        System.out.println(bag(N, W, wt, val));

    }

    private static int bag(int N, int W, int[] wt, int[] val) {
        int[][] dp = new int[N + 1][W + 1];
        for (int i = 1; i <= N; i++) {
            for (int w = 1; w <= W; w++) {
                if (w - wt[i - 1] < 0) dp[i][w] = dp[i - 1][w];
                else
                    dp[i][w] = Math.max(dp[i - 1][w - wt[i - 1]] + val[i - 1], dp[i - 1][w]);
            }
        }
        return dp[N][W];
    }
}
