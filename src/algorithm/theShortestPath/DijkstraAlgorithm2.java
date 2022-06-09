package algorithm.theShortestPath;

public class DijkstraAlgorithm2 {
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
        int start = 0;
        dijkstra(weight, start, vertexs);
    }

    /**
     * @param weight    图的邻接矩阵
     * @param start     起点下标
     * @param vertexs   节点数组
     */
    public static void dijkstra(int[][] weight, int start, char[] vertexs){
        //设下标为i， shortPath[i] 表示从start节点到i节点的最短路径长度
        int[] shortPath = new int[vertexs.length];
        //设下标为i， path[i] 表示从start节点到i节点的最短路径
        String[] path = new String[vertexs.length];
        //初始化最短路径，因为都是从起点开始的
        for(int i=0; i<vertexs.length; i++){
            path[i] = vertexs[start] + "->" + vertexs[i];
        }
        int[] visited = new int[vertexs.length];
        //start节点到start节点最短路径为0
        shortPath[start] = 0;
        //start节点一开始就设为已访问
        visited[start] = 1;
        for(int count = 1; count<vertexs.length; count++){  //起点已经被访问过，只需找其他的节点路径，因此count从1开始
            int k=-1;   //存放当前节点到下一个最短路径节点的下标
            int dmin = 65535;//存放当前节点到下一个节点最短路径长度
            for(int i=0; i<vertexs.length; i++){
                if(visited[i]==0 && weight[start][i] < dmin){
                    dmin = weight[start][i];
                    k = i;
                }
            }
            //起点start到k节点的最短距离为dmin
            shortPath[k] = dmin;
            visited[k] = 1;

            /**重点：以k为中间点，更新从start到未访问各点的距离
             */
            for(int i=0; i<vertexs.length; i++){
                if(visited[i]==0 && (weight[start][k] + weight[k][i]) < weight[start][i]){
                    weight[start][i] = weight[start][k] + weight[k][i];
                    path[i] = path[k] + "->" + vertexs[i];
                }
            }
        }

        for(int i=0; i<vertexs.length; i++){
            System.out.println("从"+vertexs[start]+"出发到"+vertexs[i]+"的最短路径为"+path[i]);
            System.out.println("从"+vertexs[start]+"出发到"+vertexs[i]+"的最短距离为"+shortPath[i]);
            System.out.println();
        }



    }
}
