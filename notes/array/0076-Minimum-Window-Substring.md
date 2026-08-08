## 总评

**思路挺聪明，正确性基本没问题，但复杂度属于“披着滑动窗口外衣的暴力解法”。**

锐评打分：

- 思路创意：**8/10**
- 正确性：**8/10**
- 时间复杂度：**3/10**
- 可读性：**5/10**
- 面试表现：**能讲清楚得 6 分，声称是标准滑窗会被追问到冒汗**

这题的数据规模可到 `10^5`，并明确要求尝试实现 `O(m+n)`；你的代码最坏会退化到 `O(m²)`。

---

## 你这套思路聪明在哪里

你的 `tMapCopy` 并不是普通的窗口计数，而是在维护：

> 当前“虚拟窗口”距离覆盖 `t` 还缺哪些字符。

当它为空时，说明当前右端点 `right` 已经形成了有效窗口。然后你从 `right` 向左重新扫描：

```java
Map<Character, Integer> anotherTMapCopy = new HashMap<>(tMap);

while (!anotherTMapCopy.isEmpty()) {
    ...
    backRight--;
}
```

找出以当前 `right` 结尾的最短有效窗口。

最后：

```java
tMapCopy.put(sCharArray[backRight + 1], 1);
```

相当于把刚刚找到的左边界字符踢出窗口，告诉后续扫描：

> 现在又缺这个字符了，继续向右找。

这个状态转移其实是成立的，而且有一定巧思。不是瞎写出来碰巧过样例。

---

## 最大问题：你每找到一次答案，就从右往左重跑一遍

这一段是性能黑洞：

```java
if (tMapCopy.isEmpty()) {
    int backRight = right;
    Map<Character, Integer> anotherTMapCopy = new HashMap<>(tMap);

    while (!anotherTMapCopy.isEmpty()) {
        ...
        backRight--;
    }
}
```

标准滑动窗口的核心是：

> 左指针只往右走，不走回头路。

你的方案则是：

> 右指针往右走，但每满足一次条件，就派另一个指针回头重新调查现场。

### 一个直接把复杂度打爆的例子

```text
s = "a" 重复 100000 次
t = "a" 重复 50000 次
```

从 `right = 49999` 开始，每加入一个新字符，窗口都会满足条件。

于是每次都要反向扫描大约 `50000` 个字符。

总扫描次数约为：

```text
50001 × 50000 ≈ 25 亿次
```

还没算 `HashMap` 查询、装箱拆箱和对象创建。

所以最坏时间复杂度是：

```text
O(|s| × |t|)
```

当 `|t|` 和 `|s|` 同阶时，就是：

```text
O(|s|²)
```

---

## `HashMap` 创建得太豪爽了

每次发现有效窗口，你都会：

```java
new HashMap<>(tMap)
```

这会产生大量短命对象，增加 GC 压力。

而题目明确只有大小写英文字母，因此字符范围很小，直接使用 `int[128]` 就够了。

即使坚持使用 `HashMap`，标准做法也只需要两张长期存在的表，不应当在循环中反复复制。

---

## 变量命名比较折磨读者

例如：

```java
Map<Character, Integer> tMapCopy
Map<Character, Integer> anotherTMapCopy
int backRight
```

实际含义分别更接近：

```java
need
reverseNeed
leftMinusOne
```

尤其是 `backRight`，它退出循环后其实已经指向：

```text
真正左边界 - 1
```

所以才有这些不直观的计算：

```java
right - backRight
backRight + 1
```

虽然数学上没错，但读代码的人必须在脑子里做一次坐标转换。

---

## 其他代码洁癖问题

这个特殊判断没必要：

```java
if (s.equals(t)) {
    return s;
}
```

后面的通用逻辑本来就能处理。

这个判断可以保留，作为快速失败：

```java
if (s.length() < t.length()) {
    return "";
}
```

下面应该删除：

```java
System.out.println(minLeft + " " + (minRight + 1));
```

还有大段注释掉的调试代码也应该清理。

这个条件：

```java
if (minLeft == -1 && minRight == -1)
```

实际上检查一个变量就够了：

```java
if (minLeft == -1)
```

更新最短窗口时通常使用严格小于：

```java
if (currentLength < minWindowLength)
```

你使用 `<=` 会在长度相同时选择后出现的窗口。题目保证答案唯一，因此不影响本题结果，但通常没必要覆盖。

---

## 标准线性解法

关键思想：

- `need[c] > 0`：当前还缺几个 `c`
- `need[c] == 0`：数量刚好
- `need[c] < 0`：窗口里有多余的 `c`
- `missing`：总共还缺多少个字符
- 右指针扩张，满足后左指针收缩
- 每个字符最多被左右指针各访问一次

```java
public String minWindow(String s, String t) {
    if (s.length() < t.length()) {
        return "";
    }

    int[] need = new int[128];

    for (int i = 0; i < t.length(); i++) {
        need[t.charAt(i)]++;
    }

    int missing = t.length();
    int left = 0;

    int minLeft = 0;
    int minLength = Integer.MAX_VALUE;

    for (int right = 0; right < s.length(); right++) {
        char rightChar = s.charAt(right);

        // 这个字符正是窗口当前缺少的
        if (need[rightChar] > 0) {
            missing--;
        }

        // 加入窗口；负数代表该字符有富余
        need[rightChar]--;

        // 当前窗口已经覆盖 t，尝试收缩左边界
        while (missing == 0) {
            int currentLength = right - left + 1;

            if (currentLength < minLength) {
                minLength = currentLength;
                minLeft = left;
            }

            char leftChar = s.charAt(left);
            need[leftChar]++;
            left++;

            // 移除后变成正数，说明移除了一个必要字符
            if (need[leftChar] > 0) {
                missing++;
            }
        }
    }

    return minLength == Integer.MAX_VALUE
            ? ""
            : s.substring(minLeft, minLeft + minLength);
}
```

时间复杂度：

```text
O(|s| + |t|)
```

空间复杂度：

```text
O(1)
```

因为数组大小固定。

## 一句话锐评

你的方案属于：

> **逻辑上有设计，工程上有灾难；成功避免了错误答案，却没有成功避免超时。**

最值得保留的是“维护缺失量”的意识；最应该删除的是每次成功后从右向左重新扫描。