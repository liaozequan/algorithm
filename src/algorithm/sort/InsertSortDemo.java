package algorithm.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 插入排序
 */
public class InsertSortDemo {
    public static void main(String[] args) {
        int [] nums = new int[200000];
        for(int i=0; i<200000; i++){
            nums[i] = (int) (Math.random()*1000000);
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss SS");
        String data1Str = simpleDateFormat.format(date1);
        System.out.println("排序前时间："+data1Str);
        nums = InsertSort(nums);
        Date date2 = new Date();
        String data2Str = simpleDateFormat.format(date2);
        System.out.println("排序后时间："+data2Str);
//        for(int i=0; i<nums.length; i++){
//            System.out.print(nums[i]+" ");
//        }
    }

    private static int[] InsertSort(int[] nums){
        int insertIndex;
        int temp;
        for(int i=1; i<nums.length; i++){
            insertIndex = i-1;//插入位置指针
            temp = nums[i];//保存待插入数据
            while(insertIndex >=0 && temp < nums[insertIndex]){
                nums[insertIndex+1] = nums[insertIndex];
                insertIndex--;
            }

                nums[insertIndex+1] = temp;


        }
        return nums;
    }

    private static int[] InsertSortBy2(int[] nums){
        int insertIndex;
        int temp;
        for(int i=1; i<nums.length; i++){
            temp = nums[i];
            int low = 0, high = i-1, mid=0;
            while (low <= high){
                mid = low + (high - low) / 2;
                if(temp < nums[mid]){
                    high = mid-1;
                }else{
                    low = mid+1;
                }
            }

            for(int j=i-1; j>=low; j--){
                nums[j+1] = nums[j];
            }
            nums[low] = temp;
        }
        return nums;
    }
}
