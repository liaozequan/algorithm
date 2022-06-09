package dataStructure.tree;

/**
 * 线索二叉树
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        ThreadedNode node1 = new ThreadedNode(1);
        ThreadedNode node2 = new ThreadedNode(3);
        ThreadedNode node3 = new ThreadedNode(6);
        ThreadedNode node4 = new ThreadedNode(8);
        ThreadedNode node5 = new ThreadedNode(10);
        ThreadedNode node6 = new ThreadedNode(14);
        ThreadedBinaryTree tree = new ThreadedBinaryTree(node1);
        node1.setLeft(node2);
        node1.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        tree.doThreadedNode(node1);
        tree.showThreadedBinaryTree();

    }
}

//线索二叉树
class ThreadedBinaryTree{
    private ThreadedNode root;
    private ThreadedNode pre = null;//保持遍历节点的上一个节点

    public ThreadedBinaryTree(ThreadedNode root) {
        this.root = root;
    }

    //将节点进行线索化(中序遍历线索化)
    public void doThreadedNode(ThreadedNode node){
        if(node == null){
            return;
        }
        //线索化左子树
        doThreadedNode(node.getLeft());
        //线索化当前节点
        if(node.getLeft() == null){
            node.setLeft(pre);
            node.setLeftType(1);
        }
        //当前节点的右子树是由中序遍历顺序的后一个节点来处理的
        if(pre != null && pre.getRight() == null){
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;//更新上一个节点为当前节点，继续递归

        //线索化右子树
        doThreadedNode(node.getRight());

    }

    //遍历 中序线索化二叉树
    public void showThreadedBinaryTree(){
        ThreadedNode node = this.root;
        while(node != null){
            //遍历到最左下角节点
            while (node.getLeftType() == 0){
                node = node.getLeft();
            }
            System.out.println(node.getVal());
            //如果当前节点的右子树指向的是遍历顺序的下一个节点（node.getrightType() == 1），则输出
            while (node.getRightType() == 1){
                node = node.getRight();
                System.out.println(node.getVal());
            }
            //此时的node为中间节点，如果中间节点的右指针不是按顺序指向的（rightType() == 0），则根据中序遍历，直接指向右节点
            node = node.getRight();
        }
    }
}

//线索二叉树节点
class ThreadedNode{
    private int val;
    private ThreadedNode left;
    private ThreadedNode right;

    private int leftType;   //leftType==0,表示指向左子树  leftType==1,表示指向前驱节点
    private int rightType;  //leftType==0,表示指向右子树  leftType==1,表示指向后继节点



    public ThreadedNode(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public ThreadedNode getLeft() {
        return left;
    }

    public void setLeft(ThreadedNode left) {
        this.left = left;
    }

    public ThreadedNode getRight() {
        return right;
    }

    public void setRight(ThreadedNode right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }
}