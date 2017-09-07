package com.jokers.common.sort;

/**
 * 排序算法
 * @author yuton
 *
 */
public interface Sort {
	
	/**
	 * 堆排序
	 * 最坏时间复杂度为O(nlogn)
	 * 不稳定排序
	 * @param Array
	 */
	void heapSort(Object[] Array);
	
	/**
	 * 冒泡排序
	 * 起泡排序平均时间复杂度为O(n2)
	 * 稳定排序
	 * @param Array
	 */
	void bubbleSort(Object[] Array);
	
	/**
	 * 归并排序
	 * 时间复杂度为O(nlogn)
	 * 速度仅次于快速排序，为稳定排序算法，一般用于对总体无序，但是各子项相对有序的数列。
	 * @param Array
	 */
	void mergeSort(Object[] Array);
	
	/**
	 * 希尔排序
	 * 平均时间复杂度为O(nlogn)
	 * 不稳定排序
	 * @param Array
	 */
	void ShellSort(Object[] Array);
}
