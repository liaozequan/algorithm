package algorithm.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class QuickSortDemo {
    public static void main(String[] args) {
        int [] nums = new int[200000];
        for(int i=0; i<200000; i++){
            nums[i] = (int) (Math.random()*1000000);
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss SS");
        String data1Str = simpleDateFormat.format(date1);
        System.out.println("排序前时间："+data1Str);
        QuickSort(nums, 0, nums.length-1);
        Date date2 = new Date();
        String data2Str = simpleDateFormat.format(date2);
        System.out.println("排序后时间："+data2Str);

        //System.out.println(Arrays.toString(nums));
    }

    public static void QuickSort(int arr[], int left, int right){
        int l = left, r = right;    //左右下标
        int pivot = arr[(l+r)/2];   //中轴值
        int temp = 0;
        //while循环的目的：让比pivot中轴值小的放到左边，比中轴值大的放到右边
        while (l < r){
            //while循环的目的:在pivot最左边开始一直找，直到找到比pivot大的值停止循环,如果找不到，则指向pivot
            while(arr[l]<pivot){
                l++;
            }
            //while循环的目的:在pivot最右边开始一直找，直到找到比pivot小的值停止循环,如果找不到，则指向pivot
            while(arr[r]>pivot){
                r--;
            }
            if(l >= r){
                //pivot两边没有比pivot大的或小的值,此时pivot左边都是小于pivot的值，右边都是大于pivot的值
                break;
            }
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            /**如果交换完，发现arr[l]==pivot，则说明pivot已经移动到了l指针的位置，而l指针左边都是比pivot小的值
             * 此时l指针指向pivot，说明pivot左边已经没有比pivot大的值了
             * 此时要继续遍历r指针，寻找pivot右边比pivot小的值
             */
            if(arr[l] == pivot){
                r--;
            }
            /**如果交换完，发现arr[r]==pivot，则说明pivot已经移动到了r指针的位置，而r指针右边都是比pivot大的值
             * 此时r指针指向pivot，说明pivot右边已经没有比pivot小的值了
             * 此时要继续遍历l指针，寻找pivot左边比pivot大的值
             */
            if(arr[r] == pivot){
                l++;
            }
        }
        //为避免重复比较，无限递归
        if(l == r){
            l++;r--;
        }
        if(left < r){
            QuickSort(arr, left, r);
        }
        if(l < right){
            QuickSort(arr, l, right);
        }


    }

}
