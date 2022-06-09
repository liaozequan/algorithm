package dataStructure.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 图，邻接矩阵实现
 * DFS（需要递归）,BFS（不需递归）
 */
public class GraphDemo {
    private ArrayList<String> vertexList;   //存储顶点集合
    private int[][] edges;  //存储图对应的邻接矩阵
    private int numOfEdge;  //存储边的数目
    private boolean[] isVisit;  //记录节点是否被访问过
    public static void main(String[] args) {
        int n=5;
        String[] vertexValue = {"A", "B","C","D","E"};
        GraphDemo graph = new GraphDemo(n);
        for(String value : vertexValue){
            graph.insertVertex(value);
        }
        graph.insertEdge(0,1,1);//A-B
        graph.insertEdge(0,2,1);//A-C
        graph.insertEdge(1,2,1);//B-C
        graph.insertEdge(1,3,1);//B-D
        graph.insertEdge(1,4,1);//B-E
        graph.showGraph();
        //graph.dfs();
        graph.bfs();
    }

    public GraphDemo(int n) {
        edges = new int[n][n];  //根据顶点个数创建邻接矩阵
        vertexList = new ArrayList<String>();
        numOfEdge = 0;
        isVisit = new boolean[n];
    }

    //插入节点
    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }

    /**
     * 添加边
     * @param v1 顶点1在邻接矩阵的指针
     * @param v2 顶点2
     * @param weight 两个顶点的权值
     */
    public void insertEdge(int v1, int v2, int weight){
        //无向图需要互相指向
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdge++;
    }

    //返回节点个数
    public int getNumOfVertex(){
        return vertexList.size();
    }

    //返回节点“i”对应的数据：0->"A", 1->"B"
    public String getValueByIndex(int i){
        return vertexList.get(i);
    }

    //返回v1和v2的权值
    public int getWeightBy2V(int v1, int v2){
        return edges[v1][v2];
    }

    //显示邻接矩阵
    public void showGraph(){
        for(int[] link : edges){
            System.out.println(Arrays.toString(link));
        }
    }

    /**得到第一个邻接节点的下标
     * @param index 当前节点的下标
     * @return  返回当前节点第一个邻接节点的下标
     */
    public int getFirstNeighbor(int index){
        for(int i =0; i<vertexList.size(); i++){
            if(edges[index][i] > 0){
                //如果index下标的节点的邻接节点存在,则返回
                return i;
            }
        }
        return -1;
    }

    /**
     * v1->v2,如果v2没有下一个邻接节点，则返回到v1继续找除v2外的邻接节点
     * @param v1 开始搜索的节点
     * @param v2 已经搜索的节点
     * @return  v1新邻接节点
     */
    public int getNextNeighbor(int v1, int v2){
        for(int i=v2+1; i<vertexList.size(); i++){
            if(edges[v1][i]>0){
                return i;
            }
        }
        return -1;
    }

    //dfs所有节点
    public void dfs(){
        for(int i=0; i<getNumOfVertex(); i++){
            if(!isVisit[i]){
                dfs(isVisit, i);
            }
        }
    }

    //深度优先搜索
    public void dfs(boolean[] isVisit, int i){
        System.out.print(getValueByIndex(i)+"->");
        //将访问过的节点设为已经访问过
        isVisit[i] = true;
        //得到i的第一个邻接节点
        int w = getFirstNeighbor(i);
        while (w != -1){
            //如果i的第一个邻接节点存在
            if(!isVisit[w]){
                //如果i的第一个邻接节点未被访问过
                dfs(isVisit, w);
            }else{
                //如果i的第一个邻接节点被访问过
                //得到i节点的下一个邻接节点（除w节点）
                w = getNextNeighbor(i ,w);
            }
        }
    }

    //bfs所有节点
    public void bfs(){
        for(int i=0; i<getNumOfVertex(); i++){
            if(!isVisit[i]){
                bfs(isVisit, i);
            }
        }
    }
    //对一个节点进行广度优先搜索
    public void bfs(boolean[] isVisit, int i){
        int u; //表示队列头节点下标
        int w; //表示邻接节点下标
        //创建队列
        LinkedList<Integer> queue = new LinkedList();
        System.out.print(getValueByIndex(i)+"->");
        isVisit[i] = true;
        //将已访问节点加入队列
        queue.addLast(i);
        while (!queue.isEmpty()){
            u = queue.removeFirst();
            w = getFirstNeighbor(u);
            while (w != -1){
                if(!isVisit[w]){
                    System.out.print(getValueByIndex(w)+"->");
                    isVisit[w] = true;
                    queue.addLast(w);
                }else{
                    w = getNextNeighbor(u, w);
                }
            }

        }

    }

}
