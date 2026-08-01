package leetcode.array.p0076;

import java.util.HashMap;
import java.util.Map;

/**
 * 76. 最小覆盖子串
 * 困难
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给定两个字符串 s 和 t，长度分别是 m 和 n，返回 s 中的 最短窗口 子串，使得该子串包含 t 中的每一个字符（包括重复字符）。如果没有这样的子串，返回空字符串 ""。
 *
 * 测试用例保证答案唯一。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
 * 示例 2：
 *
 * 输入：s = "a", t = "a"
 * 输出："a"
 * 解释：整个字符串 s 是最小覆盖子串。
 * 示例 3:
 *
 * 输入: s = "a", t = "aa"
 * 输出: ""
 * 解释: t 中两个字符 'a' 均应包含在 s 的子串中，
 * 因此没有符合条件的子字符串，返回空字符串。
 *
 *
 * 提示：
 *
 * m == s.length
 * n == t.length
 * 1 <= m, n <= 105
 * s 和 t 由英文字母组成
 *
 *
 * 进阶：你能设计一个在 O(m + n) 时间内解决此问题的算法吗？
 * */

/**
 * 示例 1：
 *
 * 输入：s = "ADOBECODEBANC", t = "ABC"     "DOBECODEBANC"   "AAADOBECODEBANC"
 * 输出："BANC"
 * 解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
 *
 * */
class Solution {
    //1.扫描目标串t，用Map记录每个字符出现的顺序
    public String minWindow(String s, String t) {
        if (s.equals(t)) {
            return s;
        }

        if (s.length() < t.length()) {
            return "";
        }

        char[] sCharArray = s.toCharArray();
        char[] tCharArray = t.toCharArray();
        Map<Character, Integer> tMap = new HashMap<>();
        int minRight = -1, minLeft = -1;
        int minWindowLength = Integer.MAX_VALUE;

        for (int i = 0; i < t.length(); i++) {
            char tin = tCharArray[i];
            tMap.put(tin, tMap.getOrDefault(tin, 0) + 1);
        }

        Map<Character, Integer> tMapCopy = new HashMap<>(tMap);


        for (int right = 0; right < sCharArray.length; right++) {
            char lastChar = sCharArray[right];

            //不需要维护一个数组用来返回值，只要知道最小子串所在窗口的left和right就可以直接截取返回;

            //contain肯定不为空
            if (tMapCopy.containsKey(lastChar)) {
                int keyValue = tMapCopy.get(lastChar) - 1;
                if (keyValue == 0) {
                    tMapCopy.remove(lastChar);
                } else {
                    tMapCopy.put(lastChar, keyValue);
                }
            }
            //输入：s = "ADOBECODEBANC", t = "ABC"     "DOBECODEBANC"   "AAADOBECODEBANC" "AABBDFGDFGDFHCSAB"
            if (tMapCopy.isEmpty()){
                int backRight = right;
                Map<Character, Integer> anotherTMapCopy = new HashMap<>(tMap);
                while (!anotherTMapCopy.isEmpty()) {
                    char backChar = sCharArray[backRight];
                    if (anotherTMapCopy.containsKey(backChar)) {
                        int backValue = anotherTMapCopy.get(backChar) - 1;
                        if (backValue == 0) {
                            anotherTMapCopy.remove(backChar);
                        } else {
                            anotherTMapCopy.put(backChar, backValue);
                        }
                    }
                    backRight--;
                }

                if ((right - backRight) <= minWindowLength) {
                    minWindowLength = right -backRight;
                    minLeft = backRight + 1;
                    minRight = right;
                }
                tMapCopy.put(sCharArray[backRight+1],1);
            }
        }

        System.out.println(minLeft + " " + (minRight + 1));
        if (minLeft == -1 && minRight == -1) {
            return "";
        }
//        tMap.put(tCharArray[0], 999);
//        for (Map.Entry<Character, Integer> entry : tMapCopy.entrySet()) {
//            Character key = entry.getKey();
//            Integer value = entry.getValue();
//            System.out.println(key + ":" + value);
//        }
//        System.out.println(tMap.get(tCharArray[0]));
        return s.substring(minLeft, minRight+1);
    }


    public  void main(String[] args) {
        //s = "ab" t = "a"; s = "a" t = "b";
        String s = "b";
        String t = "a";
        System.out.println(minWindow(s, t));
    }
}
