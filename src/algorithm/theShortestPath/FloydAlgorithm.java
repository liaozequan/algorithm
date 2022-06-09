package algorithm.theShortestPath;

import java.util.Arrays;

/**
 * floyd算法，计算每一个节点到其他所有节点的最短路径
 */
public class FloydAlgorithm {
    public static void main(String[] args) {
        final int N = 65535;
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] weight = {
                {N, 5, 7, N, N, N, 2},
                {5,N, N,9,N, N,3},
                {7,N, N,N,8,N, N},
                {N,9, N, N, N,4,N},
                {N,N,8,N, N,5,4},
                {N, N, N,4,5, N, 6},
                {2,3,N,N,4,6,N}};
        FloydGraph floydGraph = new FloydGraph(vertexs.length, vertexs, weight);
        floydGraph.floyd();
        floydGraph.show();
    }
}

class FloydGraph{
    char[] vertexs; //存放顶点数组
    int[][] dis;    //保持各个顶点到其他所有节点距离，最后结果也保存在该数组
    int[][] pre;    //pre[i][j]保存从i节点到j节点路径中，j节点的前驱节点

    public FloydGraph(int len, char[] vertexs, int[][] dis) {
        this.vertexs = vertexs;
        this.dis = dis;
        this.pre = new int[len][len];
        //对pre初始化
        for(int i=0; i<len; i++){
            Arrays.fill(pre[i], i);
        }
    }

    public void show(){
        for(int i=0; i<dis.length; i++){
            for(int j=0; j<dis[i].length; j++){
                System.out.printf("%-6s", dis[i][j]);
            }
            System.out.println();
        }
        for(int i=0; i<pre.length; i++){
            for(int j=0; j<pre[i].length; j++){
                System.out.print(pre[i][j]);
            }
            System.out.println();
        }
    }

    public void floyd(){
        int len=0;
        //对中间顶点的遍历，k为中间顶点下标
        for(int k=0; k<dis.length; k++){
            //从i节点开始出发
            for(int i=0; i<dis.length; i++){
                //从i节点出发，经过k，到达j
                for(int j=0; j<dis.length; j++){
                    //计算i->k , k->j的距离之和
                    len = dis[i][k] + dis[k][j];
                    if(len < dis[i][j]){
                        //如果 i->k->j 路径的距离 < i->j 路径的距离，则更新dis[i][j](即更新i->j的最短距离)
                        dis[i][j] = len;
                        /**关键步骤：
                         * 更新i——>j路径中，j节点的前驱节点为 k——>j路径中j的前驱节点，
                         */
                        pre[i][j] = pre[k][j];
                    }
                }
            }

        }
    }
}
