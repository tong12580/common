package com.jokers.common.sort;

import java.util.List;

/**
 * 排序公共方法
 * 堆排序，希尔排序，冒泡排序，归并排序
 *
 * @author yuTong
 * @version 1.0
 * @since 2016/05/22
 */
@SuppressWarnings("unchecked")
public class SortUtil {

    private static Sort sortNum = new SortNumImpl();
    private static Sort sortStr = new SortStrImpl();

    /**
     * 归并排序
     * 时间复杂度为O(nlogn)
     * 速度仅次于快速排序，为稳定排序算法，一般用于对总体无序，但是各子项相对有序的数列。
     */
    public static <T> void mergeSort(List<T> list) {

        Object[] Array = list.toArray(new Object[list.size()]);
        if (Array[0] instanceof String) {
            sortStr.mergeSort(Array);
        } else {
            sortNum.mergeSort(Array);
        }

        list.clear();
        for (Object object : Array) {
            list.add((T) object);
        }
    }

    /**
     * 冒泡排序
     * 起泡排序平均时间复杂度为O(n2)
     * 稳定排序
     */
    public static <T> void bubbleSort(List<T> list) {

        Object[] Array = list.toArray(new Object[list.size()]);
        if (Array[0] instanceof String) {
            sortStr.bubbleSort(Array);
        } else {
            sortNum.bubbleSort(Array);
        }

        list.clear();
        for (Object object : Array) {
            list.add((T) object);
        }
    }

    /**
     * 希尔排序
     * 平均时间复杂度为O(nlogn)
     * 不稳定排序
     */
    public static <T> void ShellSort(List<T> list) {

        Object[] Array = list.toArray(new Object[list.size()]);
        if (Array[0] instanceof String) {
            sortStr.ShellSort(Array);
        } else {
            sortNum.ShellSort(Array);
        }

        list.clear();
        for (Object object : Array) {
            list.add((T) object);
        }
    }

    /**
     * 堆排序
     * 最坏时间复杂度为O(nlogn)
     * 不稳定排序
     */
    public static <T> void heapSort(List<T> list) {

        Object[] Array = list.toArray(new Object[list.size()]);
        if (Array[0] instanceof String) {
            sortStr.heapSort(Array);
        } else {
            sortNum.heapSort(Array);
        }

        list.clear();
        for (Object object : Array) {
            list.add((T) object);
        }
    }

    /**
     * 希尔排序
     * 平均时间复杂度为O(nlogn)
     * 不稳定排序
     */
    public static <T> void ShellSort(T[] Arrays) {

        Object[] Array = Arrays;
        if (Array[0] instanceof String) {
            sortStr.ShellSort(Array);
        } else {
            sortNum.ShellSort(Array);
        }
        int i = 0;
        for (Object object : Array) {
            Arrays[i] = ((T) object);
            i++;
        }
    }

    /**
     * 冒泡排序
     * 起泡排序平均时间复杂度为O(n2)
     * 稳定排序
     */
    public static <T> void bubbleSort(T[] Arrays) {

        Object[] Array = Arrays;
        if (Array[0] instanceof String) {
            sortStr.bubbleSort(Array);
        } else {
            sortNum.bubbleSort(Array);
        }
        int i = 0;
        for (Object object : Array) {
            Arrays[i] = ((T) object);
            i++;
        }
    }

    /**
     * 归并排序
     * 时间复杂度为O(nlogn)
     * 速度仅次于快速排序，为稳定排序算法，一般用于对总体无序，但是各子项相对有序的数列。
     */
    public static <T> void mergeSort(T[] Arrays) {

        Object[] Array = Arrays;
        if (Array[0] instanceof String) {
            sortStr.mergeSort(Array);
        } else {
            sortNum.mergeSort(Array);
        }
        int i = 0;
        for (Object object : Array) {
            Arrays[i] = ((T) object);
            i++;
        }
    }

    /**
     * 堆排序
     * 最坏时间复杂度为O(nlogn)
     * 不稳定排序
     */
    public static <T> void heapSort(T[] Arrays) {

        Object[] Array = Arrays;
        if (Array[0] instanceof String) {
            sortStr.heapSort(Array);
        } else {
            sortNum.heapSort(Array);
        }
        int i = 0;
        for (Object object : Array) {
            Arrays[i] = ((T) object);
            i++;
        }
    }

}
