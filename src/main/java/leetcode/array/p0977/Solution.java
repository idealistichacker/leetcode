package leetcode.array.p0977;

class Solution {
    public int[] sortedSquares(int[] nums) {
        int index = nums.length-1;
        int left = 0;
        int right =nums.length-1;
        int[] result = new int[nums.length];
        while (right >= left) {
            int leftSquare = nums[left] * nums[left];
            int rightSquare = nums[right] * nums[right];
            if (rightSquare >= leftSquare) {
                result[index] = rightSquare;
                right--;
            }
            else {
                result[index] = leftSquare;
                left++;
            }
            index--;
        }
        return result;
    }
}
