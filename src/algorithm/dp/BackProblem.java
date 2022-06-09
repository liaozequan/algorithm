package algorithm.dp;

/**
 * 动态规划:01背包问题
 */
public class BackProblem {
    public static void main(String[] args) {
        int[] w = {1, 4, 3};        //物品重量：如w[0]=1表示第一个商品的重量是1
        int[] v = {1500, 3000, 2000};//物品价值，如如w[0]=1表示第一个商品的价值是1500
        int c = 4;                  //背包容量
        int n = w.length;           //物品的数量

        //dp[i][j] 表示前i个商品装在容量为j的背包中，所有装配方案中的最大价值
        int[][] dp = new int[n+1][c+1]; //加0行和0列
        /**初始化第一行第一列
         * 第一行数据 dp[0][j] 表示：前0个物品（就是没有商品）在所有容量的背包中的价值都是0
         * 第一列数据 dp[i][0] 表示：所有的商品在容量为0的背包中价值都是0，即有商品也无法装配
         */
        for(int j=0; j<dp[0].length; j++){
            dp[0][j] = 0;
        }
        for(int i=0; i<dp.length; i++){
            dp[i][0] = 0;
        }

        for(int i=1; i<dp.length; i++){
            for(int j=1; j<dp[0].length; j++){
                if(w[i-1]>j){   //w[i-1]：表示第i个商品在重量数组w的下标是i-1
                    //如果第i个商品的重量大于当前背包的容量j，则使用上一个商品所总结出的背包容量j能装配的最大价值
                    dp[i][j] = dp[i-1][j];
                }else{
                    /**如果第i个商品的重量 <= 当前背包的容量j
                     * 则比较 <上一个商品所总结出的背包容量j能装配的最大价值>
                     * 和 <当前商品价值v[i-1]> + <上一个商品所总结出（背包容量j - 当前商品重量w[i-1]）能装配的最大价值>
                     * 大的作为前i个商品装在容量为j的背包中，所有装配方案中的最大价值
                     */
                    dp[i][j] = Math.max(dp[i-1][j], v[i-1]+dp[i-1][j-w[i-1]]);
                }
            }
        }


        for(int i=0; i<dp.length; i++){
            for(int j=0; j<dp[i].length; j++){
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }

        System.out.println("最大价值：" + dp[n][c]);


    }
}
