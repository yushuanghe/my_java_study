package com.shuanghe.algorithm;

/**
 * Description:二分法查找
 * <p>
 * Date: 2018/06/25
 * Time: 20:36
 *
 * @author yushuanghe
 */
public class DichotomySearch {
    public static void main(String[] args) {
        int[] arr = new int[]{12, 23, 34, 45, 56, 67, 77, 89, 90};
        System.out.println(search(arr, 12));
        System.out.println(search(arr, 45));
        System.out.println(search(arr, 67));
        System.out.println(search(arr, 89));
        System.out.println(search(arr, 99));

        System.out.println(search2(arr, 12));
        System.out.println(search2(arr, 45));
        System.out.println(search2(arr, 67));
        System.out.println(search2(arr, 89));
        System.out.println(search2(arr, 99));
    }

    public static int search(int[] arr, int key) {
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int middle = (start + end) / 2;
            if (key < arr[middle]) {
                end = middle - 1;
            } else if (key > arr[middle]) {
                start = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;
    }

    public static int search2(int[] arr, int key) {
        int start = 0;
        int end = arr.length - 1;
        while (end >= start) {
            int middle = (end + start) / 2;
            if (key > arr[middle]) {
                start = middle + 1;
            } else if (key < arr[middle]) {
                end = middle - 1;
            } else {
                return middle;
            }
        }
        return -1;
    }
}
