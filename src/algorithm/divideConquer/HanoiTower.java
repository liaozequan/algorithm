package algorithm.divideConquer;

/**
 * 分治算法：解决汉诺塔问题
 */
public class HanoiTower {
    public static void main(String[] args) {
        hanoiTower(5, 'a', 'b' , 'c' );
    }

    /**
     * @param num   有几个盘子
     * @param a 将盘移出的柱子
     * @param b 辅助移动的柱子
     * @param c 移入盘子的柱子
     */
    public static void hanoiTower(int num, char a, char b, char c){
        if(num == 1){
            //如果只有一个盘，直接A->C
            System.out.println("第1个盘从 "+ a + "-->" + c);
        }else{
            /**如果有 num>=2个盘子，我们总是看做2个盘
             * 1.最下面的一个盘    2.上面的所有盘
             * 第一步：先把上面的所有盘 A————>B,移动过程中要利用C来寄存
             */
            hanoiTower(num-1, a, c, b);
            //第二步：把最下边的盘 A--->C
            System.out.println("第"+ num +"个盘从 "+ a + "-->" + c);
            //第三步：把B上的所有盘全部移动到C，移动过程中要利用A来寄存
            hanoiTower(num-1, b, a, c);
        }
    }
}
