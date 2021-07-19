package basics;

public class BitOperationTest {
    public static void main(String[] args) {
        // 00000000 00000000 00000000 00000101
        //    00000000 00000000 00000000 00000000 右移动3位
        System.out.println(5 >> 3);//结果是0
        // 10000000 00000000 00000000 00000101
        // 11111111 11111111 11111111 11111010
        // 11111111 11111111 11111111 11111011
        //    11111111 11111111 11111111 11111111
        System.out.println(-5 >> 3);//结果是-1 高位补了1
        // 11111111 11111111 11111111 11111011
        //    00011111 11111111 11111111 11111011
        System.out.println(-5 >>> 3);//结果是536870911 高位补了0
    }

    int hammingDistance(int x, int y) {
        // 异或操作, 相反为 1
        int z = x ^ y;  
        // 求出 z 中 1 的个数
        int sum = 0;
        while (z > 0) {
            sum += z & 1;
            z = z >> 1;
        }
        return sum;
    }
}
