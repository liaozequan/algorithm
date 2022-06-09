package dataStructure.linkedList;

/**
 * 实现双向链表
 * 注意插入删除节点时，插入位置后一个节点是否为空的判断
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        DoubleLinkedList linkedList = new DoubleLinkedList();
        linkedList.addNode(5);
        linkedList.addNode(7);
        linkedList.addNode(8);
        linkedList.addNodeByOrder2(4);
        linkedList.addNodeByOrder2(6);
        linkedList.addNodeByOrder2(11);
        linkedList.show();
        linkedList.delNode(7);
        linkedList.delNode(8);
        linkedList.delNode(4);
        linkedList.show();
    }
}

class DoubleLinkedList{
    public DoubleNode head = new DoubleNode(0);

    //添加节点
    public void addNode(int val){
        DoubleNode cur = this.head;
        while(cur.next != null){
            cur = cur.next;
        }
        DoubleNode newNode = new DoubleNode(val);
        cur.next = newNode;
        newNode.pre = cur;
    }

    //显示链表所有元素
    public void show(){
        DoubleNode cur = this.head;
        if(cur.next == null){
            throw new RuntimeException("SingleLinkedList is empty,output error!");
        }
        while(cur.next != null){
            cur = cur.next;
            System.out.println(cur.val);
        }
    }

    //删除指定val节点
    public void delNode(int val){
        DoubleNode cur = this.head.next;
        if(cur == null){
            throw new RuntimeException("DoubleLinkedList is empty, error!");
        }
        while (cur != null){
            if(cur.val == val){
                cur.pre.next = cur.next;
                //如果删除的不是最后一个节点，则删除节点的后一个节点应指向删除节点的前一个节点
                if(cur.next != null){
                    cur.next.pre = cur.pre;
                }
                return;
            }
            cur = cur.next;
        }
        throw new RuntimeException("DoubleLinkedList is no contain val!");
    }

    //按元素值大小顺序插入链表
    public void addNodeByOrder2(int val){
        DoubleNode cur = this.head;
        DoubleNode newNode = new DoubleNode(val);//新节点
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
        newNode.pre = cur;
        cur.next = newNode;
        if(newNode.next != null){
            newNode.next.pre = newNode;
        }

    }


}

//节点类
class DoubleNode{
    public int val;
    public DoubleNode next;
    public DoubleNode pre;

    public DoubleNode() {
    }

    public DoubleNode(int val) {
        this.val = val;
    }


}


