package leetcode.array.p0392;
import java.util.ArrayList;
import java.util.List;

/**
 * 392. 判断子序列
 * 简单
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 *
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 *
 * 进阶：
 *
 * 如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
 *
 * 致谢：
 *
 * 特别感谢 @pbrother 添加此问题并且创建所有测试用例。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "abc", t = "ahbgdc"
 * 输出：true
 * 示例 2：
 *
 * 输入：s = "axc", t = "ahbgdc"
 * 输出：false
 *
 *
 * 提示：
 *
 * 0 <= s.length <= 100
 * 0 <= t.length <= 10^4
 * 两个字符串都只由小写字符组成。
 *
 */

//输入：s = "abc", t = "ahbgdc"
//输出：true
class Solution {
    public boolean isSubsequence(String s, String t) {
        if (s.isEmpty()) {
            return true;
        }

        if (s.length() > t.length()) {return false; }

        char[] tArray = t.toCharArray();
        char[] sArray = s.toCharArray();
        int sPointer = 0;
        for (int i = 0; i < tArray.length; i++) {

            if (tArray[i] == sArray[sPointer]) {
                sPointer++;
            }
            if (sPointer == s.length()) {
                return true;
            }
        }
        return false;
    }

    public  boolean isSubsequenceSimper(String s, String t) {
        //定义双指针
        int i = 0;
        int j = 0;

        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }

            j++;
        }

        return i == s.length();
    }
}



/**
 * 二、进阶为什么不能继续直接双指针
 * 进阶场景是：
 * 固定一个 t
 * 然后有大量查询 s1、s2、s3……
 * 例如：
 * t = "abcde"
 *
 * 查询：
 * "ace"
 * "aec"
 * "bd"
 * "abcd"
 * ...
 * 如果每次都重新扫描整个 t，假设：
 * t.length() = 10000
 * 查询次数 k = 10亿
 * 最坏要处理：
 * 10000 × 10亿
 * 次字符访问，显然不可接受。
 * 关键变化是：
 * t 不变，所以可以提前预处理 t，让每次查询更快。
 *
 * 有两种经典预处理方案。
 * 三、进阶方案一：记录每个字符出现的位置
 * 假设：
 * t = "abacb"
 * 记录每个字符在 t 中出现的位置：
 * a → [0, 2]
 * b → [1, 4]
 * c → [3]
 * 现在查询：
 * s = "acb"
 * 匹配过程：
 * 找 a：选择位置 0
 * 找 c：必须找位置 > 0 的 c，得到 3
 * 找 b：必须找位置 > 3 的 b，得到 4
 * 成功。
 * 再看：
 * s = "abc"
 * a 选择位置 0
 * b 选择位置 1
 * c 选择位置 3
 * 成功。
 * 再看：
 * s = "ca"
 * c 选择位置 3
 * 需要找位置 > 3 的 a
 * a 的位置只有 [0, 2]
 * 失败。
 * 如何寻找下一个位置
 * 每个字符的位置列表都是有序的。
 * 我们需要解决：
 * 在有序数组中，找第一个严格大于当前位置的位置。
 *
 * 这可以用二分查找。
 *
 * */
public class SubsequenceChecker {

    private final List<Integer>[] positions;

    @SuppressWarnings("unchecked")
    public SubsequenceChecker(String t) {
        positions = new List[26];

        for (int i = 0; i < 26; i++) {
            positions[i] = new ArrayList<>();
        }

        for (int i = 0; i < t.length(); i++) {
            int index = t.charAt(i) - 'a';
            positions[index].add(i);
        }
    }

    public boolean isSubsequence(String s) {
        // 上一次匹配到的 t 中的位置
        int previousPosition = -1;

        for (int i = 0; i < s.length(); i++) {
            List<Integer> list = positions[s.charAt(i) - 'a'];

            // 找到第一个 > previousPosition 的位置
            int index = upperBound(list, previousPosition);

            if (index == list.size()) {
                return false;
            }

            previousPosition = list.get(index);
        }

        return true;
    }

    /**
     * 找到有序列表中第一个严格大于 target 的元素下标。
     */
    private int upperBound(List<Integer> list, int target) {
        int left = 0;
        int right = list.size();

        while (left < right) {
            int middle = left + (right - left) / 2;

            if (list.get(middle) <= target) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }

        return left;
    }
}