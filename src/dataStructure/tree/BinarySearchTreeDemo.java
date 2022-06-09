package dataStructure.tree;

/**
 * 二叉搜索树（二叉排序树）
 */
public class BinarySearchTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7,3,10,12,5,1,9};
        BinarySearchTree tree = new BinarySearchTree();
        for(int i=0; i< arr.length; i++){
            tree.add(new BSTNode(arr[i]));
        }

        tree.delNode(7);
        tree.infixOrder(tree.root);
    }
}
//创建二叉搜索树
class BinarySearchTree{
    public BSTNode root;

    //二叉搜索树添加节点
    public void add(BSTNode node){
        if(this.root == null){
            this.root = node;
        }else{
            this.root.addNode(node);
        }
    }

    //中序遍历
    public void infixOrder(BSTNode node){
        if(node == null){
            return;
        }
        infixOrder(node.left);
        System.out.println(node.val);
        infixOrder(node.right);
    }

    public BSTNode searchNode(int val){
        if(root == null){
            return null;
        }else{
            return this.root.search(val);
        }
    }

    public BSTNode searchParentNode(int val){
        if(this.root == null){
            return null;
        }else{
            return this.root.searchParent(val);
        }
    }

    /**删除节点值为val的节点，设待删除节点为p
     * 1.p为叶子节点-->直接删除p
     * 2.p只有左子树或只有右子树-->用p的左儿子或右儿子节点代替p
     * 3.p有左右子树-->（本次使用该方法）找到p的右子树中最小值的节点，用该节点代替p，并删除该节点，该节点一定是叶子节点
     *            -->（另一只方法）或找到p的左子树中的最大值的节点，用该节点代替p，并删除该节点
     */
    public void delNode(int val){
        if(this.root == null){
            return;
        }else{
            //找到待删除的目标节点
            BSTNode targetNode = searchNode(val);
            if(targetNode==null){
                return; //未找到目标节点，结束
            }
            if(root.left==null && root.right==null){
                //如果整棵树只剩待删除的目标节点（即目标节点是根节点，且无子树）
                root = null;//删除根节点
                return;//结束
            }
            //找到目标节点的父节点
            BSTNode parentNode = searchParentNode(val);
            if(targetNode.left==null && targetNode.right==null){
                //如果待删除的目标节点是 叶子节点
                //继续判断待删除的目标节点是其父节点的左子节点还是右子节点
                if(parentNode.left!=null && parentNode.left.val==val){
                    //如果待删除的目标节点是其父节点的左子节点，则删除
                    parentNode.left = null;
                }else if(parentNode.right!=null && parentNode.right.val==val){
                    //如果待删除的目标节点是其父节点的右子节点，则删除
                    parentNode.right = null;
                }
            }else if(targetNode.left != null && targetNode.right != null){
                //如果待删除的目标节点 有左右子树
                //得到待删除的目标节点的右子树中的最小值，并且下面的方法中已经删除了这个最小值节点
                int min = getDelRightTreeMin(targetNode.right);
                //实现待删除的目标节点替换为右子树中的最小值节点
                targetNode.val = min;
            }else{
                //如果待删除的目标节点 只有一颗子树，只有左子树或右子树
                if(targetNode.left != null){
                    //如果待删除的目标节点只有左子树
                    if(parentNode == null){
                        root = targetNode.left;
                    }
                    if(parentNode.left.val == val){
                        //如果待删除的目标节点是其父节点的左子树，这其父节点的左子树替换为待删除的目标节点的左子树
                        parentNode.left = targetNode.left;
                    }else if(parentNode.right.val == val){
                        //如果待删除的目标节点是其父节点的右子树，这其父节点的右子树替换为待删除的目标节点的左子树
                        parentNode.right = targetNode.left;
                    }
                }else if(targetNode.right != null){
                    //如果待删除的目标节点只有右子树
                    if(parentNode == null){
                        root = targetNode.right;
                    }
                    if(parentNode.left.val == val){
                        //如果待删除的目标节点是其父节点的左子树，这其父节点的左子树替换为待删除的目标节点的右子树
                        parentNode.left = targetNode.right;
                    }else if(parentNode.right.val == val){
                        //如果待删除的目标节点是其父节点的右子树，这其父节点的右子树替换为待删除的目标节点的右子树
                        parentNode.right = targetNode.right;
                    }
                }
            }

        }
    }

    /**本方法实现：传入待删除节点右子树，找到右子树的最小值，删除这个最小值节点，返回这个最小值
     * @param node  待删除节点右子树的根
     * @return  返回右子树中最小值
     */
    public int getDelRightTreeMin(BSTNode node){
        BSTNode minNode = node;
        while (minNode.left != null){
            minNode = minNode.left;
        }
        delNode(minNode.val);
        return minNode.val;
    }
}

//创建二叉搜索树节点
class BSTNode{
    int val;
    BSTNode left;
    BSTNode right;

    public BSTNode(int val) {
        this.val = val;
    }

    //添加节点，递归添加，需满足二叉搜索树要求
    public void addNode(BSTNode node){
        if(node == null){
            return;
        }
        if(node.val < this.val){
            //如果待加入节点的值 < 当前节点的值
            if(this.left == null){
                //如果当前节点的左子树为空，则待加入节点作为其左子节点加入
                this.left = node;
            }else {
                //如果当前节点的左子树不为空，则递归到左子节点继续判断
                this.left.addNode(node);
            }
        }else{
            //如果待加入节点的值 >= 当前节点的值
            if(this.right == null){
                //如果当前节点的右子树为空，则待加入节点作为其右子节点加入
                this.right = node;
            }else{
                //如果当前节点的右子树不为空，则递归到右子节点继续判断
                this.right.addNode(node);
            }
        }
    }

    //找到值为val的节点
    public BSTNode search(int val){
        if(val == this.val) {
            return this;
        }else if(val < this.val){
            //如果查找的val < 当前节点val，则向左查找
            if(this.left == null){
                return null;
            }
            return this.left.search(val);
        }else{
            //如果查找的val >= 当前节点val，则向右查找
            if(this.right == null){
                return null;
            }
            return this.right.search(val);
        }
    }

    //找到值为val的节点的父节点
    public BSTNode searchParent(int val){
        if((this.left != null && this.left.val == val) || (this.right != null && this.right.val == val)){
            //左子节点不为空且左子节点的值就是所查找的值，则说明左子节点即为所查找的节点，此时当前节点即为所查找节点的父节点
            //右边同理，故用 || 来判断
            return this;
        }else{
            if(val < this.val && this.left != null){
                //如果所找节点的值 < 当前节点的值，且当前节点的左子节点不为空，则递归进入当前节点左子节点查找
                return this.left.searchParent(val);
            }else if(val >= this.val && this.right != null){
                //如果所找节点的值 >= 当前节点的值，且当前节点的右子节点不为空，则递归进入当前节点右子节点查找
                return this.right.searchParent(val);
            }else{
                return null;//没找到父节点，例如要找到节点是根节点，就没有父节点
            }
        }
    }
}