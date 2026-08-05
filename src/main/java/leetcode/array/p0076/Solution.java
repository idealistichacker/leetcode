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
        char[] sCharArray = s.toCharArray();
        char[] tCharArray = t.toCharArray();
        Map<Character, Integer> tMap = new HashMap<>();
        Map<Character, Integer> sMap = new HashMap<>();

        int minStrLength = 0;
        for (int i = 0; i < t.length(); i++) {
            char tin = tCharArray[i];
            tMap.put(tin, tMap.getOrDefault(tin, 0) + 1);
        }

        Map<Character, Integer> sMapCopy = new HashMap<>(tMap);

        //记录最小子串
        char[] minRecord = new char[tCharArray.length];
        int currentMinRecordLength = 0;

        for (int right = 0; right < sCharArray.length; right++) {
            char lastChar = sCharArray[right];
            if (sMapCopy.size() == 0){
                minStrLength = Math.min(currentMinRecordLength, minStrLength);
                Map<Character, Integer> anotherTMap = new HashMap<>(tMap);
               while (anotherTMap.size() == sMap.size()) {

               }
//                    1.1需要一个值记录left
//
//               2.重新开始扩大窗口
//                    2.2重置sMapCopy Map<Character, Integer> sMapCopy = new HashMap<>(tMap);
            }

            minRecord[right] = sCharArray[right];
            currentMinRecordLength++;

            //contain肯定不为空
            if (sMapCopy.containsKey(lastChar)) {
                int keyValue = sMapCopy.get(lastChar) - 1;
                if (keyValue == 0) {
                    sMapCopy.remove(lastChar);
                } else {
                    sMapCopy.put(lastChar, keyValue);
                }
            }
        }

//        tMap.put(tCharArray[0], 999);
//        for (Map.Entry<Character, Integer> entry : sMapCopy.entrySet()) {
//            Character key = entry.getKey();
//            Integer value = entry.getValue();
//            System.out.println(key + ":" + value);
//        }
//        System.out.println(tMap.get(tCharArray[0]));
        return "god damn";
    }


    public  void main(String[] args) {
        String s = "abcabc";
        String t = "abc";
        minWindow(s, t);
    }
}
