package dataStructure.stack;

public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(3);
        arrayStack.push(1);
        arrayStack.push(2);
        arrayStack.push(3);
        arrayStack.pop();
        System.out.println(arrayStack.stack[arrayStack.top]);

    }


}
class ArrayStack{
    public int maxSize;
    public int[] stack;
    public int top = -1; //无数据时，为-1

    //初始化栈
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        this.stack = new int[maxSize];
    }

    public boolean isFull(){
//        System.out.println("dataStructure.stack is full,error!");
        return this.top == this.maxSize-1;
    }

    public boolean isEmpty(){
//        System.out.println("dataStructure.stack is empty,error!");
        return this.top == -1;
    }

    public void push(int element){
        if(isFull()){
            throw new RuntimeException("dataStructure.stack is full,push error!");
        }
        this.stack[++this.top] = element;
    }

    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("dataStructure.stack is empty,pop error!");
        }
        return this.stack[this.top--];
    }

    //返回栈顶元素，不出栈
    public int peak(){
        return this.stack[top];
    }




}