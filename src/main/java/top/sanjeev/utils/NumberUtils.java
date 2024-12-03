package top.sanjeev.utils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Sanjeev
 * @version 1.0.0
 * @since 2024/12/3 15:44
 */
public class NumberUtils {

    /**
     * 移除列表中的重复元素并对列表进行排序。
     *
     * @param <T>     泛型类型，列表中元素的类型，必须实现Comparable接口
     * @param numbers 输入的列表，包含了可能的重复数字
     * @return 返回一个去除重复并按升序排序后的新列表
     * @throws NullPointerException 如果输入的列表为null
     */
    public static <T extends Comparable<T>> List<T> removeDuplicatesAndSort(List<T> numbers) {
        if (numbers == null) {
            throw new NullPointerException("输入的列表不能为 null");
        }
        return numbers.stream()
                .distinct()
                .sorted(Comparator.nullsFirst(Comparable::compareTo))
                .collect(Collectors.toList());
    }

}
