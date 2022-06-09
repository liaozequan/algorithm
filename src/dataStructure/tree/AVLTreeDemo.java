package dataStructure.tree;

/**
 * 平衡二次搜索树
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
        //int[] arr = {4,3,6,5,7,8};
        //int[] arr = {10,12,8,9,7,6};
        int[] arr = {10,11,7,6,8,9};
        AVLTree avlTree = new AVLTree();
        for (int i=0; i<arr.length; i++){
            avlTree.add(new AVLNode(arr[i]));
        }
        avlTree.infixOrder(avlTree.root);
        System.out.println("树高："+ avlTree.height(avlTree.root));
        System.out.println("树左子树高："+ avlTree.leftHeight(avlTree.root));
        System.out.println("树右子树高："+ avlTree.rightHeight(avlTree.root));
        System.out.println("根节点："+avlTree.root.val);
    }
}

//AVL树
class AVLTree extends BinarySearchTree{
    AVLNode root;

    public int height(AVLNode node){
        return Math.max((node.left == null ? 0 : height(node.left)), (node.right == null ? 0 : height(node.right)))+1;
    }
    //返回左子树的高度
    public int leftHeight(AVLNode node){
        if(node.left == null){
            return 0;
        }
        return height(node.left);
    }

    //返回左子树的高度
    public int rightHeight(AVLNode node){
        if(node.right == null){
            return 0;
        }
        return height(node.right);
    }

    //二叉搜索树添加节点
    public void add(AVLNode node){
        if(this.root == null){
            this.root = node;
        }else{
            this.root.addNode(node);
        }
    }
    //中序遍历
    public void infixOrder(AVLNode node){
        if(node == null){
            return;
        }
        infixOrder(node.left);
        System.out.println(node.val);
        infixOrder(node.right);
    }


}

//AVL树节点
class AVLNode extends BSTNode{
    AVLNode left;
    AVLNode right;
    public AVLNode(int val) {
        super(val);
    }

    //左旋转方法
    public void leftRotate(){
        //以当前节点的值，创建新节点
        AVLNode newNode = new AVLNode(this.val);
        //把新节点的左子树设置为当前节点的左子树
        newNode.left = this.left;
        //把新节点的右子树设置成当前节点的右子树的左子树
        newNode.right = this.right.left;
        //把当前节点的值替换成其右子节点的值
        this.val = this.right.val;
        //把当前节点的右子树设置成其右子树的右子树
        this.right = this.right.right;
        //把当前节点的左子树设置成新的节点
        this.left = newNode;
    }

    //右旋转方法, 与左旋转相反
    public void rightRotate(){
        AVLNode newNode = new AVLNode(this.val);
        newNode.right = this.right;
        newNode.left = this.left.right;
        this.val = this.left.val;
        this.left = this.left.left;
        this.right = newNode;
    }

    //添加节点，递归添加，需满足二叉搜索树要求
    public void addNode(AVLNode node){
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

        //当添加完一个节点后，如果：（右子树高度 - 左子树高度）> 1，则左旋转
        if(rightHeight() - leftHeight() > 1){
            /**（双旋转）
             * 如果满足左旋转条件
             * 判断当前节点的右子树的左子树高度是否大于当前节点的右子树的右子树高度
             * 满足则当前节点的右子树先右旋转，再对当前节点左旋转
             */
            if(this.right != null && this.right.leftHeight() > this.right.rightHeight()){
                //对当前节点的右子树进行右旋转
                this.right.rightRotate();
            }
            leftRotate();
            return;
        }
        //当添加完一个节点后，如果：（左子树高度 - 右子树高度）> 1，则右旋转
        if(leftHeight() - rightHeight() > 1){
            /**（双旋转）
             * 如果满足右旋转条件
             * 判断当前节点的左子树的右子树高度是否大于当前节点的左子树的左子树高度
             * 满足则当前节点的左子树先左旋转，再对当前节点右旋转
             */
            if(this.left != null && this.left.rightHeight() >this.left.leftHeight()){
                //对当前节点的左子树进行左旋转
                this.left.leftRotate();
            }
            rightRotate();
        }

    }
    //返回以当前节点为根节点的树的高度
    public int height(){
        return Math.max((left == null ? 0 : left.height()), (right == null ? 0 : right.height()))+1;
    }
    //返回左子树的高度
    public int leftHeight(){
        if(left == null){
            return 0;
        }
        return left.height();
    }

    //返回左子树的高度
    public int rightHeight(){
        if(right == null){
            return 0;
        }
        return right.height();
    }


}
