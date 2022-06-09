package dataStructure.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 中缀表达式转后缀表达式，栈实现
 * 1.初始化两个栈，一个s1运算符栈，一个s2结果栈（存放最终转换结果）
 * 2.从左到右扫描中缀表达式
 * 3.遍历到数字，将其push到s2结果栈
 * 4.遍历到运算符：
 * 4.1.如果s1为空，或s1栈顶为“(”,则将遍历到的运算符push到s1
 * 4.2如果遍历到的运算符比s1栈顶运算符优先级高，则同样将遍历到的运算符push到s1
 * 4.3否则将s1栈顶运算符pop，push到s2，回到4.1继续比较
 *
 * 5.遇到括号时：
 * 5.1“(”则直接push到s1
 * 5.2")"则将s1栈中元素pop，push到s2中，直到遇到"("，且括号不push到s2
 *
 * 6.当遍历完整个中缀表达式，将s1全部元素push到s2
 */
public class InfixToSuffix {
    public static void main(String[] args) {
        String infixExpression = "1+((2+3)*4)-5";
        List<String> list = getListString(infixExpression);
        List<String> suffixExpression = convertToSuffix(list);
                System.out.println("结果:"+suffixExpression);
        //应用SuffixByStack
        int res = SuffixByStack.calculateAll(suffixExpression);
        System.out.println("计算结果："+ res);

    }

    public static List<String> convertToSuffix(List<String> list){
        Stack<String> s1 = new Stack<>();//操作符栈
        List<String> s2 = new ArrayList<String>();//存放结果
        for(String item : list){
            if(item.matches("\\d+")){
                //3
                s2.add(item);
            }else if("(".equals(item)){
                //5.1
                s1.push(item);
            }else if(")".equals(item)){
                //5.2
                while (!"(".equals(s1.peek())){
                    s2.add(s1.pop());
                }
                s1.pop();//最后遇到"("将其pop
            }else{
                //4.3
                while (s1.size() !=0 && Operation.getPriority(s1.peek()) >= Operation.getPriority(item)){
                    s2.add(s1.pop());
                }
                s1.push(item);
            }
        }
        while (s1.size() !=0){
            s2.add(s1.pop());
        }
        return s2;

    }

    //将中缀表达式放入ArrayList中
    public static List<String> getListString(String s){
        List<String> list = new ArrayList<String>();
        int i=0;//遍历表达式的指针
        String str;//拼接多位数字
        char c;//遍历的每一个字符
        do{
            if((c=s.charAt(i)) < 48 || (c=s.charAt(i)) > 57){
                //如果遍历到的字符为非数字，则加入list
                list.add(String.valueOf(c));
                i++;
            }else{
                //如果遍历到的字符为数字，则考虑是否是多位数字
                str="";
                while (i<s.length() && (c=s.charAt(i)) >= 48 && (c=s.charAt(i)) <= 57){
                    str += c;
                    i++;
                }
                list.add(str);
            }
        }while (i<s.length());
        return list;
    }
}

class Operation{
    private static int ADD=1;
    private static int SUB=1;
    private static int MUL=2;
    private static int DIV=2;

    public static int getPriority(String opreation){
        int res=0;//计算结果
        switch (opreation){
            case "+":
                res = ADD;
                break;
            case "-":
                res = SUB; //注意顺序
                break;
            case "*":
                res = MUL;
                break;
            case "/":
                res = DIV;//注意顺序
                break;
        }
        return res;
    }
}
