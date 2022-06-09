package algorithm.search;

import java.util.Arrays;

public class FibonacciSearchDemo {
    public static int maxSize = 20;
    public static void main(String[] args) {

    }

    //返回斐波那契数列
    public static int[] fib(){
        int[] fib = new int[maxSize];
        fib[0] = 1;
        fib[1] = 1;
        for(int i=2; i<maxSize-1; i++){
            fib[i] = fib[i-1] + fib[i-2];
        }
        return fib;
    }

    /**斐波那契查找算法
     * mid = low + fib(k-1)-1
     */
    public static int FibonacciSearch(int[] arr, int findVal){
        int low = 0;
        int high = arr.length-1;
        int k = 0;//表示
        int mid = 0;
        int[] fib = fib();//得到斐波那契数列
        while(high > fib[k]-1){//找到斐波那契数列中大于等于待查询数组长度的下标值
            k++;
        }
        //因为fib[k]的值可能大于待查询数组长度,将原数组copy到temp中且超出部分补0
        int[] temp = Arrays.copyOf(arr, fib[k]);
        //使用arr最后一个数字填充
        for(int i=arr.length; i<temp.length; i++){
            temp[i] = arr[arr.length-1];
        }
        while(low < high){
            mid = low + fib[k-1]-1;
            if(findVal < temp[mid]){
                high = mid-1;
                k-=1;
            }else if(findVal < temp[mid]){
                low = mid+1;
                k-=2;
            }else{
                //因为temp是加长过的，所以防止下标初阶，需判断
                if(mid <= high){
                    return mid;
                }else{
                    return high;
                }
            }
        }

        return -1;


    }
}
