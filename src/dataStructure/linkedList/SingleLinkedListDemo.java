package dataStructure.linkedList;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        SingleLinkedList linkedList = new SingleLinkedList();
        linkedList.addNodeByOrder2(5);
        linkedList.addNodeByOrder2(2);
        linkedList.addNodeByOrder2(4);
        linkedList.addNodeByOrder2(9);
        linkedList.addNodeByOrder2(1);
        linkedList.delNode(5);
        linkedList.show();

    }




}
class SingleLinkedList{
    public Node head = new Node(0);


    //添加节点
    public void addNode(int val){
        Node cur = this.head;
        while(cur.next != null){
            cur = cur.next;
        }
        Node newNode = new Node(val);
        cur.next = newNode;
    }

    //显示链表所有元素
    public void show(){
        Node cur = this.head;
        if(cur.next == null){
            throw new RuntimeException("SingleLinkedList is empty,output error!");
        }
        while(cur.next != null){
            cur = cur.next;
            System.out.println(cur.val);
        }
    }

    //按元素值大小顺序插入链表
    public void addNodeByOrder(int val){
        Node cur = this.head;
        Node newNode = new Node(val);//新节点
        while (cur != null){
            if(cur.val < val && cur.next == null){
                newNode.next = cur.next;
                cur.next = newNode;
                return;
            }else if(cur.val < val && cur.next != null){
                if(cur.next.val > val){
                    newNode.next = cur.next;
                    cur.next = newNode;
                    return;
                }
            }
            cur = cur.next;
        }
    }
    //按元素值大小顺序插入链表
    public void addNodeByOrder2(int val){
        Node cur = this.head;
        Node newNode = new Node(val);//新节点
        while (true){
            if(cur.next==null){
                break;
            }
            if(cur.next.val > val){
                break;
            }
            cur = cur.next;
        }
        newNode.next = cur.next;
        cur.next = newNode;
    }

    //删除指定val节点
    public void delNode(int val){
        Node cur = this.head;
        while (cur.next != null){
            if(cur.next.val == val){
                cur.next = cur.next.next;
                return;
            }
            cur = cur.next;
        }
        throw new RuntimeException("SingleLinkedList is no contain val!");
    }
}

//节点类
class Node{
    public int val;
    public Node next;

    public Node() {
    }

    public Node(int val) {
        this.val = val;
    }


}
