package algs.array;

public class LC240SearchMatrix {
    private static final int[][] matrix =
            {{1, 4, 7, 11, 15},
                    {2, 5, 8, 12, 19},
                    {3, 6, 9, 16, 22},
                    {10, 13, 14, 17, 24},
                    {18, 21, 23, 26, 30}};
    private static final int target = 5;

    public static void main(String[] args) {
        System.out.println(searchMatrix(matrix, target));
    }

    private static boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length, column = matrix[0].length;
        int r = 0, c = column - 1;
        int anchor;
        while (r < row && c >= 0) {
            anchor = matrix[r][c];
            if (target > anchor) r++;
            else if (target < anchor) c--;
            else return true;
        }
        return false;
    }
}
