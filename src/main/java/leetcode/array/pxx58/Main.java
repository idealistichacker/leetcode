package leetcode.array.pxx58;

import java.util.Scanner;

/**
 * 58. 区间和（第九期模拟笔试）
 * 题目描述
 * 给定一个整数数组 Array，请计算该数组在每个指定区间内元素的总和。
 *
 * 输入描述
 * 第一行输入为整数数组 Array 的长度 n，接下来 n 行，每行一个整数，表示数组的元素。随后的输入为需要计算总和的区间下标：a，b （b > = a），直至文件结束。
 *
 * 输出描述
 * 输出每个指定区间内元素的总和。
 *
 * 输入示例
 * 5
 * 1
 * 2
 * 3
 * 4
 * 5
 * 0 1
 * 1 3
 * 输出示例
 * 3
 * 9
 * 提示信息
 * 数据范围：
 * 0 < n <= 100000
 *
 * */

//前缀和
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        long[] prefix = new long[n];

        long sum = 0;

        for (int i = 0; i < n; i++) {
            int value = scanner.nextInt();
            sum += value;
            prefix[i] = sum;
        }

        while (scanner.hasNextInt()) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();

            if (a == 0) {
                System.out.println(prefix[b]);
            } else {
                System.out.println(prefix[b] - prefix[a - 1]);
            }
        }

        scanner.close();
    }
}
