package leetcode.hashtable.p0049;

/**
 * 49. 字母异位词分组
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 *
 * 示例 1:
 *
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 *
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 *
 * 解释：
 *
 * 在 strs 中没有字符串可以通过重新排列来形成 "bat"。
 * 字符串 "nat" 和 "tan" 是字母异位词，因为它们可以重新排列以形成彼此。
 * 字符串 "ate" ，"eat" 和 "tea" 是字母异位词，因为它们可以重新排列以形成彼此。
 * 示例 2:
 *
 * 输入: strs = [""]
 *
 * 输出: [[""]]
 *
 * 示例 3:
 *
 * 输入: strs = ["a"]
 *
 * 输出: [["a"]]
 *
 *
 *
 * 提示：
 *
 * 1 <= strs.length <= 104
 * 0 <= strs[i].length <= 100
 * strs[i] 仅包含小写字母
 *
 */

import java.util.*;


//用 HashMap 分组
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {

            // 转成字符数组
            char[] chars = str.toCharArray();

            // 排序
            Arrays.sort(chars);

            // 作为key
            String key = new String(chars);

            // 不存在则创建List
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }

            // 加入对应分组
            map.get(key).add(str);
        }

        return new ArrayList<>(map.values());
    }
}
