package top.sanjeev.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumberUtilsTest {

    @Test
    @DisplayName("测试去重和排序功能：正常情况")
    void testRemoveDuplicatesAndSort_Normal() {
        List<Integer> input = Arrays.asList(3, 1, 2, 3, 2, 1);
        List<Integer> expected = Arrays.asList(1, 2, 3);
        List<Integer> result = NumberUtils.removeDuplicatesAndSort(input);

        assertEquals(expected, result, "去重和排序后的列表应为 [1, 2, 3]");
    }

    @Test
    @DisplayName("测试去重和排序功能：已经去重并排序过的列表")
    void testRemoveDuplicatesAndSort_AlreadySorted() {
        List<Integer> input = Arrays.asList(1, 2, 3);
        List<Integer> expected = Arrays.asList(1, 2, 3);
        List<Integer> result = NumberUtils.removeDuplicatesAndSort(input);

        assertEquals(expected, result, "去重和排序后的列表应为 [1, 2, 3]");
    }

    @Test
    @DisplayName("测试去重和排序功能：空列表")
    void testRemoveDuplicatesAndSort_EmptyList() {
        List<Integer> input = Collections.emptyList();
        List<Integer> expected = Collections.emptyList();
        List<Integer> result = NumberUtils.removeDuplicatesAndSort(input);

        assertEquals(expected, result, "空列表应返回空列表");
    }

    @Test
    @DisplayName("测试去重和排序功能：包含负数和零")
    void testRemoveDuplicatesAndSort_NegativeAndZero() {
        List<Integer> input = Arrays.asList(0, -1, 3, 2, -1, 0, 2);
        List<Integer> expected = Arrays.asList(-1, 0, 2, 3);
        List<Integer> result = NumberUtils.removeDuplicatesAndSort(input);

        assertEquals(expected, result, "去重和排序后的列表应为 [-1, 0, 2, 3]");
    }

    @Test
    @DisplayName("测试去重和排序功能：包含 null 值")
    void testRemoveDuplicatesAndSort_WithNull() {
        List<Integer> input = Arrays.asList(1, null, 2, null, 3);
        List<Integer> expected = Arrays.asList(null, 1, 2, 3);
        List<Integer> result = NumberUtils.removeDuplicatesAndSort(input);

        assertEquals(expected, result, "包含 null 值的列表应返回 [null, 1, 2, 3]");
    }
}
