package algorithm.minCostTree;

import java.util.Arrays;

/**
 * 最小生成树（Kruskal算法）
 */
public class KruskalAlgorithm {
    //当图的权值为INF时，表示不联通
    private static final int INF = Integer.MAX_VALUE;
    private static int edgeNum;
    public static void main(String[] args) {
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int vertxs = data.length;
        int[][] weight = {
                {0, 12, INF, INF, INF, 16, 14},
                {12, 0, 10, INF, INF, 7, INF},
                {INF, 10, 0, 3, 5, 6, INF},
                {INF, INF, 3, 0, 4, INF, INF},
                {INF, INF, 5, 4, 0, 2, 8},
                {16, 7, 6, INF, 2, 0, 9},
                {14, INF, INF, INF, 8, 9,0}};
        MGraph mGraph = new MGraph(vertxs);
        MinTree minTree = new MinTree();
        minTree.createMGraph(mGraph, vertxs, data, weight);
        minTree.show(mGraph);
        getEdgeNum(weight);
        Edata[] edges = getEdges(weight, data);
        System.out.println(Arrays.toString(edges));
        kruskal(data, weight, edges);
    }

    //获得边的数量
    public static void getEdgeNum(int[][] weight){
        for(int i=0; i<weight.length; i++){
            for(int j=i+1; j< weight.length; j++){
                if(weight[i][j] != INF){
                    edgeNum++;
                }
            }
        }
    }

    /**功能：从邻接矩阵weight中获取所有边，实例化成Edata，放入Edata[]数组中
     * @param weight    邻接矩阵
     * @param data 存放顶点的数组
     * @return  Edata[]数组，型如:[['A','B',12], ['A','F',16].....]
     */
    private static Edata[] getEdges(int[][] weight, char[] data){
        int index = 0;
        Edata[] edges = new Edata[edgeNum];
        for(int i=0; i<weight.length; i++){
            for(int j=i+1; j< weight.length; j++){
                if(weight[i][j] != INF){
                    edges[index++] = new Edata(data[i], data[j], weight[i][j]);
                }
            }
        }
        return edges;
    }

    //对边按权值进行排序（冒泡排序）-->递减序列
    private static void sortEdges(Edata[] edata){
        for(int i=0; i<edata.length-1; i++){
            for(int j=0; j<edata.length-i-1; j++){
                if(edata[j].weight > edata[j+1].weight){
                    Edata temp = edata[j+1];
                    edata[j+1] = edata[j];
                    edata[j] = temp;
                }
            }
        }
    }

    /** 给一个顶点，返回顶点在data[]的下标
     * @param v 比如给"A"
     * @return  返回下标0，因为data[0] = "A",如果找不到，返回-1
     */
    private static int getPosition(char v, char[] data){
        for(int i=0; i<data.length; i++){
            if(data[i] == v){
                return i;
            }
        }
        return -1;
    }

    /**获取start起点节点的终点节点，终点节点表示：从start开始能联通到字母最大的节点
     * 用于判断是否形成回路，如果两个起点的终点相同，则不能将这两个点连成的边加入到最小生成树中，不然会导致形成回路
     * @param ends ends[start] 表示start的终点节点下标。ends数组是在将边加入最小生成树过程中动态变化的，如A刚开始的终点可能是B，在加入边到最小生成树后，终点可能变成F
     * @param start 起始节点
     * @return  下标为start节点的终点节点在data[]下标
     */
    private static int getEnd(int[] ends, int start){
        while(ends[start] != 0){
            start = ends[start];
        }
        return start;
    }

    public static void kruskal(char data[], int[][] weight, Edata[] edges){
        int index = 0;  //最终结果数组的索引
        int[] ends = new int[data.length];  //保存已有 最小生成树的每个顶点在树中的终点
        Edata[] rets = new Edata[edgeNum];  //保存最终最小生成树边集合
        sortEdges(edges);   //从小到大给边排序
        //将边添加到最小生成树，需要判断是否构成回路,如果不构成回路，则加入rets中，否则遍历下一个
        for(int i=0; i<edgeNum; i++){
            //获取第i条边的第一个顶点
            int p1 = getPosition(edges[i].start, data);
            //获取第i条边的第二个顶点
            int p2 = getPosition(edges[i].end, data);
            //获取p1、p2在已有的最小生成树中的终点，判断是否相同，如果相同，则p1、p2构成的边加入树中会形成回路，就不能将这条边加入最小生成树
            //在没有回路的情况下，遍历到最后一定每个节点会联通
            int end1 = getEnd(ends, p1);
            int end2 = getEnd(ends, p2);
            if(end1 != end2){
                //将p1节点在最小生成树的终点指向p2节点.
                //不需要考虑C->A的情况下，C的终点会是A，因为遍历邻接矩阵是从A开始按字母顺序的。所以边的情况一定是（小字母-->大字母）
                ends[p1] = p2;
                rets[index++] = edges[i];//将符合的最小边加入到结果集中
            }
        }

        System.out.println(Arrays.toString(rets));


    }

}

//创建一个Edata类，表示边
class Edata{
    char start; //边的起点
    char end;   //边的另一个点
    int weight;

    public Edata(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Edata{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }
}
