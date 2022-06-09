package dataStructure.tree;

/**
 * 二叉树，数组实现
 * 数组下标n的节点：左子树下标（n*2+1）右子树下标（n*2+2）
 * 可应用于堆排序
 */
public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        ArrayBinaryTree tree = new ArrayBinaryTree(arr);
        tree.preOrder(0);
    }
}

class ArrayBinaryTree{
    private int arr[];

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void preOrder(int index){
        if(arr == null || arr.length == 0){
            System.out.println("数组为空！");
        }
        System.out.println(arr[index]);
        if(index*2+1 < arr.length){
            preOrder(index*2+1);
        }
        if(index*2+2 < arr.length){
            preOrder(index*2+2);
        }

    }
}

