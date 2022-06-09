package dataStructure.stack;

/**
 * 中缀表达式计数器，栈实现
 */
public class CalculatorByStack {
    public static void main(String[] args) {
        String expression = "31+2*6-2";
        CalculateStack numStack = new CalculateStack(expression.length()); //数值栈
        CalculateStack operStack = new CalculateStack(expression.length());//操作符栈
        int index = 0; //扫描expression表达式的指针
        int num1=0, num2=0; //记录单次运算所需的两个数字
        int oper = 0; //记录单次运算所需的运算符
        int res = 0; //记录单次运算结果
        char ch = ' '; //记录扫描expression得到的char
        String keepNum="";//用于拼接多位数
        while (index < expression.length()){
            ch = expression.substring(index, index+1).charAt(0);
            //当前所遍历是否是操作符
            if(operStack.isOper(ch)){
                //操作符栈是否为空
                if(!operStack.isEmpty()){
                    //操作符栈不为空，将当前操作符与操作符栈顶的操作符做优先级比较
                    if(operStack.priority(ch) <= operStack.priority(operStack.peak())){
                        /**如果当前操作符优先级小于等于(操作符栈)顶的操作符
                         * 1.pop出两个数字栈元素
                         * 2.pop出一个操作符栈元素
                         * 3.对1.2.进行运算，并将结果push到数字栈
                         * 4.将当前操作符push到操作符栈
                         */
                        num1 = numStack.pop();num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.calculate(num1, num2, oper);
                        numStack.push(res);
                        operStack.push(ch);
                    }else{
                        //如果当前操作符优先级大于(操作符栈)顶的操作符，则直接入栈
                        operStack.push(ch);
                    }
                }else{
                    //操作符栈不空，当前操作符直接入栈
                    operStack.push(ch);
                }
            }else{
                //如果当前遍历是数字，则判断是否是多位数
                keepNum += ch;
                if(index== expression.length()-1 || numStack.isOper(expression.substring(index+1, index+2).charAt(0))){
                    //如果下一位是运算符，这当前遍历的数字直接入栈
                    numStack.push(Integer.parseInt(keepNum));
                    keepNum = "";
                }

            }
            index++;

        }
        while(!operStack.isEmpty()){
            num1 = numStack.pop();num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.calculate(num1, num2, oper);
            numStack.push(res);
        }
        System.out.printf("计算 %s 结果：= %d", expression, numStack.pop());
    }
}

class CalculateStack extends ArrayStack{
    public CalculateStack(int maxSize) {
        super(maxSize);
    }

    //返回运算符优先级，返回数值越大，则优先级越大
    public int priority(int oper){
        if (oper == '*' || oper == '/') {
            return 1;
        }else if(oper == '+' || oper == '-'){
            return 0;
        }
        return -1;
    }

    //判断是否是运算符
    public boolean isOper(int oper){
        return oper == '*' || oper == '/' || oper == '+' || oper == '-';

    }

    public int calculate(int a, int b, int oper){
        int res=0;//计算结果
        switch (oper){
            case '+':
                res = a+b;
                break;
            case '-':
                res = b-a; //注意顺序
                break;
            case '*':
                res = a*b;
                break;
            case '/':
                res = b/a;//注意顺序
                break;
            default:
                break;
        }
        return res;
    }
}
