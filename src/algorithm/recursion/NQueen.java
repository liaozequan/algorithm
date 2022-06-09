package algorithm.recursion;

/**
 * N皇后问题，递归实现
 */
public class NQueen {
    static int Num = 8;//8个皇后
    //保存皇后放置位置，（index-1, res[index-1]）表示第index个皇后放在第index行，第res[index-1]列
    static int[] res = new int[Num];
    static int count = 0;
    public static void main(String[] args) {
        check(0);
        System.out.println("共"+count+"种解法");
    }

    //放置第n个皇后
    private static void check(int n){
        if(n == Num){   //所有皇后放置完毕
            show(res);
            return;
        }
        for (int i=0; i<Num; i++){  //i代表第n个皇后放置的i列
            //先把第n个皇后放置n行第1列
            res[n] = i;
            if(judge(n)){
                //放置第n个皇后不冲突，这继续放置第n+1个皇后
                check(n+1);
            }
        }

    }

    //当放置第n个皇后时，判断位置是否与其他所有已放置皇后的位置合法
    private static boolean judge(int n){
        for (int i=0; i<n; i++){
            //是否在同一列 || 是否在同一对角线
            if(res[i] == res[n] || Math.abs(n-i) == Math.abs(res[n]-res[i])){
                return false;
            }
        }
        return true;
    }

    //将结果输出
    private static void show(int[] res){
        count++;
        for(int i=Num-1; i>=0; i--){
            for(int j=0; j<Num; j++){
                if(j==res[i]){
                    System.out.print(1+" ");
                }
                System.out.print(0+" ");
            }
            System.out.println();

        }
        System.out.println();
    }

}
