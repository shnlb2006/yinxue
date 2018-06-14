package org.yinxueframework.util;

import java.util.Arrays;

/**
 * 各类排序
 *
 * @author zengjian
 * @create 2018-04-08 14:39
 * @since 1.0.0
 */
public abstract class SortUtil {

    /**
     * 选择排序
     *
     * @param array
     * @return
     */
    public static void selectSort(int[] array) {
        int temp = 0;
        int minIndex = 0;
        for (int i = 0; i < array.length - 1; i++) {
            minIndex = i;
            // 循环比较一次找到最小值下标
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
    }


    /**
     * 冒泡排序
     *
     * @param array
     */
    public static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i; j < array.length - 1 - i; j++) {
                // 两两比较，后面的比前面的小就交换一次
                if (array[j + 1] < array[j]) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    /**
     * 希尔排序
     *
     * @param arr
     */
    public static void shellSort(int[] arr) {
        int step = arr.length;
        int count1 = 0;
        int count2 = 0;
        while ((step /= 2) != 0) {
            for (int i = step; i < arr.length; i++) {
                for (int j = i - step; j >= 0; j -= step) {
                    if (arr[j] > arr[j + step]) {
                        int temp = arr[j];
                        arr[j] = arr[j + step];
                        arr[j + step] = temp;
                    }
                    count2++;
                }
                count1++;
            }
        }
        System.out.println(count1);
        System.out.println(count2);
    }

    public static void InsertSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            // 比较值
            int temp = arr[i + 1];
            for (int j = i + 1; j > 0; j--) {
                // 前面已排序的数如果大于比较值就往后退一格
                if (arr[j - 1] > temp) {
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
            }
        }
    }


    /**
     * 归并排序(需要两个函数，一个递归的分解，一个合并)
     *
     * @param arr
     * @return
     */
    public static void mergerSort(int[] arr, int low, int high) {
        int mid = (low + high) / 2;
        if (low < high) {
            mergerSort(arr, 0, mid);
            mergerSort(arr, mid + 1, high);
            merger(arr, mid, high);
        }
    }

    public static void merger(int[] arr, int mid, int high) {
        // 折半归并
        int i = 0;
        int j = mid;
        int count = 0;

        int[] newArr = new int[high];
        while (i < mid && j < high) {
            if (arr[i] > arr[j]) {
                newArr[count++] = arr[j++];
            } else {
                newArr[count++] = arr[i++];
            }
        }

        while (i < mid) {
            newArr[count++] = arr[i++];
        }

        while (j < high) {
            newArr[count++] = arr[j++];
        }

        for (int k = 0; k < high; k++) {
            arr[k] = newArr[k];
        }
    }

    public static void quickSort(int[] arr){
        int key = arr[0];
        for (int i = 0; i < arr.length; i++) {

        }
    }

    public static void heapSort(int[] arr){

    }

    public static void radixSort(int[] arr){

    }

}
