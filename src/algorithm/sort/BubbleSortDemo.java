package algorithm.sort;

import java.util.Arrays;

public class BubbleSortDemo {
    public static void main(String[] args) {
        int [] nums = {-1,-5,39,2,10,11};
        nums = BubbleSort(nums);
        for(int i=0; i<nums.length; i++){
            System.out.print(nums[i]+",");
        }
        Arrays.sort(nums);
    }

    private static int[] BubbleSort(int[] nums){
        boolean flag = true;
        for(int i=nums.length; i>1 && flag; i--){
            for (int j=0; j<i-1; j++){
                flag = false;
                if(nums[j]>nums[j+1]){
                    int temp = nums[j+1];
                    nums[j+1] = nums[j];
                    nums[j] = temp;
                    flag = true;
                }
            }
        }
        return nums;
    }
}
