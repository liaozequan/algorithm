package algorithm.sort;

import java.util.Arrays;

/**
 * 堆排序，数组二叉树实现
 * 第一步:将待排序的树（数组实现），调整为大顶堆：
 *      1.找到最后一个非叶子节点(数组长度/2-1)，比较它与其子节点大小，将最大的替换为该节点
 *      2.被替换的节点的子节点有可能有比它更大的，因此需要继续往深处寻找最大节点
 *      3.如果没有，则继续往上找非叶子节点，重复1，2
 * 第二步:输出堆顶：
 *      1.将堆顶与堆树的最后一个叶子节点互换
 *      2.数组大小-1（去除掉已输出的节点）
 *      3.重新调整为大顶堆:
 *          3.1:从堆顶开始比较其两棵子节点，大的换到堆顶
 *          3.2:走第一步.2.
 */
public class HeapSortDemp {
    public static void main(String[] args) {
        int arr[] = {4,6,8,5,9};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    //
    public static void heapSort(int[] arr){
        int temp=0;
        //堆排序第一步：将整个堆调整成大顶堆
        for(int i = arr.length/2-1; i>=0; i--){
            adjustHeap(arr, i, arr.length);
        }
        //堆排序第一步：循环做(输出堆顶元素，交换堆顶和最后一个元素，数组大小-1，并重新调整成大顶堆)
        for(int j=arr.length-1; j>0; j--){
            System.out.println(arr[0]);
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            //交换后从堆顶开始调整，数组长度-1（即j）
            adjustHeap(arr, 0, j);

        }
    }

    /**
     * 将一个数组（二叉树），以i节点为根节点，调整成大顶堆
     * 举例：int arr[]={4,6,8,5,9},i=1,调用adjustHeap后==>{4,9,8,5,6}
     * 再调用adjustHeap，i=0,后==>{9,6,8,5,4}
     * @param arr   待调整的数组（二叉树）
     * @param i     非叶子节点在数组中的索引
     * @param len   对多少个元素进行调整（每一轮出堆，数组大小都会减少1）
     */
    public static void adjustHeap(int arr[], int i, int len){
        int temp = arr[i];
        //k为i节点的左子节点
        for(int k=2*i+1; k<len; k=2*k+1){
            //找到两个子节点中最大的节点
            if(k+1 < len && arr[k]<arr[k+1]){
                //如果左子节点小于右子节点
                k++;    //k指向右子节点（指向最大值）
            }
            if(arr[k] > temp){
                //如果右子节点大于i节点(k的父节点)
                arr[i] = arr[k];    //k节点替代i（父）节点
                i = k;  //继续往下循环
            }else{
                break;
            }
        }
        arr[i] = temp;
    }
}
