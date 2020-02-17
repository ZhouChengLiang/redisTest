package org.zcl.mytest.poiExcel;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BinarySearch {
    
    static int binarysearch(int array[], int low, int high, int target) {
        if (low > high) return -1;
        int mid = low + (high - low) / 2;
        if (array[mid] > target)
            return binarysearch(array, low, mid - 1, target);
        if (array[mid] < target)
            return binarysearch(array, mid + 1, high, target);
        return mid;
    }
    
    // 有序数组
    static int binarySearchWithoutRecursion(int array[], int key) {
        int low = 0;
        int high = array.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (array[mid] > key)
                high = mid - 1;
            else if (array[mid] < key)
                low = mid + 1;
            else
                return mid;
        }
        return -1;
    }
    
    public static void bubbleSort(int[] arr, int n) {
        if (n <= 1) return;       //如果只有一个元素就不用排序了
        for (int i = 0; i < n; ++i) {
            // 提前退出冒泡循环的标志位,即一次比较中没有交换任何元素，这个数组就已经是有序的了
            boolean flag = false;
            for (int j = 0; j < n - i - 1; ++j) {        //此处你可能会疑问的j<n-i-1，因为冒泡是把每轮循环中较大的数飘到后面，
                // 数组下标又是从0开始的，i下标后面已经排序的个数就得多减1，总结就是i增多少，j的循环位置减多少
                if (arr[j] > arr[j + 1]) {        //即这两个相邻的数是逆序的，交换
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = true;
                }
            }
            if (!flag) break;//没有数据交换，数组已经有序，退出排序
        }
    }
    
    public static void bubbleSort2(int[] arr, int n) {
        if (n <= 1) return;
        for (int i = 0; i < n; i++) {
            boolean flag = false;
            for (int j = 0; j < n - i - 1; j++) {        
                if (arr[j] > arr[j + 1]) {        
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = true;
                }
            }
            if (!flag) break;//没有数据交换，数组已经有序，退出排序
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{1,3,5,7,9,13,15,21};
        System.out.println(binarysearch(array, 0, array.length, 13));
        System.out.println(binarySearchWithoutRecursion(array,13));
        
        int[] array2 = new int[]{41,15,2,14,6,7,5,1};
        bubbleSort2(array2, array2.length);
        List<Integer> list = Arrays.stream(array2).map(a->Integer.valueOf(a)).boxed().collect(Collectors.toList());
        System.out.println(list);
    }
}
