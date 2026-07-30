package leetcode.array.p0904;

import java.util.HashMap;
import java.util.Map;

/**
 * 滑动窗口核心思想：
 * 1.right右边界负责扩大窗口直到不满足窗口维护条件
 * 2.left负责缩小窗口直到窗口重新满足条件
 * 3.在每次扩大缩小的变化过程中找到最合适的目标值
 * 4.不断重复上述过程直到遍历完整个数组，一是right到达数组尽头（right < nums.length）
 *
 * 904. 水果成篮
 * 你正在探访一家农场，农场从左到右种植了一排果树。这些树用一个整数数组 fruits 表示，其中 fruits[i] 是第 i 棵树上的水果 种类 。
 *
 * 你想要尽可能多地收集水果。然而，农场的主人设定了一些严格的规矩，你必须按照要求采摘水果：
 *
 * 你只有 两个 篮子，并且每个篮子只能装 单一类型 的水果。每个篮子能够装的水果总量没有限制。
 * 你可以选择任意一棵树开始采摘，你必须从 每棵 树（包括开始采摘的树）上 恰好摘一个水果 。采摘的水果应当符合篮子中的水果类型。每采摘一次，你将会向右移动到下一棵树，并继续采摘。
 * 一旦你走到某棵树前，但水果不符合篮子的水果类型，那么就必须停止采摘。
 * 给你一个整数数组 fruits ，返回你可以收集的水果的 最大 数目。
 *
 *
 *
 * 示例 1：
 *
 * 输入：fruits = [1,2,1]
 * 输出：3
 * 解释：可以采摘全部 3 棵树。
 * 示例 2：
 *
 * 输入：fruits = [0,1,2,2]
 * 输出：3
 * 解释：可以采摘 [1,2,2] 这三棵树。
 * 如果从第一棵树开始采摘，则只能采摘 [0,1] 这两棵树。
 * 示例 3：
 *
 * 输入：fruits = [1,2,3,2,2]
 * 输出：4
 * 解释：可以采摘 [2,3,2,2] 这四棵树。
 * 如果从第一棵树开始采摘，则只能采摘 [1,2] 这两棵树。
 * 示例 4：
 *
 * 输入：fruits = [3,3,3,1,2,1,1,2,3,3,4]
 * 输出：5
 * 解释：可以采摘 [1,2,1,1,2] 这五棵树。
 *
 *
 * 提示：
 *
 * 1 <= fruits.length <= 105
 * 0 <= fruits[i] < fruits.length
 */
class Solution {
    //My initial solution:
    public int totalFruit(int[] fruits) {
        int fruitNums = 1, maxNums = -1, left = 0, right = 1, firstFruit = -1, secondFruitNow = -2;
        if (fruits.length == 1) {return 1;}
        firstFruit = fruits[0];
        int secondFruit = firstFruit;
        while (right < fruits.length) {

            secondFruitNow = fruits[right];
            if (secondFruitNow != firstFruit) {secondFruit = secondFruitNow;}
            right++;
            fruitNums++;
            if (fruitNums > maxNums) {
                maxNums = fruitNums;
            }
            if (right < fruits.length && firstFruit != secondFruit) {
                if (fruits[right] != firstFruit && fruits[right] != secondFruit) {
                    int back = right-1;
                    /**
                     * 进入这段逻辑时，当前窗口中一定存在另一种水果，因此 back 理论上不会越过 left。所以在题目约束和当前逻辑下，它是安全的。
                     * 但维护者必须先完整证明这一点，才敢相信它不会访问 fruits[-1]。更稳妥的写法是：
                     *
                     * while (back >= left && fruits[back] == lastType) {
                     *     back--;
                     * }
                     *
                     */
                    while(fruits[back] == secondFruitNow) {
                        back--;
                    }
                    left = back+1;
                    firstFruit = fruits[left];
                    fruitNums = right - left;
                }
            }
        }

        return maxNums;
    }

    public int totalFruitHashMap(int[] fruits) {
        //标准解法：用HashMap维护一个大小最大为2的滑动窗口，当窗口不满足条件即>2时，不断缩小左边的边界丢掉最左边的水果，使得窗口大小重新满足条件<=2
        Map<Integer, Integer> counts = new HashMap<>();

        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < fruits.length; right++) {
            int fruit = fruits[right];
            counts.put(fruit, counts.getOrDefault(fruit, 0) + 1);
            //{0,1,6,6,4,4,6}
            while (counts.size() > 2) {
                int leftFruit = fruits[left];
                counts.put(leftFruit, counts.get(leftFruit) - 1);

                if (counts.get(leftFruit) == 0) {
                    counts.remove(leftFruit);
                }

                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    /**
     * 更接近我初始思路的 O(1) 精简版
     *
     * 还可以完全不用 HashMap，只维护：
     *
     * 最近一种水果；
     * 倒数第二种水果；
     * 末尾连续相同水果的数量；
     * 当前合法区间长度。
     * 
     * 这个版本与原解法的核心思想基本一致，但不用向后扫描，也不需要手动维护左右指针。
     * */

    public int totalFruitInitialOptimize(int[] fruits) {
        int lastFruit = -1;
        int otherFruit = -1;

        int lastFruitCount = 0;
        int currentLength = 0;
        int maxLength = 0;

        for (int fruit : fruits) {
            if (fruit == lastFruit || fruit == otherFruit) {
                currentLength++;
            } else {
                currentLength = lastFruitCount + 1;
            }

            if (fruit == lastFruit) {
                lastFruitCount++;
            } else {
                otherFruit = lastFruit;
                lastFruit = fruit;
                lastFruitCount = 1;
            }

            maxLength = Math.max(maxLength, currentLength);
        }

        return maxLength;
    }


    public  void main(String[] args) {
        //{0,1,6,6,4,4,6}
        //{3,3,1,3,1,2,1,1,2,3,3,4}
        //{3,3,3,3,1,2,1,1,2,3,3,4}
        int[] f = {3,3,3,3,1,2,1,1,2,3,3,4};
        int a =  totalFruitHashMap(f);
        System.out.println(a);
    }

}
