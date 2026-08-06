package leetcode.hashtable.p0001;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1. 两数之和
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案，并且你不能使用两次相同的元素。
 *
 * 你可以按任意顺序返回答案。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * 示例 2：
 *
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 * 示例 3：
 *
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 *
 *
 * 提示：
 *
 * 2 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * 只会存在一个有效答案
 *
 *
 * 进阶：你可以想出一个时间复杂度小于 O(n2) 的算法吗？
 */
class Solution {
    //用HashMap
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        Map<Integer, Integer> record = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int temp = target - nums[i];
            if (record.containsKey(nums[i])) {
                res[0] = record.get(nums[i]);
                res[1] = i;
                break;
            }
            else {
                record.put(temp, i);
            }
        }
    return res;
    }


    //nums = [2,7,11,15], target = 9
//使用双指针
    public int[] twoSumDoublePointer(int[] nums, int target) {
        int m=0,n=0,k,board=0;
        int[] res=new int[2];
        int[] tmp1=new int[nums.length];
        //备份原本下标的nums数组
        System.arraycopy(nums,0,tmp1,0,nums.length);
        //将nums排序
        Arrays.sort(nums);
        //双指针
        for(int i=0,j=nums.length-1;i<j;){
            if(nums[i]+nums[j]<target)
                i++;
            else if(nums[i]+nums[j]>target)
                j--;
            else if(nums[i]+nums[j]==target){
                m=i;
                n=j;
                break;
            }
        }
        //找到nums[m]在tmp1数组中的下标
        for(k=0;k<nums.length;k++){
            if(tmp1[k]==nums[m]){
                res[0]=k;
                break;
            }
        }
        //找到nums[n]在tmp1数组中的下标
        for(int i=0;i<nums.length;i++){
            if(tmp1[i]==nums[n]&&i!=k)
                res[1]=i;
        }
        return res;
    }
}
