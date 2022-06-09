package dataStructure.tree.huffmanTree;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 哈夫曼树实现，链表树
 */
public class HuffmanTreeDemo {
    public static void main(String[] args) {
        int[] arr = {13,7,8,3,29,6,1};
        HuffmanNode root = createHuffmanTree(arr);
        preOrder(root);
    }

    public static HuffmanNode createHuffmanTree(int[] arr){
        //第一步:将arr数组中的元素全部实例化为HuffmanNode，在放入ArrayList中排序
        ArrayList<HuffmanNode> huffmanList = new ArrayList<>();
        for(int val : arr){
            huffmanList.add(new HuffmanNode(val));
        }

        //第二步：循环构建哈夫曼树，直到huffmanList只剩一个元素，即完整哈夫曼树的根
        while(huffmanList.size() > 1){
            //排序从小到大
            Collections.sort(huffmanList);
            //取出权值最小的节点（二叉树）
            HuffmanNode leftNode = huffmanList.get(0);
            //取出权值次小的节点（二叉树）
            HuffmanNode rightNode = huffmanList.get(1);
            //构建一颗新二叉树根节点，权重为左右子树权重之和
            HuffmanNode parent = new HuffmanNode(leftNode.val+rightNode.val);
            parent.left = leftNode;
            parent.right = rightNode;
            //删除huffmanList中已经用过的两个最小节点
            huffmanList.remove(leftNode);
            huffmanList.remove(rightNode);
            //将parent加入到huffmanList
            huffmanList.add(parent);
        }
        return huffmanList.get(0);

    }

    public static void preOrder(HuffmanNode node){
        if(node == null){
            return;
        }
        System.out.println(node.val);
        preOrder(node.left);
        preOrder(node.right);
    }

}

class HuffmanNode implements Comparable<HuffmanNode>{
    int val;
    HuffmanNode left;
    HuffmanNode right;

    public HuffmanNode(int val) {
        this.val = val;
    }

    @Override
    public int compareTo(HuffmanNode o) {
        //从小到大排序
        return this.val-o.val;
    }

}
