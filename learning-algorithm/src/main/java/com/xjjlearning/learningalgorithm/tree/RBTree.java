package com.xjjlearning.learningalgorithm.tree;

import java.util.Scanner;

/**        r
 *       /   \
 *     p      q
 *   / | \  / | \
 *  a  b c d  e  f
 * /|\
 *g h i
 * /

 /**
 * https://blog.csdn.net/weixin_43591980/article/details/109580974
 */
@SuppressWarnings("all")
public class RBTree<K extends Comparable<K>, V> {
    private static final boolean RED = true;// 红
    private static final boolean BLACK = false;// 黑
    /**
     * 树根的引用
     **/
    private RBNode root;

    public RBNode getRoot() {
        return root;
    }

    /**
     * 获取当前节点的父节点
     */
    private RBNode parentOf(RBNode node) {
        if (node != null) {
            return node.parent;
        }
        return null;
    }

    /**
     * 节点是否为红色
     */
    private boolean isRed(RBNode node) {
        if (node != null) {
            return node.color == RED;
        }
        return false;
    }

    /**
     * 节点是否为黑色
     */
    private boolean isBlack(RBNode node) {
        if (node != null) {
            return node.color == BLACK;
        }
        return false;
    }

    /**
     * 设置节点为红色
     */
    private void setRed(RBNode node) {
        if (node != null) {
            node.color = RED;
        }
    }

    /**
     * 设置节点为黑色
     */
    private void setBlack(RBNode node) {
        if (node != null) {
            node.color = BLACK;
        }
    }

    /**
     * 中序打印二叉树
     */
    public void inOrderPrint() {
        inOrderPrint(this.root);
    }

    private void inOrderPrint(RBNode node) {
        if (node != null) {
            inOrderPrint(node.left);
            System.out.println("key:" + node.key + ",value:" + node.value);
            inOrderPrint(node.right);
        }
    }

    /**
     * 左旋方法
     * 左旋示意图：左旋x节点
     *
     *   p                   p
     *   |                   |
     *   x                   y
     *  / \      ---->      / \
     * lx  y               x   ry
     *    / \             / \
     *   ly  ry          lx  ly
     *
     * 左旋做了几件事？
     * 1.将x的右子节点指向y的左子节点(ly)，并且把y的左子节点更新为x
     * 2.当x的父节点(不为空时)，更新y的父节点为x的父节点，并将x的父节点 指定 子树(当前x的子树位置) 指定为y
     * 3.将x的父节点更新为y，将y的左子节点更新为x
     */
    private void leftRotate(RBNode x) {
        RBNode y = x.right;// 获得y
        // 1.将x的右子节点指向y的左子节点(ly)，并且把y的左子节点更新为x
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }

        // 2.当x的父节点(不为空时)，更新y的父节点为x的父节点，并将x的父节点 指定 子树(当前x的子树位置) 指定为y
        if (x.parent != null) {
            y.parent = x.parent;
            if (x == x.parent.left) {// 如果x是其父节点的左子节点，则将y放在x父节点的左边
                x.parent.left = y;
            } else {
                x.parent.right = y;// 如果x是其父节点的右子节点，则将y放在x父节点的右边
            }
        } else {// 说明x为根节点，此时需要更新y为根节点 的引用
            this.root = y;
            this.root.parent = null;// 根节点无父节点
        }

        // 3.将x的父节点更新为y，将y的左子节点更新为x
        x.parent = y;
        y.left = x;
    }

    /**
     * 右旋方法
     * 右旋示意图：右旋y节点
     *
     *       p                       p
     *      /                       /
     *     y                       x
     *    / \          ---->      / \
     *   x   ry                  lx  y
     *  / \                         / \
     * lx  ly                      ly  ry
     *
     * 右旋都做了几件事？
     * 1.将y的左子节点指向x的右子节点，并且更新x的右子节点的父节点为y
     * 2.当y的父节点不为空时，更新x的父节点为y的父节点，更新y的父节点的指定子节点（y当前位置） 为x
     * 3.更新y 的父节点为x ,更新x 的右子节点为y
     */
    private void rightRotate(RBNode y) {
        RBNode x = y.left;// 获得 x
        // 1.将x的右子节点 赋值 给了 y 的左子节点，并且更新x的右子节点的父节点为 y
        y.left = x.right;
        if(x.right != null) {
            x.right.parent = y;
        }

        // 2.将y的父节点p（非空时）赋值给x的父节点，同时更新p的子节点为x（左或右）
        if(y.parent != null) {
            x.parent = y.parent;
            if(y.parent.left == y) {// 如果y是其父节点的左子节点，则将x放在y父节点的左边
                y.parent.left = x;
            } else {// 如果y是其父节点的右子节点，则将x放在y父节点的右边
                y.parent.right = x;
            }
        } else {// 说明y为根节点，此时需要更新x为根节点 的引用
            this.root = x;
            this.root.parent = null;// 根节点无父节点
        }

        // 3.将x的右子节点赋值为y，将y的父节点设置为x
        x.right = y;
        y.parent = x;
    }

    /**
     * public插入方法
     */
    public void insert(K key, V value) {
        RBNode node = new RBNode<>();
        node.setKey(key);
        node.setValue(value);
        // 新节点 一定要是红色！
        node.setColor(RED);

        insert(node);
    }

    private void insert(RBNode node) {
        // 第一步：查找当前要插入节点node的父节点
        RBNode parent = null;// 声明要插入节点node的父节点
        RBNode x = this.root;

        while (x != null) {
            parent = x;

            /**
             * cmp > 0 说明node.key 大于 x.key 需要到x 的右子树查找
             * cmp == 0 说明node.key 等于 x.key 需要进行替换操作
             * cmp < 0 说明node.key 小于 x.key 需要到x 的左子树查找
             */
            int cmp = node.key.compareTo(x.key);
            if (cmp > 0) {
                x = x.right;
            } else if (cmp == 0) {
                x.setValue(node.getValue());
                return;// 修改完后 就不再继续往下面的代码执行了
            } else {
                x = x.left;
            }
        }

        /**
         * 退出上面的while循环后，到这里，说明树中没有相同key 的元素
         * 需要添加新元素node到 x(parent) 目前位置的左子树/右子树
         */
        node.parent = parent;
        if (parent != null) {
            // 判断node与parent 的key 谁大
            int cmp = node.key.compareTo(parent.key);
            if (cmp > 0) {// 当前node的key比parent 的key大，需要把node放入parent 的右子节点
                parent.right = node;
            } else {// 当前node的key比parent 的key小，需要把node放入parent 的左子节点
                parent.left = node;
            }
        } else {// parent == null; 说明为空树
            this.root = node;// 直接给树根赋值为node
        }

        // 新元素node 加入树中之后，要调用修复红黑树平衡的方法
        insertFixUp(node);
    }

    /**
     * 插入后修复红黑树平衡的方法
     * |---情景1：如果红黑树为空树,需要将根节点染为黑色
     * |---情景2：如果插入节点的key已经存在,(这种情况不需要处理,因为修改树中的值不会触发红黑树修复平衡方法)
     * |---情景3：如果插入节点的父节点为黑色,这种情况不需要处理,(参考红黑树的性质4和性指5去理解)
     * (因为所插入的路径中,黑色节点数没发生变化,所以红黑树依然平衡)
     *
     * 情景4 需要去处理的情景
     * |---情景4：插入节点的父节点为红色,(违反红黑树性质4,不能有两个红色节点相连)
     * |---情景4.1：叔叔节点存在，并且为红色（父-叔 双红）
     * 处理：将爸爸和叔叔染成黑色，将爷爷染成红色，并且再以爷爷节点为当前节点，进行下一轮处理
     * |---情景4.2：叔叔节点不存在，或者为黑色，父节点为爷爷节点的左子树
     * 处理：
     * |---情景4.2.1：插入节点为其父节点的左子节点（LL情况）
     * 处理：将父节点染为黑色，将爷爷染为红色，然后以爷爷节点右旋即可
     * |---情景4.2.2：插入节点为其父节点的右子节点（LR情况）
     * 处理：将父节点进行一次左旋，得到LL双红情景(4.2.1),然后指定父节点为当前节点进行下一轮处理
     * |---情景4.3：叔叔节点不存在，或者为黑色，父节点为爷爷节点的右子树
     * |---情景4.3.1：插入节点为其父节点的右子节点（RR情况）
     * 处理：将父节点染为黑色，将爷爷节点染为红色，然后以爷爷节点左旋即可
     * |---情景4.3.2：插入节点为其父节点的左子节点（RL情况）
     * 处理：以父节点进行一次右旋，得到RR双红情景(4.3.1),然后指定父节点为当前节点进行下一轮处理
     */
    private void insertFixUp(RBNode node) {
        RBNode parent = parentOf(node);// 当前节点的父节点
        RBNode gparent = parentOf(parent);// 当前节点的爷爷节点
        // 存在父节点且父节点为红色
        if (parent != null && isRed(parent)) {
            // 父节点是红色的，那么一定存在爷爷节点(性质2：根节点只能是黑色)

            // 父节点为爷爷节点的左子树
            if (parent == gparent.left) {
                RBNode uncle = gparent.right;
                // 情景4.1：叔叔节点存在，并且为红色（父-叔 双红）
                // 将父和叔染色为黑色，再将爷爷染红，并将爷爷设置为当前节点，进入下一次循环判断
                if (uncle != null && isRed(uncle)) {
                    setBlack(parent);
                    setBlack(uncle);
                    setRed(gparent);
                    insertFixUp(gparent);
                    return;
                }

                // 情景4.2：叔叔节点不存在，或者为黑色，父节点为爷爷节点的左子树
                if (uncle == null || isBlack(uncle)) {
                    /**
                     * 情景4.2.1：插入节点为其父节点的左子节点（LL情况）
                     * 处理：将父节点染为黑色，将爷爷染为红色，然后以爷爷节点右旋即可
                     */
                    // 插入节点为其父节点的左子节点（LL情况）=>
                    // 变色（父节点变黑，爷爷节点变红），右旋爷爷节点
                    if (node == parent.left) {
                        setBlack(parent);
                        setRed(gparent);
                        rightRotate(gparent);// 以gparent 右旋
                    }

                    /**
                     * 情景4.2.2：插入节点为其父节点的右子节点（LR情况）
                     * 处理：将父节点进行一次左旋，得到LL双红情景(4.2.1),然后指定父节点为当前节点进行下一轮处理
                     */
                    // 插入节点为其父节点的右子节点（LR情况）=>
                    // 左旋（父节点），当前节点设置为父节点，进入下一次循环
                    if (node == parent.right) {
                        leftRotate(parent);// parent 左旋
                        insertFixUp(parent);// 进行下一轮处理
                        return;
                    }
                }
            }
            else {// 父节点为爷爷节点的右子树
                RBNode uncle = gparent.left;
                // 情景4.1：叔叔节点存在，并且为红色（父-叔 双红）
                // 将父和叔染色为黑色，再将爷爷染红，并将爷爷设置为当前节点，进入下一次循环判断
                if (uncle != null && isRed(uncle)) {
                    setBlack(parent);
                    setBlack(uncle);
                    setRed(gparent);
                    insertFixUp(gparent);// 进行下一轮处理
                    return;
                }

                // 情景4.3：叔叔节点不存在，或者为黑色，父节点为爷爷节点的右子树
                if (uncle == null || isBlack(uncle)) {
                    /**
                     * 情景4.3.1：插入节点为其父节点的右子节点（RR情况）
                     * 处理：将父节点染为黑色，将爷爷节点染为红色，然后以爷爷节点左旋即可
                     */
                    // 插入节点为其父节点的右子节点（RR情况）=>
                    // 变色（父节点变黑，爷爷节点变红），右旋爷爷节点
                    if (node == parent.right) {
                        setBlack(parent);
                        setRed(gparent);
                        leftRotate(gparent);
                    }

                    /**
                     * 情景4.3.2：插入节点为其父节点的左子节点（RL情况）
                     * 处理：以父节点进行一次右旋，得到RR双红情景(4.3.1),然后指定父节点为当前节点进行下一轮处理
                     */
                    // 插入节点为其父节点的左子节点（RL情况）
                    // 右旋（父节点）得到RR情况，当前节点设置为父节点，进入下一次循环
                    if (node == parent.left) {
                        rightRotate(parent);
                        insertFixUp(parent);
                        return;
                    }
                }
            }
        }
        setBlack(this.root);
    }

    // 静态内部类
    static class RBNode<K extends Comparable<K>, V> {
        private RBNode parent;// 父节点
        private RBNode left;// 左子树
        private RBNode right;// 右子树
        private boolean color;// 颜色
        private K key;// 键
        private V value;// 值

        public RBNode(RBNode parent, RBNode left, RBNode right, boolean color, K key, V value) {
            this.parent = parent;
            this.left = left;
            this.right = right;
            this.color = color;
            this.key = key;
            this.value = value;
        }

        public RBNode() {
        }

        public RBNode getParent() {
            return parent;
        }

        public void setParent(RBNode parent) {
            this.parent = parent;
        }

        public RBNode getLeft() {
            return left;
        }

        public void setLeft(RBNode left) {
            this.left = left;
        }

        public RBNode getRight() {
            return right;
        }

        public void setRight(RBNode right) {
            this.right = right;
        }

        public boolean isColor() {
            return color;
        }

        public void setColor(boolean color) {
            this.color = color;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    /**
     * 下面是测试部分
     */
    public static void main(String[] args) {
        RBTree<String, Object> rbtree = new RBTree();
        //测试输入：ijkgefhdabc
        while(true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入key:");
            String key = sc.next();

            rbtree.insert(key, null);
            TreeOperation.show(rbtree.getRoot());
        }
    }

    static class TreeOperation {
    /*
           树的结构示例：
              1
            /   \
          2       3
         / \     / \
        4   5   6   7
    */

        // 用于获得树的层数
        public static int getTreeDepth(RBTree.RBNode root) {
            return root == null ? 0 : (1 + Math.max(getTreeDepth(root.getLeft()), getTreeDepth(root.getRight())));
        }


        private static void writeArray(RBTree.RBNode currNode, int rowIndex, int columnIndex, String[][] res, int treeDepth) {
            // 保证输入的树不为空
            if (currNode == null) return;
            // 先将当前节点保存到二维数组中
            res[rowIndex][columnIndex] = String.valueOf(currNode.getKey() /*+ "-" + (currNode.isColor() ? "R" : "B") + ""*/);

            // 计算当前位于树的第几层
            int currLevel = ((rowIndex + 1) / 2);
            // 若到了最后一层，则返回
            if (currLevel == treeDepth) return;
            // 计算当前行到下一行，每个元素之间的间隔（下一行的列索引与当前元素的列索引之间的间隔）
            int gap = treeDepth - currLevel - 1;

            // 对左儿子进行判断，若有左儿子，则记录相应的"/"与左儿子的值
            if (currNode.getLeft() != null) {
                res[rowIndex + 1][columnIndex - gap] = "/";
                writeArray(currNode.getLeft(), rowIndex + 2, columnIndex - gap * 2, res, treeDepth);
            }

            // 对右儿子进行判断，若有右儿子，则记录相应的"\"与右儿子的值
            if (currNode.getRight() != null) {
                res[rowIndex + 1][columnIndex + gap] = "\\";
                writeArray(currNode.getRight(), rowIndex + 2, columnIndex + gap * 2, res, treeDepth);
            }
        }


        public static void show(RBTree.RBNode root) {
            if (root == null) System.out.println("EMPTY!");
            // 得到树的深度
            int treeDepth = getTreeDepth(root);

            // 最后一行的宽度为2的（n - 1）次方乘3，再加1
            // 作为整个二维数组的宽度
            int arrayHeight = treeDepth * 2 - 1;
            int arrayWidth = (2 << (treeDepth - 2)) * 3 + 1;
            // 用一个字符串数组来存储每个位置应显示的元素
            String[][] res = new String[arrayHeight][arrayWidth];
            // 对数组进行初始化，默认为一个空格
            for (int i = 0; i < arrayHeight; i++) {
                for (int j = 0; j < arrayWidth; j++) {
                    res[i][j] = " ";
                }
            }

            // 从根节点开始，递归处理整个树
            // res[0][(arrayWidth + 1)/ 2] = (char)(root.val + '0');
            writeArray(root, 0, arrayWidth / 2, res, treeDepth);

            // 此时，已经将所有需要显示的元素储存到了二维数组中，将其拼接并打印即可
            for (String[] line : res) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < line.length; i++) {
                    sb.append(line[i]);
                    if (line[i].length() > 1 && i <= line.length - 1) {
                        i += line[i].length() > 4 ? 2 : line[i].length() - 1;
                    }
                }
                System.out.println(sb.toString());
            }
        }
    }
}