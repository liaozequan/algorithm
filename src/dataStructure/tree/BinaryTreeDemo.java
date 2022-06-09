package dataStructure.tree;

/**
 * 二叉树，链表实现
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        BinaryTree tree = new BinaryTree(node1);
        node1.setLeft(node2);
        node1.setRight(node3);
        node3.setRight(node4);
        tree.postOrder(node1);
    }
}
//二叉树类
class BinaryTree{
    private Node root;//根节点

    public BinaryTree(Node root) {
        this.root = root;
    }

    //前序遍历
    public void preOrder(Node node){
        if(node != null){
            System.out.println(node.getVal());
        }
        if(node.getLeft() != null){
            preOrder(node.getLeft());
        }
        if(node.getRight() != null){
            preOrder(node.getRight());
        }

    }

    //中序遍历
    public void inOrder(Node node){
        if(node.getLeft() != null){
            inOrder(node.getLeft());
        }
        if(node != null){
            System.out.println(node.getVal());
        }
        if(node.getRight() != null){
            inOrder(node.getRight());
        }

    }
    //后序遍历
    public void postOrder(Node node){
        if(node.getLeft() != null){
            postOrder(node.getLeft());
        }

        if(node.getRight() != null){
            postOrder(node.getRight());
        }
        if(node != null){
            System.out.println(node.getVal());
        }

    }

}

//树节点
class Node{
    private int val;
    private Node left;
    private Node right;



    public Node(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}

