package algorithm.recursion;

import javafx.scene.control.TableRow;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 马踏棋盘算法:给定一个棋盘，随机初始化马在棋盘的位置，马走日，让马能够走遍棋盘且不能重复走
 */
public class HorseChessBoard {
    private static int X;   //棋盘的行数
    private static int Y;   //棋盘的列数
    private static int[][] ChessBoard;  //棋盘
    private static boolean[] visited;   //记录棋盘的某个各种是否被访问过 visited[row * X + col]=true 表示row行col列已经被访问过
    private static boolean finished = false;    //记录棋盘是否都被走完
    public static void main(String[] args) {
        X = 8;
        Y = 8;
        ChessBoard = new int[X][Y];
        visited = new boolean[X*Y];
        long startTime = System.currentTimeMillis();
        travelChessBoard(0,0,1);
        long endTime = System.currentTimeMillis();
        System.out.println("耗时：" + (endTime-startTime));
        for(int[] row : ChessBoard){
            for (int i : row){
                System.out.printf("%-3s", i);
            }
            System.out.println();
        }

    }

    /**马踏棋盘算法
     * @param row   马当前所在行数
     * @param col   马当前所在列数
     * @param step  马已经走了几个步（即棋盘有几个格子已经走过）
     */
    public static void travelChessBoard(int row, int col, int step){
        ChessBoard[row][col] = step;
        //visited[row * X + col]=true 表示row行col列已经被访问过,
        // 乘以X是为了不重复，如[10+2][2+10]10行2列和2列10行是相同的指针
        visited[row*X + col] = true;
        //获取当前位置可以走的下一步位置的集合
        ArrayList<Point> nextPoints = getNext(new Point(col, row));
        //对nextPoints里的点进行排序，让nextPoints里的点走下一步能走最少走法的点排在前面
        //这样如果进入的一个点，如果这个点即它以后的点都是行不通的，可以减少步入和回溯的次数
        sort(nextPoints);
        //遍历下一步位置的集合
        while (!nextPoints.isEmpty()){
            Point p = nextPoints.remove(0);
            //判断p点是否被访问过
            if(!visited[p.y * X + p.x]){
                travelChessBoard(p.y, p.x, step+1);
            }
        }
        /**step < X*Y 有两种情况
         *  （1）棋盘没走完，还要继续往下走，就不能进入下面的if语句
         *  （2）棋盘没走完，当已经没格子走了，开始回溯到上一步，就可以进入下面的if语句
         */
        if(step < X*Y && !finished){
            ChessBoard[row][col] = 0;
            visited[row*X + col] = false;
        }else{
            finished = true;
        }

    }

    /**
     * 根据当前马在棋盘的位置(curPoint),得到马在下一步能走的位置的集合ArrayList<Point>
     * @param curPoint  当前位置
     * @return  下一步位置的集合
     */
    public static ArrayList<Point> getNext(Point curPoint){
        ArrayList<Point> nextPoints = new ArrayList<>();
        Point p = new Point();
        if((p.x = curPoint.x - 2) >=0 && (p.y = curPoint.y - 1) >=0){
            nextPoints.add(new Point(p));
        }
        if((p.x = curPoint.x - 2) >=0 && (p.y = curPoint.y + 1) <Y){
            nextPoints.add(new Point(p));
        }
        if((p.x = curPoint.x - 1) >=0 && (p.y = curPoint.y - 2) >=0){
            nextPoints.add(new Point(p));
        }
        if((p.x = curPoint.x - 1) >=0 && (p.y = curPoint.y + 2) <Y){
            nextPoints.add(new Point(p));
        }
        if((p.x = curPoint.x + 2) <X && (p.y = curPoint.y - 1) >=0){
            nextPoints.add(new Point(p));
        }
        if((p.x = curPoint.x + 2) <X && (p.y = curPoint.y + 1) <Y){
            nextPoints.add(new Point(p));
        }
        if((p.x = curPoint.x + 1) <X && (p.y = curPoint.y + 2) <Y){
            nextPoints.add(new Point(p));
        }
        if((p.x = curPoint.x + 1) <X && (p.y = curPoint.y - 2) >=0){
            nextPoints.add(new Point(p));
        }
        return nextPoints;
    }

    /**
     * 对points中的每一个point能走的走法数进行非递归排序
     * @param points
     */
    public static void sort(ArrayList<Point> points){
        points.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                //先获取o1走下一步能走多少种走法
                int count1 = getNext(o1).size();
                //再获取o2走下一步能走多少种走法
                int count2 = getNext(o2).size();
                if(count1 < count2){
                    return -1;
                }else if(count1 > count2){
                    return 1;
                }
                return 0;
            }
        });
    }

}
