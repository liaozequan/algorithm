package dataStructure.array;

/**
 * 实现稀疏数组:
 * 压缩后的数组：
 * ------------------------------------------------------
 *      原数组行数     |     原数组列数     |   有效元素个数
 *  第一个有效元素所在行 | 第一个有效元素所在列 | 第一个有效元素值
 *  第二个有效元素所在行 | 第二个有效元素所在列 | 第二个有效元素值
 *        .          |       .          |      .
 *        .          |       .          |      .
 * -------------------------------------------------------
 */
public class SparseArray {
    public static void main(String[] args) {
        int chessArr[][] = new int[11][11];
        chessArr[1][2] = 1;
        chessArr[2][4] = 2;
        //输出完整二维数组
        for(int[] row : chessArr){
            for(int data : row){
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        //得到二维数组中非0的值
        int sum = 0;
        for(int[] row : chessArr){
            for(int data : row){
                if(data != 0) sum++;
            }
        }

        //构建稀疏数组
        int sparseArr[][] = new int[sum+1][3];
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;
        int index = 1;
        for(int i=0; i<11; i++){
            for(int j=0; j<11; j++){
                if(chessArr[i][j] != 0){
                    sparseArr[index][0] = i;
                    sparseArr[index][1] = j;
                    sparseArr[index][2] = chessArr[i][j];
                    index++;
                }
            }
        }

        //输出稀疏数组
        for(int[] row : sparseArr){
            for(int data : row){
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        //稀疏数组转完整数组
        int comArr[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
        for(int i=1; i<=sparseArr[0][2]; i++){
            comArr[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        //输出稀疏数组转换得到的完整数组
        for(int[] row : comArr){
            for(int data : row){
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

    }
}
