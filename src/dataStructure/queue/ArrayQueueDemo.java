package dataStructure.queue;

public class ArrayQueueDemo {
    public static void main(String[] args) {

        try {
            ArrayQueue arrayQueue = new ArrayQueue(10);
            arrayQueue.addQueue(9);
            arrayQueue.addQueue(5);
            arrayQueue.addQueue(4);
            arrayQueue.top();
            arrayQueue.show();
            arrayQueue.delQueue();
            arrayQueue.delQueue();
            arrayQueue.top();
            arrayQueue.show();
            arrayQueue.delQueue();
            arrayQueue.delQueue();
        }catch (Exception e){
            //e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }
}

class ArrayQueue{
    private int maxSize;
    private int front;  //头指针,指向队首元素前一个位置
    private int rear;   //尾指针，指向队尾元素
    private int[] queue;

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        this.queue = new int[maxSize];
        this.front = -1;
        this.rear = -1;
    }

    //是否队满
    public boolean isFull(){
        return this.rear == this.maxSize-1;
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
        this.rear++;
        this.queue[rear] = element;
    }

    //出队并返回出队元素
    public int delQueue(){
        if(isEmpty()){
            throw new RuntimeException("dataStructure.queue is empty,output error!");
        }
        return this.queue[++this.front];
    }

    //输出队列中所有数据
    public void show(){
        if(isEmpty()){
            throw new RuntimeException("dataStructure.queue is empty,output error!");
        }
        for(int i=this.front+1; i<=rear; i++){
            System.out.println(this.queue[i]);
        }
    }

    //输出队头元素，不出队
    public void top(){
        if(isEmpty()){
            throw new RuntimeException("dataStructure.queue is empty,output error!");
        }
        System.out.println(this.queue[this.front+1]);
    }
}
