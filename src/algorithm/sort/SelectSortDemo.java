package algorithm.sort;

/**
 * 选择排序
 * 第一轮：选择arr[0:arr.length]中最小的放arr[0]
 * 第一轮：选择arr[1:arr.length]中最小的放arr[1]
 */
public class SelectSortDemo {
    public static void main(String[] args) {
        int [] nums = {39,12,2,11};//12 39 2 11/2 39 12 11/2 39 11 12
    }
    private static int[] SelectSort(int[] nums){
        int k,j;    //k每一轮最小值的下标
        for(int i=0; i<nums.length-1; i++){
            for(j=i+1,k=i; j<nums.length; j++){
				//找到每一轮最小的下标
                if(nums[k]>nums[j]){
                    k=j;
                }
            }
			//交换，，如果最小的是第一个则不交换
			if(k!=i){
                    int temp = nums[k];
                    nums[k] = nums[i];
                    nums[i] = temp;
            }
        }
        return nums;
    }
}
