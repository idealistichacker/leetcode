package leetcode.array.p0283;

class Solution {
    public void moveZeroes(int[] nums) {
        //1.aheadPointer为0时直接++，不为0时和zeroPointer交换元素后++1，同时zeroPointer++1
        //2.zeroPointer检查到当前位置元素为0时不动，不为0时++1
        int zeroPointer = 0, aheadPointer = 0;
        for (; aheadPointer < nums.length; aheadPointer++) {
            if (nums[aheadPointer] == 0) {continue;}
            else {
                int temp = nums[aheadPointer];
                nums[aheadPointer] = nums[zeroPointer];
                nums[zeroPointer] = temp;
            }
            if (nums[zeroPointer] != 0 ) {zeroPointer++;}
        }

    }
}