package algorithm.theShortestPath;

import java.util.Arrays;

/**
 * Dijkstra算法求最短路径
 * 求指定的一个顶点到其他节点的最短路径
 */
public class DijkstraAlgorithm {
    public static void main(String[] args) {
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        final int N = 65535;
        int[][] matrix = {
                {N, 5, 7, N, N, N, 2},
                {5,N, N,9,N, N,3},
                {7,N, N,N,8,N, N},
                {N,9, N, N, N,4,N},
                {N,N,8,N, N,5,4},
                {N, N, N,4,5, N, 6},
                {2,3,N,N,4,6,N}};
        Graph graph = new Graph(vertexs , matrix);
        graph.show();
        graph.Dijkstra(2);
    }


}
//已经访问的顶点
class VisitedVertex{
    //记录顶点是否被访问过
    public int[] visited;
    //i顶点的上一个顶点的下标
    public int[] preVisited;
    //记录从出发顶点到其他顶点的距离，最短的距离存放在dis[]中
    public int[] dis;

    /** 构造函数
     * @param len   顶点个数
     * @param index 出发顶点的下标,比如出发点是B，index=1
     */
    public VisitedVertex(int len, int index) {
        this.visited = new int[len];
        this.preVisited = new int[len];
        this.dis = new int[len];
        //初始化dis[]，所有距离都设置为最大
        Arrays.fill(dis, 65535);
        //出发顶点到自身距离为0
        this.dis[index] = 0;
        //出发顶点设为已访问
        this.visited[index] = 1;
        this.preVisited[index] = -1;
    }


    /**假如经过Graph.update方法后，已经把某个节点到其他未访问节点的最短距离放入dis[]中
     * getNext()的功能是：从dis[]数组中找到最短距离的点，作为下一个节点
     * 如G->A距离比G->其他节点都短， 则A就做为下一个节点
     * @return  下一个节点（例子中的A节点）
     */
    public int getNext(){
        int min = 65535;
        int index = 0;
        for (int i=0; i<visited.length; i++){
            if(visited[i] == 0 && dis[i] < min){
                min = dis[i];
                index = i;
            }
        }
        visited[index] = 1;
        return index;
    }

}
class Graph{
    char[] vertexs; //顶点数组
    int[][] matrix; //邻接矩阵
    VisitedVertex vv; //已经访问的顶点

    public Graph(char[] vertexs, int[][] matrix) {
        this.vertexs = vertexs;
        this.matrix = matrix;
    }

    public void show(){
        for(int i=0; i<vertexs.length; i++){
            for (int j=0; j<vertexs.length; j++){
                System.out.printf("%-6s", matrix[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Dijkstra算法
     * @param index 出发顶点下标
     */
    public void Dijkstra(int index){
        this.vv = new VisitedVertex(vertexs.length, index);
        //通过起点到其他为被访问节点的距离，更新 vv 对象中的dis[], preVisited[]
        update(index);
        for(int j=1; j<vertexs.length; j++){    //起点已经被访问过，只需找其他的节点路径，因此j从1开始
            index = vv.getNext();   //选择并返回新的访问顶点
            update(index);  //通过index节点继续更新 vv 对象的dis[], preVisited[]
        }
        showRes();
    }

    /**
     * 更新 已访问节点（index）-->其他可联通节点的距离、前驱节点（VisitedVertex类）
     * @param index 已经访问的节点
     */
    private void update(int index){
        int len = 0;
        //遍历index节点所能联通的所有节点
        for(int j = 0; j<matrix[index].length; j++){
            //len表示: 起点 到index节点的最短距离 + index节点到 j节点的距离
            len = vv.dis[index] + matrix[index][j];
            if(vv.visited[j] == 0 && len < vv.dis[j]){
                //如果 j节点 未被访问过， 且(起点到index节点的最短距离+index节点到j节点的距离) < 起点到j节点的距离
                //这 起点-->index节点-->j节点 的距离可以放入dis[]，做为起点-->j节点的暂时最短路径（可能还有更短的）
                vv.dis[j] = len;
                //暂不将j设为已访问 vv.visited[j] = 1;
                //更新j节点的前驱节点为index节点
                vv.preVisited[j] = index;
            }
        }
    }

    //显示最后结果
    public void showRes(){
        for(int i=0; i< vertexs.length; i++){
            System.out.print("通向"+vertexs[i]+"节点的最短路径=");
            showPath(vv.preVisited[i]);
            System.out.print(vertexs[i]);
            System.out.print("  最短距离=" + vv.dis[i]);
            System.out.println();
        }

    }

    void showPath(int i){
        if(i == -1){
            return;
        }
        showPath(vv.preVisited[i]);
        System.out.print(vertexs[i]+"->");
    }
}
