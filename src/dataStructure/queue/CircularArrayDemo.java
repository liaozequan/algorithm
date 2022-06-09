package dataStructure.queue;

/**
 * 实现循环队列
 * 牺牲一个元素空间
 * front 指向队首元素
 * rear 指向队尾元素的后一个元素
 * 队满判断：(rear+1)%maxsize == front
 * 对空判断：rear == front
 * 有效数据个数 (rear+maxsize-front)%maxsize
 * 出入队操作：先取值/赋值，后移指针
 */


class CircularArray{
    private int maxSize;    //实际有效空间maxSize-1
    private int front;  //头指针,指向队首元素、
    private int rear;   //尾指针，指向队尾元素的后一个元素
    private int[] queue;

    public CircularArray(int maxSize) {
        this.maxSize = maxSize;
        this.queue = new int[maxSize];
        this.front = 0;
        this.rear = 0;
    }

    //是否队满
    public boolean isFull(){
        return (this.rear+1)%this.maxSize == this.front;
    }

    //是否队空
    public boolean isEmpty(){
        return this.front == this.rear;
    }

    //入队
    public void addQueue(int element){
        if(isFull()){
            System.out.println("dataStructure.queue is full,input error!");
            return;
        }
        this.queue[this.rear] = element;
        this.rear = (rear+1)%this.maxSize;

    }

    //出队并返回出队元素
    public int delQueue(){
        if(isEmpty()){
            throw new RuntimeException("dataStructure.queue is empty,output error!");
        }
        int x = this.queue[this.front];
        this.front = (this.front+1) % this.maxSize;
        return x;
    }

    //输出队列中所有数据
    public void show(){
        if(isEmpty()){
            throw new RuntimeException("dataStructure.queue is empty,output error!");
        }
        for(int i=this.front; i<this.front+size(); i++){
            System.out.println(this.queue[i%this.maxSize]+" ");
        }
    }

    //返回当前队列有效元素个数
    public int size(){
        return (this.rear+this.maxSize-this.front)%this.maxSize;
    }

    //输出队头元素，不出队
    public void top(){
        if(isEmpty()){
            throw new RuntimeException("dataStructure.queue is empty,output error!");
        }
        System.out.println(this.queue[this.front]);
    }
}
public class CircularArrayDemo {
    public static void main(String[] args) {
        CircularArray circularArray = new CircularArray(4);
        circularArray.addQueue(1);
        circularArray.addQueue(2);
        circularArray.addQueue(3);
        circularArray.show();
        circularArray.delQueue();
        circularArray.addQueue(4);
        circularArray.show();
    }
}