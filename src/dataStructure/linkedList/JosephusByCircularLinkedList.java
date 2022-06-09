package dataStructure.linkedList;

/**
 * 约瑟夫环，循环链表实现
 */
public class JosephusByCircularLinkedList {
    public static void main(String[] args) {
        CircularLinkedList circularLinkedList = new CircularLinkedList();
        circularLinkedList.add(5);
        circularLinkedList.show();
        circularLinkedList.delKid(1, 3, 5);
    }
}

//环形链表
class CircularLinkedList{
    //初始化头节点
    private KidNode first = null;

    /**
     * 添加节点，构建环形链表
     * @param nums 孩子个数
     */
    public void add(int nums){
        if(nums<1){
            throw new RuntimeException("nums<1");
        }
        KidNode cur = this.first;
        for(int i=1; i<=nums; i++){
            KidNode newKid = new KidNode(i);
            if(i==1){
                this.first = newKid;
                newKid.setNext(this.first);
                cur = this.first;
            }else{
                cur.setNext(newKid);
                newKid.setNext(this.first);
                cur = newKid;
            }
        }
    }

    public void show(){
        if(this.first == null){
            throw new RuntimeException("CircularLinkedList is null");
        }

        KidNode cur = this.first;
        System.out.println(cur.getNo());
        while (cur.getNext() != this.first){
            System.out.println(cur.getNext().getNo());
            cur = cur.getNext();
        }
    }

    /**
     * kid出列
     * @param start 从第几个kid开始数数
     * @param m 数几下出列
     * @param nums  kid总数
     */
    public void delKid(int start, int m, int nums){
        //对游戏数据校验，开始的位置不合法
        if(this.first == null || start <1 || start >nums){
            throw new RuntimeException("game error!");
        }
        KidNode helper = this.first;    //辅助指针
        //先让辅助指针指向链尾
        while (helper.getNext() != this.first){
            helper = helper.getNext();
        }
        //指针到达报数开始位置
        while (start > 1){
            helper = helper.getNext();
            this.first = this.first.getNext();
            start--;
        }
        while (nums > 1){
            nums--;
            for(int i=0; i<m-1; i++){
                helper = helper.getNext();
                this.first = this.first.getNext();
            }
            System.out.println("出圈序号："+this.first.getNo());
            this.first = this.first.getNext();
            helper.setNext(this.first);
        }
        System.out.println("幸存者序号："+this.first.getNo());

    }
}

//孩子节点
class KidNode{
    private int no;     //编号
    private KidNode next;   //下一个孩子节点

    public KidNode(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public KidNode getNext() {
        return next;
    }

    public void setNext(KidNode next) {
        this.next = next;
    }
}
