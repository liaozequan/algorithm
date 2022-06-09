package algorithm.search;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 实现二分查找（递归实现）
 * 插值查找（递归实现）
 */
public class BinarySearchDemo {
    public static void main(String[] args) {
        int [] nums = new int[12];
        for(int i=0; i<12; i++){
            nums[i] = i;
        }
        int i = BinarySearch(nums, 0, nums.length-1, 11);
        System.out.println(i);
    }

    public static int BinarySearch(int[] arr, int left, int right, int findVal){
        System.out.println("===");
        if(left<=right){
            int mid = left + (right-left)/2;
            if(arr[mid] < findVal){
                return BinarySearch(arr, mid+1, right, findVal);
            }
            if(arr[mid] > findVal){
                return BinarySearch(arr, left, mid-1, findVal);
            }
            if(arr[mid] == findVal){
                return mid;
            }
        }
        return -1;
    }

    public static int BinarySearchNoRecursion(int[] arr, int left, int right, int findVal){
        while(left<=right){
            int mid = left + (right - left)/2;
            if(arr[mid] == findVal){
                return mid;
            }else if(arr[mid] > findVal){
                right = mid - 1;
            }else if(arr[mid] < findVal){
                left = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 插值查找
     * 通过修改mid计算方式来提速
     */
    public static int BinarySearchByInsert(int[] arr, int left, int right, int findVal){
        System.out.println("+++");
        if(left>right){
            return -1;
        }
        int mid = left + (findVal-arr[left])/(arr[right]-arr[left])*(right-left);
        if(arr[mid] < findVal){
            return BinarySearchByInsert(arr, mid+1, right, findVal);
        }
        if(arr[mid] > findVal){
            return BinarySearchByInsert(arr, left, mid-1, findVal);
        }
        if(arr[mid] == findVal){
            return mid;
        }
        return -1;
    }
}
