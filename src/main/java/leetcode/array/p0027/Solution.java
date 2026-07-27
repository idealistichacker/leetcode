package leetcode.array.p0027;

class Solution {
    public int removeElement(int[] nums, int val) {
        //1.fastIndex所在元素等于要删除的元素就跳过(fastIndex++),slowIndex不变
        //2.fastIndex所在元素不等于要删除的元素就赋值给slowIndex所在元素，然后fastIndex++、slowIndex++
        int fastIndex = 0, slowIndex = 0;
        for (; fastIndex < nums.length; fastIndex++) {
            if (nums[fastIndex] == val) {
                continue;
            } else if (nums[fastIndex] != val) {
                nums[slowIndex] = nums[fastIndex];
                slowIndex++;
            }
        }
        return slowIndex;
    }
}
