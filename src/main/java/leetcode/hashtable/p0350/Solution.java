package leetcode.hashtable.p0350;

import java.util.ArrayList;
import java.util.List;

/**
 * 350. 两个数组的交集 II
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你两个整数数组 nums1 和 nums2 ，请你以数组形式返回两数组的交集。返回结果中每个元素出现的次数，应与元素在两个数组中都出现的次数一致（如果出现次数不一致，则考虑取较小值）。可以不考虑输出结果的顺序。
 *
 *人话：找两个数组的交集，重复几个 x 就返回几个 x
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,2,2,1], nums2 = [1,2,2]    进阶:[1,1,2,2,3,5,9] [1,2,2,2,3,4,5,6,6,7]
 * 输出：[1,2,2] 或 [2,1,2] 或 [2,2,1]
 * 示例 2:
 *
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[4,9]
 *
 *
 * 提示：
 *
 * 1 <= nums1.length, nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 1000
 *
 *
 * 进阶：
 *
 * 如果给定的数组已经排好序呢？你将如何优化你的算法？
 * 如果 nums1 的大小比 nums2 小，哪种方法更优？
 * 如果 nums2 的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
 *
 *
 * 三种进阶的核心分别是：
 * 已排序：双指针
 * 无序且一边更小：用较小数组建立哈希表
 * 数据在磁盘：小数组进内存，大数组分块读取，同时结果也存在内存中
 */
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        int[] record1 = new int[1001];
        int[] record2 = new int[1001];
        List<Integer> resList = new ArrayList<>();
        for (int num1 : nums1) {
            record1[num1]++;
        }

        for (int num2 : nums2) {
            record2[num2]++;
        }

        for (int i = 0; i < 1001; i++) {
            if (record1[i] > 0 && record2[i] >0) {
                int nums = Math.min(record1[i], record2[i]);
                for (int j = 0; j < nums; j++) {
                    resList.add(i);
                }
            }
        }
        int index = 0;
        int[] res = new int [resList.size()];
        for (int i : resList) {
            res[index++] = i;
        }

        return res;
    }

    public int[] intersectNextLevel(int[] nums1, int[] nums2) {
        int i = 0;
        int j = 0;
        List<Integer> result = new ArrayList<>();

        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                result.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }

        int[] res = new int[result.size()];
        for (int k = 0; k < result.size(); k++) {
            res[k] = result.get(k);
        }

        return res;
    }

}
