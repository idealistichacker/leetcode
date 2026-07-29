package leetcode.array.p0209;

class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        //1.left负责缩小窗口找最小长度，right负责扩大窗口使得sum>=target
        int minLength=Integer.MAX_VALUE, left=0, right=0, sum=0, length;

        while (right < nums.length) {
            //1.此处的while循环可直接去掉，但为思路清晰把条件加上
            while (sum < target && right < nums.length) {
                sum = sum + nums[right];
                right++;
            }
            while (sum >= target) {
                length = right - left;
                if (length < minLength) {minLength = length;}
                sum = sum - nums[left];
                left++;
            }
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
}
