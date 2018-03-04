package com.jokers.common.sort;

/**
 * Number类型排序
 *
 * @author yuTong
 * @version 1.0
 * @since 2016/05/22
 */
public class SortNumImpl implements Sort {

    /** {@inheritDoc} */
    @Override
    public void heapSort(Object[] Array) {

        int arrayLength = Array.length;
        for (int i = 0; i < arrayLength - 1; i++) {
            buildMaxHeap(Array, arrayLength - 1 - i);
            swap(Array, 0, arrayLength - 1 - i);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void bubbleSort(Object[] Array) {

        for (int i = 0; i < Array.length; i++) {
            for (int j = 0; j < Array.length - i - 1; j++)
                if (Long.parseLong(Array[j].toString()) > Long.parseLong(Array[j + 1].toString())) {
                    Object temp = Array[j];
                    Array[j] = Array[j + 1];
                    Array[j + 1] = temp;

                }
        }

    }

    /** {@inheritDoc} */
    @Override
    public void mergeSort(Object[] Array) {

        int left = 0;
        int right = Array.length - 1;

        leftAndRight(Array, left, right);

    }

    /** {@inheritDoc} */
    @Override
    public void ShellSort(Object[] Array) {

        int d = Array.length;
        while (d != 1) {
            d = d / 2;
            for (int x = 0; x < d; x++) {
                for (int i = x + d; i < Array.length; i = i + d) {
                    Object temp = Array[i];
                    int j;
                    for (j = i - d; j >= 0 && Long.parseLong(Array[j].toString()) > Long.parseLong(temp.toString()); j = j - d) {
                        Array[j + d] = Array[j];
                    }
                    Array[j + d] = temp;
                }
            }
        }

    }

    private static void merge(Object[] Array, int left, int middle, int right) {

        Object[] tmpArr = new Object[Array.length];

        int mid = middle + 1; //右边的起始位置
        int tmp = left;
        int third = left;

        while (left <= middle && mid <= right) {
            //从两个数组中选取较小的数放入中间数组
            if (Long.parseLong(Array[left].toString()) < Long.parseLong(Array[mid].toString())) {
                tmpArr[third++] = Array[left++];
            } else {
                tmpArr[third++] = Array[mid++];
            }
        }
        //将剩余的部分放入中间数组
        while (left <= middle) {
            tmpArr[third++] = Array[left++];
        }

        while (mid <= right) {
            tmpArr[third++] = Array[mid++];
        }

        //将中间数组复制回原数组
        while (tmp <= right) {
            Array[tmp] = tmpArr[tmp++];
        }
    }

    private static void leftAndRight(Object[] Array, int left, int right) {

        if (left < right) {

            int middle = (left + right) / 2;

            //左递归
            leftAndRight(Array, left, middle);

            //右递归
            leftAndRight(Array, middle + 1, right);

            //合并
            merge(Array, left, middle, right);

        }
    }

    //对data数组从0到lastIndex建大顶堆
    /**
     * <p>buildMaxHeap.</p>
     *
     * @param data an array of {@link java.lang.Object} objects.
     * @param lastIndex a int.
     */
    public static void buildMaxHeap(Object[] data, int lastIndex) {
        //从lastIndex处节点（最后一个节点）的父节点开始
        for (int i = (lastIndex - 1) / 2; i >= 0; i--) {
            //k保存正在判断的节点 
            int k = i;
            //如果当前k节点的子节点存在  
            while (k * 2 + 1 <= lastIndex) {
                //k节点的左子节点的索引 
                int biggerIndex = 2 * k + 1;
                //如果biggerIndex小于lastIndex，即biggerIndex+1代表的k节点的右子节点存在
                if (biggerIndex < lastIndex) {
                    //若果右子节点的值较大  
                    if (Long.parseLong(data[biggerIndex].toString()) < Long.parseLong(data[biggerIndex + 1].toString())) {
                        //biggerIndex总是记录较大子节点的索引  
                        biggerIndex++;
                    }
                }
                //如果k节点的值小于其较大的子节点的值  
                if (Long.parseLong(data[k].toString()) < Long.parseLong(data[biggerIndex].toString())) {
                    //交换他们  
                    swap(data, k, biggerIndex);
                    //将biggerIndex赋予k，开始while循环的下一次循环，重新保证k节点的值大于其左右子节点的值  
                    k = biggerIndex;
                } else {
                    break;
                }
            }
        }
    }

    //交换
    private static void swap(Object[] data, int i, int j) {
        Object tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

}
