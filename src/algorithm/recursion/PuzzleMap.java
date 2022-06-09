package algorithm.recursion;

/**
 * 迷宫问题，递归实现
 * 出发点map[0][0]
 * 终点map右下角
 * map元素值:(0未走)(1墙)(2通路)(3走过但是不是通路)
 * 方向策略优先级:下右上左,都走不通则回溯
 */
public class PuzzleMap {
    public static void main(String[] args) {
        //创建二维数组，模拟迷宫
        int[][] map  = new int[8][7];
        //二维数值中，元素1为墙，先设置四面围墙
        for(int i=0; i<8; i++){
            if(i == 0 || i==7){
                for(int j=0; j<7; j++){
                    map[i][j] = 1;
                }
            }else{
                map[i][0] = 1;map[i][6] = 1;
            }
        }
        //设置挡板
        map[3][1] = 1; map[3][2] = 1;
        //输出地图
        for(int i=0; i<8; i++){
            for(int j=0; j<7; j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
        setWay(map, 1, 1);

        //输出结果地图
        for(int i=0; i<8; i++){
            for(int j=0; j<7; j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }

    /***
     *
     * @param map 地图
     * @param i 当前所在行
     * @param j 当前所在列
     * @return  如果能找到路，返回true
     */
    public static boolean setWay(int[][] map, int i, int j){
        if(map[6][5] == 2){ //终点为2，这找到整条通路
            return true;
        }else if(map[i][j] == 0){
            //如果没走过就走
            map[i][j] = 2;
            //按策略先向下
            if(setWay(map, i+1, j)){
                //如果向下可以走，则返回true
                return true;
            }else if(setWay(map, i, j+1)){  //向下走不通，按策略向右走
                return true;
            }else if(setWay(map, i-1, j)){  //向右走不通，按策略向上走
                return true;
            }else if(setWay(map, i, j-1)){  //向上走不通，按策略向左走
                return true;
            }else { //都走不通是死路，设为3
                map[i][j] = 3;
                return false;
            }
        }else{  //其他情况：map[i][j]=1，2，3说明都没必要往下走
            return false;
        }
    }
}
