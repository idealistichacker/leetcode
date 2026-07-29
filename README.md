# LeetCode Java 刷题记录

这是一个用于长期整理 LeetCode Java 解答、测试代码和学习笔记的项目。

目标不是只保存“能通过”的代码，而是记录：

- 每道题的最终解法
- 本地 JUnit 测试
- 时间与空间复杂度
- 做题时出现的错误
- 后续复习记录

---

## 项目结构

```text
leetcode/
├── build.gradle.kts
├── settings.gradle.kts
├── .gitignore
├── README.md
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── leetcode/
│   │           ├── array/
│   │           │   ├── p0026/
│   │           │   │   └── Solution.java
│   │           │   └── p0027/
│   │           │       └── Solution.java
│   │           ├── binarysearch/
│   │           ├── linkedlist/
│   │           ├── hashmap/
│   │           ├── stack/
│   │           ├── tree/
│   │           ├── graph/
│   │           └── dynamicprogramming/
│   │
│   └── test/
│       └── java/
│           └── leetcode/
│               └── array/
│                   └── p0027/
│                       └── SolutionTest.java
│
└── notes/
    └── array/
        └── 0027-remove-element.md
```

### 各目录用途

- `src/main/java`：保存正式题解
- `src/test/java`：保存 JUnit 测试
- `notes`：保存思路、复杂度、错误和复习记录
- `README.md`：记录整个项目的说明和刷题进度

---

## 命名规则

### Package

每道题使用独立 package：

```text
leetcode.<题型>.p<四位题号>
```

例如：

```text
leetcode.array.p0027
leetcode.binarysearch.p0704
leetcode.linkedlist.p0206
```

题号前加 `p`，是因为 Java 标识符不能以数字开头。

### Java 类

每道题统一使用：

```java
public class Solution {
}
```

虽然不同题目都叫 `Solution`，但它们位于不同 package 中，因此不会冲突。

例如：

```text
leetcode.array.p0027.Solution
leetcode.binarysearch.p0704.Solution
```

### 测试类

测试类统一命名为：

```text
SolutionTest.java
```

并放在与题解相同的 package 中。

### 笔记文件

笔记使用：

```text
四位题号-英文题名.md
```

例如：

```text
0027-remove-element.md
0704-binary-search.md
```

---

## Gradle 配置

本项目使用：

- Java
- Gradle
- Kotlin DSL
- JUnit 5

`build.gradle.kts`：

```kotlin
plugins {
    java
}

group = "leetcode"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.14.4")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}
```

`settings.gradle.kts`：

```kotlin
rootProject.name = "leetcode"
```

---

## IntelliJ IDEA 配置说明

`src/main/java` 应被识别为：

```text
Sources Root
```

`src/test/java` 应被识别为：

```text
Test Sources Root
```

如果左侧项目树显示：

```text
leetcode.array.p0027
```

而不是逐级目录，这是 IntelliJ 的紧凑 package 显示方式。

可以在 Project 窗口中关闭：

```text
Tree Appearance
→ Compact Middle Packages
```

关闭后会显示为：

```text
leetcode/
└── array/
    └── p0027/
```

---

## 新增一道题的流程

以第 27 题为例。

### 1. 创建题解 package

在：

```text
src/main/java
```

下创建：

```text
leetcode.array.p0027
```

### 2. 创建 `Solution.java`

```java
package leetcode.array.p0027;

public class Solution {

    public int removeElement(int[] nums, int val) {
        int slowIndex = 0;

        for (int fastIndex = 0;
             fastIndex < nums.length;
             fastIndex++) {

            if (nums[fastIndex] != val) {
                nums[slowIndex] = nums[fastIndex];
                slowIndex++;
            }
        }

        return slowIndex;
    }
}
```

### 3. 创建测试 package

在：

```text
src/test/java
```

下创建相同 package：

```text
leetcode.array.p0027
```

### 4. 创建 `SolutionTest.java`

```java
package leetcode.array.p0027;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {

    @Test
    void exampleOne() {
        Solution solution = new Solution();
        int[] nums = {3, 2, 2, 3};

        int length = solution.removeElement(nums, 3);

        assertEquals(2, length);
        assertArrayEquals(
                new int[]{2, 2},
                Arrays.copyOf(nums, length)
        );
    }

    @Test
    void exampleTwo() {
        Solution solution = new Solution();
        int[] nums = {0, 1, 2, 2, 3, 0, 4, 2};

        int length = solution.removeElement(nums, 2);

        assertEquals(5, length);
        assertArrayEquals(
                new int[]{0, 1, 3, 0, 4},
                Arrays.copyOf(nums, length)
        );
    }

    @Test
    void allElementsRemoved() {
        Solution solution = new Solution();
        int[] nums = {2, 2, 2};

        int length = solution.removeElement(nums, 2);

        assertEquals(0, length);
    }

    @Test
    void noElementsRemoved() {
        Solution solution = new Solution();
        int[] nums = {1, 3, 4};

        int length = solution.removeElement(nums, 2);

        assertEquals(3, length);
        assertArrayEquals(
                new int[]{1, 3, 4},
                Arrays.copyOf(nums, length)
        );
    }

    @Test
    void emptyArray() {
        Solution solution = new Solution();
        int[] nums = {};

        int length = solution.removeElement(nums, 2);

        assertEquals(0, length);
    }
}
```

---

## 题目笔记模板

每道题可以在 `notes` 中创建一份 Markdown 笔记：

```markdown
# 27. Remove Element

## 题目类型

数组、双指针

## 核心思路

fastIndex 遍历所有元素。

slowIndex 表示：

1. 下一个有效元素应该写入的位置
2. 当前已经保留的元素数量

当 nums[fastIndex] != val 时：

- 将该元素写入 nums[slowIndex]
- slowIndex 加 1

最后返回 slowIndex。

## 复杂度

时间复杂度：O(n)

额外空间复杂度：O(1)

## 容易出错的地方

- for 循环的三个部分之间必须有两个分号
- 不要返回 slowIndex + 1
- slowIndex 本身就是新数组长度
- 题目只保证前 slowIndex 个元素有效
- 不需要清空数组后面的旧元素

## 复习记录

- 第一次完成：
- 第一次复习：
- 第二次复习：
```

---

## 当前题目记录

| 题号 | 题目 | 类型 | 难度 | 状态 |
|---:|---|---|---|---|
| 27 | Remove Element | Array, Two Pointers | Easy | Completed |

---

## 第 27 题总结

### 核心思想

使用快慢指针：

- `fastIndex`：扫描整个数组
- `slowIndex`：指向下一个保留元素应该写入的位置

当当前元素不等于 `val` 时：

```java
nums[slowIndex] = nums[fastIndex];
slowIndex++;
```

循环结束后：

```java
return slowIndex;
```

### 为什么是原地修改

程序直接修改输入数组 `nums`，没有创建与输入长度相关的新数组。

### 为什么额外空间是 O(1)

只使用了固定数量的整数变量：

```java
int fastIndex;
int slowIndex;
```

无论数组长度是多少，额外变量数量都不变。

### 时间复杂度

每个元素只访问一次：

```text
O(n)
```

### 额外空间复杂度

只使用固定数量变量：

```text
O(1)
```

---

## Git 忽略文件

建议 `.gitignore`：

```gitignore
.idea/
.gradle/
build/
out/
*.iml
```

需要保留并提交：

```text
src/
notes/
README.md
build.gradle.kts
settings.gradle.kts
gradle/
gradlew
gradlew.bat
```

---

## 刷题原则

1. 先独立思考，再查看提示。
2. 不只提交代码，还要写本地测试。
3. 记录第一次做错的原因。
4. 写清楚时间复杂度和空间复杂度。
5. 同一道题至少复习一次。
6. 优先理解指针、索引和数据结构状态，而不是死记模板。
