package algorithm.kmp;

import java.util.Arrays;

public class KMPAlgorithm {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";    //搜索串
        int[] next = kmpNext("ABCDABD");
        System.out.println(Arrays.toString(next));
        System.out.println(kmpSearch(str1, str2, next));
    }

    /** kmp搜索算法
     * @param str1  原字符串
     * @param str2  搜索串
     * @param next  （子串对应的）部分匹配表
     * @return  -1（未匹配到）  否则返回第一个匹配的位置
     */
    public static int kmpSearch(String str1, String str2, int[] next){
        for(int i=0, j=0; i<str1.length(); i++){
            while(j>0 && str1.charAt(i) != str2.charAt(j)){
                //如果匹配过程中，两个字符串中的字符不相等，调整搜索串指针位置（往前回溯）
				//失配时，搜索串向右移动的位数为：已匹配字符数 - 失配字符的上一位字符所对应的部分匹配值
                //在搜索过程中，原串的指针是不停往下走的，而搜索串指针会通过部分匹配表回溯
                j = next[j-1];
            }
            if(str1.charAt(i) == str2.charAt(j)){
                j++;
            }
            if(j == str2.length()){
                //成功匹配
                return i-j+1;//返回str1中匹配的第一个位置下标
            }
        }
        return  -1;
    }

    /**获取搜索串的部分匹配表（前缀后缀的公共元素的最大长度表）
     * "ABCDAB" 前缀：[A, AB, ABC, ABCD, ABCDA]    后缀：[B, AB, DAB, CDAB, BCDAB]
     * 部分匹配值为：前缀集合和后缀集合的交集中最长的字符串长度。如上面两个集合的交集[AB]，字符串长度为2，则"ABCDAB"的部分匹配值为2
     * 如果前缀集合和后缀集合的交集为[A, AB],部分匹配值为2（选择最长的字符串长度）
     * @param dest  搜索串
     * @return  返回部分匹配表：int[0]的值为第一个字符的部分匹配值 int[1]的值为前两个字符的部分匹配值
     */
    public static int[] kmpNext(String dest){
        int[] next = new int[dest.length()];
        //第一个字符的部分匹配值一定为0，因为一个字符无前缀集合也无后缀集合，交集中无字符串
        next[0] = 0;
        for(int i=1, j=0; i<dest.length(); i++){
            //当dest.charAt(i) != dest.charAt(j)时，需要从next[j-1]获取新的j
            //直到dest.charAt(i) == dest.charAt(j)
            while(j>0 && dest.charAt(i) != dest.charAt(j)){
                j = next[j-1];
            }
            if(dest.charAt(i) == dest.charAt(j)){
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
