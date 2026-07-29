package leetcode.array.p0844;

class Solution {
    public boolean backspaceCompare(String s, String t) {
        int i = s.length() - 1;
        int j = t.length() - 1;

        int skipS = 0;
        int skipT = 0;

        while (i >= 0 || j >= 0) {

            // 找到 s 中下一个有效字符
            while (i >= 0) {
                if (s.charAt(i) == '#') {
                    skipS++;
                    i--;
                } else if (skipS > 0) {
                    skipS--;
                    i--;
                } else {
                    break;
                }
            }

            // 找到 t 中下一个有效字符
            while (j >= 0) {
                if (t.charAt(j) == '#') {
                    skipT++;
                    j--;
                } else if (skipT > 0) {
                    skipT--;
                    j--;
                } else {
                    break;
                }
            }

            // 两边都存在有效字符，但是字符不相同
            if (i >= 0 && j >= 0) {
                if (s.charAt(i) != t.charAt(j)) {
                    return false;
                }
            } else {
                // 一个结束了，另一个没结束
                if (i >= 0 || j >= 0) {
                    return false;
                }
            }

            // 当前有效字符比较完成，继续向前
            i--;
            j--;
        }

        return true;
    }

    public static void main(String[] args) {
        String a = "abcd";
        char[] chars = a.toCharArray();
        System.out.println(chars.length);
        String result = new String(chars);
    }
}
