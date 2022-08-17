package algorithm.manacher;

/**给你一个字符串 s，找到 s 中最长的回文子串
 * s = "babad"  result="bab"
 * 思路：
 * 0.为整个字符串穿插加入字符'#'，来避免回文串长度单复数的判断
 * 1.遍历每个字符，从每个字符开始往两边匹配来找最长回文子串
 * 2.定义：
 *  2.1 pArr[] 回文半径数组（pArr[i]为s[i]为中轴的回文串长度的一半）
 *  2.2 int R s[i]前所有回文串中右边界最大值为R-1
 *  2.3 int C 前所有回文串中右边界为R-1的中轴为C
 * 3.
 *  3.1 if i遍历到 > R 则两边暴力匹配来找最长回文子串
 *  3.2 if i遍历到 < R
 *      3.2.1 if (i`)的回文串在以C为中轴的回文串内部（L~R），则i的回文串也在L~R中，R、C不更新
 *       (i`)是i以C为中轴的对称点
 *      3.2.2 if(i`)的回文串超出了以C为中轴的回文串内部（L~R），则i的回文串也在L~R中，R、C不更新
 *      3.2.3 if(i`)的回文串左边界在 以C为中轴的回文串L处，则i从(i`)的回文半径长度开始两边暴力匹配来找最长回文子串
 *  3.3 if i回文串右边界>R,则更新 R=i回文串右边界,C=i
 */
public class Manacher {
    public static String s = "febabefad";
    //0.为整个字符串穿插加入字符'#'，来避免回文串长度单复数的判断
    public static char[] manacherString(String s){
        char[] charArr = s.toCharArray();
        char[] res = new char[s.length()*2+1];
        int index = 0;
        for (int i = 0; i < res.length; i++) {
            res[i] = (i&1)==0 ? '#':charArr[index++];
        }
        return res;
    }
    public static void main(String[] args) {
        if(s == null || "".equals(s)){
            System.out.println(0);
        }
        char[] str = manacherString(s);
        int pArr[] = new int[str.length];   //回文半径
        int C = -1; //中轴
        int R = -1; //回文右边界再往右一个指针，最右有效区为R-1
        int max = 0;
        for (int i = 0; i < str.length; i++) {
            //pArr[2 * C - i]是(i`)回文半径
            pArr[i] = R > i ?  Math.min(pArr[2 * C - i], R - i) : 1;
            while (i + pArr[i] < str.length && i - pArr[i] > -1){
                if(str[i + pArr[i]] == str[i - pArr[i]]){
                    pArr[i]++;
                }else {
                    break;
                }
            }
            if(i + pArr[i] > R){
                R = i + pArr[i];
                C = i;
            }
            max = pArr[max] > pArr[i] ? max : i;
        }
        int left = (max>>1)-((pArr[max]-1)>>1);
        int right = left + pArr[max]-1;
        System.out.println(s.substring(left, right));
    }
}
