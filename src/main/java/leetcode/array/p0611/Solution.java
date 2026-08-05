package leetcode.array.p0611;

import java.util.Arrays;

class Solution {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int count = 0;
        for (int k = nums.length - 1; 2 <= k; k--) {
            int mostLength = nums[k];
            int left = 0, right = k - 1;
            //注意right要>=0
            while (left < right) {
                if (nums[left] + nums[right] > mostLength) {
                    count += (right-left);
                    right--;
                }
                else {
                    left++;
                }
            }
        }
        return count;
    }
    public void main(String[] args) {
        int[] f = {2,2,3,4};
        int a =  triangleNumber(f);
        System.out.println(a);
    }
}
