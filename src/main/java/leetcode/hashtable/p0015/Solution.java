package leetcode.hashtable.p0015;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 三数之和
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 *
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 解释：
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
 * 不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
 * 注意，输出的顺序和三元组的顺序并不重要。
 * 示例 2：
 *
 * 输入：nums = [0,1,1]
 * 输出：[]
 * 解释：唯一可能的三元组和不为 0 。
 * 示例 3：
 *
 * 输入：nums = [0,0,0]
 * 输出：[[0,0,0]]
 * 解释：唯一可能的三元组和为 0 。
 *
 *
 * 提示：
 *
 * 3 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 */

class Solution {
    //输入：nums = [-1,0,1,2,-1,-4,-1,-1,2,2]  --> [-4,-1,-1,-1,-1,0,1,2,2,2]
    //输出：[[-1,-1,2],[-1,0,1]]

//wrong way
//    public List<List<Integer>> threeSum(int[] nums) {
//        int left1 = 0, left2 = 1, right = nums.length-1;
//        int prelv1 = -106, prelv2 = -106, prer = -106;
//        List<List<Integer>> res = new ArrayList<>();
//        Arrays.sort(nums);
//        while (left2 < right) {
//            if ((nums[left1] + nums[left2] + nums[right]) == 0) {
//                res.add(Arrays.asList(nums[left1], nums[left2], nums[right]));
//                left1++;left2++;right--;
//                continue;
//            }
//
//            //左侧
//            if ((nums[left1] == prelv1 && nums[left2] == prelv2)) {
//                left1++; left2++;
//                continue;
//            }
//            else if ((nums[left1] + nums[left2] + nums[right]) < 0) {
//                prelv1 = nums[left1];
//                prelv2 = nums[left2];
//                left1++; left2++;
//                continue;
//            }
//
//            //右侧
//
//            if (nums[right] == prer) {right--;}
//            else if ((nums[left1] + nums[left2] + nums[right]) > 0) {
//                prer = nums[right];
//                right--;
//            }
//
//        }
//        return res;
//    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        //[ -100, -70, -60, 110, 120, 130, 160 ]
        //[-4,-1-1,0,1,2]
        //[0,0,0,0,0,1,1,2]
        for (int fix = 0; fix < nums.length - 2; fix++) {
            if(nums[0] + nums[1] + nums[2] > 0) {
                return res;
            }

            if(nums[fix] + nums[nums.length-2] + nums[nums.length-1] < 0) {
                continue;
            }


            if (fix > 0  &&  nums[fix] == nums[fix - 1]) {
                continue;
            }

            int left = fix + 1;
            int right = nums.length - 1;
            int target = -nums[fix];

            while(left < right) {
                int twoSum = nums[left] + nums[right];
                if (twoSum < target) {
                    left++;
                }
                else if (twoSum > target) {
                    right--;
                }
                else if (twoSum == target) {
                    res.add(Arrays.asList(nums[fix], nums[left], nums[right]));
                    left++;right--;

                    while (left < right && nums[left] == nums[left-1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right+1]) {
                        right--;
                    }
                }
            }
        }
        return res;
    }



    public List<List<Integer>> threeSumStandard(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            // 第一个数大于 0，后面的数只会更大，不可能凑出 0
            if (nums[i] > 0) {
                break;
            }

            // 第一个数去重
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum < 0) {
                    left++;
                } else if (sum > 0) {
                    right--;
                } else {
                    result.add(Arrays.asList(
                            nums[i],
                            nums[left],
                            nums[right]
                    ));

                    // 左指针去重
                    while (left < right &&
                            nums[left] == nums[left + 1]) {
                        left++;
                    }

                    // 右指针去重
                    while (left < right &&
                            nums[right] == nums[right - 1]) {
                        right--;
                    }

                    left++;
                    right--;
                }
            }
        }

        return result;
    }

    public void main(String[] args) {
        int[] input = {1,2,0,1,0,0,0,0};
        //[ -100, -70, -60, 110, 120, 130, 160 ]
        //[-4,-1-1,0,1,2]
        threeSum(input);

    }
}
