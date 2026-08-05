package leetcode.array.p0059;


/**
 * 59. 螺旋矩阵 II
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：n = 3
 * 输出：[[1,2,3],[8,9,4],[7,6,5]]
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：[[1]]
 *
 *
 * 提示：
 *
 * 1 <= n <= 20
 *
 * */

class Solution {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];

        int top = 0;
        int bottom = n - 1;
        int left = 0;
        int right = n - 1;

        int number = 1;

        while (top <= bottom && left <= right) {

            // 1. 填写上边：从左到右
            for (int col = left; col <= right; col++) {
                matrix[top][col] = number++;
            }
            top++;

            // 2. 填写右边：从上到下
            for (int row = top; row <= bottom; row++) {
                matrix[row][right] = number++;
            }
            right--;

            // 3. 填写下边：从右到左
            if (top <= bottom) {
                for (int col = right; col >= left; col--) {
                    matrix[bottom][col] = number++;
                }
                bottom--;
            }

            // 4. 填写左边：从下到上
            if (left <= right) {
                for (int row = bottom; row >= top; row--) {
                    matrix[row][left] = number++;
                }
                left++;
            }
        }

        return matrix;
    }
}
