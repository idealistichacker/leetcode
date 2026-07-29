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