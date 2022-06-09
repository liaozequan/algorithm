package algorithm.sort;

import java.util.Arrays;

public class MergeSortDemo {
    public static void main(String[] args) {
        int[] arr = {8,4,5,7,1,3,6,2};
        int[] temp = new int[arr.length];
        MergeSort(arr, 0, arr.length-1, temp);
        System.out.println(Arrays.toString(arr));

    }

    private static void MergeSort(int arr[], int left, int right, int temp[]){
        if (left<right){
            int mid = (left + right)/2;
            MergeSort(arr, left, mid, temp);
            MergeSort(arr, mid+1, right, temp);
            merge(arr, left, mid, right, temp);
        }
    }

    /**
     * 合并方法
     * 左右两个有序数列按从小到大依次放入temp，最后将没遍历完的（左or右）数列全部放入temp
     * @param arr 排序的初始数组
     * @param left  左边有序数列的初始索引
     * @param mid   右边有序数列在arr中的初始索引的前一个索引
     * @param right
     * @param temp
     */
    private static void merge(int arr[], int left, int mid, int right, int temp[]){
        int i = left;   //左边有序数列的初始索引
        int j = mid+1;  //指向右边有序数列的初始索引
        int tempIndex = 0;  //temp数组索引
        //(1)左右两个有序数列按大小放入temp，直到两个有序数列有一个处理完
        while(i<=mid && j<=right){
            if(arr[i] <= arr[j]){
                temp[tempIndex] = arr[i];
                tempIndex++; i++;
            }else{
                temp[tempIndex] = arr[j];
                tempIndex++; j++;
            }
        }

        //(2)将有剩余的一边数列全部放入temp
        while (i<=mid){
            //如果左边有序序列有剩余，就全部放入temp
            temp[tempIndex++] = arr[i++];
        }
        while (j<=right){
            //如果右边有序序列有剩余，就全部放入temp
            temp[tempIndex++] = arr[j++];
        }

        //(3)将temp拷贝到原数组
        tempIndex = 0;
        int copyIndex = left;//拷贝到原数组的初始位置
        while (copyIndex <= right){
            arr[copyIndex++] = temp[tempIndex++];
        }

    }
}
