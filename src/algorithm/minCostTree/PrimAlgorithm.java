package algorithm.minCostTree;

import java.util.Arrays;

/**
 * 最小生成树（prim算法）
 */
public class PrimAlgorithm {
    public static void main(String[] args) {
        char[] data = {'A','B','C','D','E','F','G'};
        int vertxs = data.length;
        //距离10000表示不联通
        int[][] weight = {
                {10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000}};
        MGraph mGraph = new MGraph(vertxs);
        MinTree minTree = new MinTree();
        minTree.createMGraph(mGraph, vertxs, data, weight);
        minTree.show(mGraph);
        minTree.prim(mGraph, 0);
    }



}

//创建最小生成树
class MinTree{
    //创建图的邻接矩阵
    public void createMGraph(MGraph mGraph, int vertxs, char[] data, int[][] weight){
        int i,j;
        for(i=0; i<vertxs; i++){
            mGraph.data[i] = data[i];
            for(j=0; j<vertxs; j++){
                mGraph.weight[i][j] = weight[i][j];
            }
        }
    }

    //输出图
    public void show(MGraph mGraph){
        for(int i=0; i<mGraph.vertxs; i++){
            for (int j=0; j<mGraph.vertxs; j++){
                System.out.printf("%-12s", mGraph.weight[i][j]);
            }
            System.out.println();
        }
//        for(int[] link : mGraph.weight){
//            System.out.printf("|%-15s|/n", Arrays.toString(link));
//        }
    }

    /** prim算法
     * @param mGraph 原图
     * @param v 开始生成最小生成树的顶点下标，如从A点开始生成，v=0   <数组data[]>
     */
    public void prim(MGraph mGraph, int v){
        //visited 表示节点是否已放入树中  visited[]=1表示已放入树
        int[] visited = new int[mGraph.vertxs];
        //开始节点设置为已放入树中
        visited[v] = 1;
        int h1 = -1;
        int h2 = -1;
        int minWeight = 10000;
        for(int k=1; k<mGraph.vertxs; k++){ //因为有mGraph.vertxs个顶点，最小生成树一定只有mGraph.vertxs -1条边，因此k从1开始
            //寻找已放入最小生成树的节点中，和哪个未放入节点最近
            for(int i=0; i<mGraph.vertxs; i++){ //i节点表示已放入树的节点
                for(int j=0; j<mGraph.vertxs; j++){ //j节点表示未放入树的节点
                    if(visited[i] == 1 && visited[j] == 0 && mGraph.weight[i][j] < minWeight){
                        //当i节点是已在最小生成树的节点 且 j节点不在最小生成树中 且 i、j的距离比minWeight小，则更新minWeight，并用h1h2记录两个节点
                        //只找为加入最小生成树的节点，因此不会出现回路
                        minWeight = mGraph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            //当退出第二个循环后就表示找到了一个节点，放入了最小生成树
            System.out.println("边<"+mGraph.data[h1]+", "+mGraph.data[h2]+"> 权值="+minWeight);
            //将加入的节点设为visited[] = 1
            visited[h2] = 1;
            //重置minWeight
            minWeight = 10000;
        }
    }
}

//创建图
class MGraph{
    int vertxs; //表示图的节点个数
    char[] data; //存放节点数据
    int[][] weight; //邻接矩阵

    public MGraph(int vertxs) {
        this.vertxs = vertxs;
        data = new char[vertxs];
        weight = new int[vertxs][vertxs];
    }
}