package leetcode.hashtable.p0383;

import java.util.HashMap;
import java.util.Map;

/**
 * 383. 赎金信
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你两个字符串：ransomNote 和 magazine ，判断 ransomNote 能不能由 magazine 里面的字符构成。
 *
 * 如果可以，返回 true ；否则返回 false 。
 *
 * magazine 中的每个字符只能在 ransomNote 中使用一次。
 *
 *
 *
 * 示例 1：
 *
 * 输入：ransomNote = "a", magazine = "b"
 * 输出：false
 * 示例 2：
 *
 * 输入：ransomNote = "aa", magazine = "ab"
 * 输出：false
 * 示例 3：
 *
 * 输入：ransomNote = "aa", magazine = "aab"
 * 输出：true
 *
 *
 * 提示：
 *
 * 1 <= ransomNote.length, magazine.length <= 105
 * ransomNote 和 magazine 由小写英文字母组成
 *
 */

class Solution {
    //用HashMap遍历ransomNote每个字符++,再遍历magazine每个字符--，判断是否ransomNote的HashMap为空
    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> ransomNoteRecord = new HashMap<>();
        for (int i = 0; i < ransomNote.length(); i++) {
            char currentChar = ransomNote.charAt(i);
            ransomNoteRecord.put(currentChar, ransomNoteRecord.getOrDefault(currentChar, 0) + 1);
        }

        for (int i = 0; i < magazine.length(); i++) {
            char currentChar = magazine.charAt(i);
            if (ransomNoteRecord.containsKey(currentChar)) {
                int ranValue = ransomNoteRecord.get(currentChar);
                ranValue--;
                if (ranValue == 0) {
                    ransomNoteRecord.remove(currentChar);
                }
                else {
                    ransomNoteRecord.put(currentChar, ranValue);
                }
            }
            if (ransomNoteRecord.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public boolean canConstructSimper(String ransomNote, String magazine) {
        // shortcut
        if (ransomNote.length() > magazine.length()) {
            return false;
        }
        // 定义一个哈希映射数组
        int[] record = new int[26];

        // 遍历
        for(char c : magazine.toCharArray()){
            record[c - 'a'] += 1;
        }

        for(char c : ransomNote.toCharArray()){
            record[c - 'a'] -= 1;
        }

        // 如果数组中存在负数，说明ransomNote字符串中存在magazine中没有的字符
        for(int i : record){
            if(i < 0){
                return false;
            }
        }

        return true;
    }

}
