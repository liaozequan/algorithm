package dataStructure.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/***
 * 后缀表达式计算器，栈实现
 */
public class SuffixByStack {
    public static void main(String[] args) {
        String suffixExpression = "3 4 + 5 * 6 -";
        List<String> list = getListString(suffixExpression);
        System.out.println(calculateAll(list));
    }

    //将后缀表达式放入ArrayList中
    public static List<String> getListString(String suffixExpression){
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
        for(String ele : split){
            list.add(ele);
        }
        return list;
    }

    public static int calculateAll(List<String> list){
        Stack<String> stack = new Stack<>();
        for(String item : list){
            if(item.matches("\\d+")){    //匹配是多位数
                stack.push(item);
            }else{
                //如果是运算符则取出两个数计算
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int res = 0;
                res = calculate(num1, num2,item);
                stack.push(String.valueOf(res));
            }
        }
        return Integer.parseInt(stack.pop());
    }

    public static int calculate(int a, int b, String oper){
        int res=0;//计算结果
        switch (oper){
            case "+":
                res = a+b;
                break;
            case "-":
                res = b-a; //注意顺序
                break;
            case "*":
                res = a*b;
                break;
            case "/":
                res = b/a;//注意顺序
                break;
            default:
                throw new RuntimeException("operate is error!");

        }
        return res;
    }
}
