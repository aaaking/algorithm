package datastructor;

import java.util.*;

// https://blog.csdn.net/stay_foolish12/article/details/89065550
// https://blog.csdn.net/sdr_zd/article/details/82812823
/*
 *        1
 *    2       3
 *  4   5   6   7
 *
 * */
public class Tree {
    public static void main(String[] args) {
        Tree tree=new Tree();
        TreeNode four = new TreeNode(4);
        TreeNode five = new TreeNode(5);
        TreeNode six = new TreeNode(6);
        TreeNode seven = new TreeNode(7);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        TreeNode root = new TreeNode(1);
        root.left = two;
        root.right = three;
        two.left = four;
        two.right = five;
        three.left = six;
        three.right = seven;
        System.out.println("----先序遍历----");
        travPreNormal(root);
        travPre(root);
        System.out.println("\n----中序遍历----");
        System.out.println("\n中序");
        travIn_I(root);
        System.out.println("\n中序2");
        travIn_I2(root);
        System.out.println("\n----后序遍历----");
        System.out.println("\n后序stack");
        posOrderTraverse(root);
        System.out.println("\n后序list");
        tree.postorderTraversal(root);
        System.out.println("\n----level-BFS-------");
        travLevel(root);
        System.out.println("\n------DFS-----");
        DFS(root);
        System.out.println();
        dfsTraverse(root);
    }

    //--------pre--------------------------------------------------------------
    //先序遍历，迭代方法
    public static void travPreNormal(TreeNode root) {
        Stack<TreeNode> s = new Stack();
        s.push(root);
        while (!s.isEmpty()) {
            root = s.pop();
            System.out.print(root);
            if (root.right != null) {
                s.push(root.right);
            }
            if (root.left != null) {
                s.push(root.left);
            }
        }
        System.out.println();
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preorderTraversal(root, list);
        return list;
    }
    public void preorderTraversal(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        if (root.left != null) {
            preorderTraversal(root.left, list);
        }
        if (root.right != null) {
            preorderTraversal(root.right, list);
        }
    }

    static void visitAlongLeftBranch(TreeNode x, Stack<TreeNode> s) {
        while (x != null) {
            System.out.print(x.toString());
            if (x.right != null) {
                s.push(x.right);
            }
            x = x.left;
        }
    }

    public static void travPre(TreeNode root) {
        Stack<TreeNode> s = new Stack();
        while (true) {
            visitAlongLeftBranch(root, s); //从当前节点出发，逐批访问
            if (s.isEmpty()) break; //直到栈空
            root = s.pop(); //弹出下一批的起点
        }
    }

    //--------in----------------------------------------------------------------
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorderTraversal(root, list);
        return list;
    }

    public void inorderTraversal(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            inorderTraversal(root.left, list);
        }
        list.add(root.val);
        if (root.right != null) {
            inorderTraversal(root.right, list);
        }
    }

    static void goAlongLeftBranch(TreeNode x, Stack<TreeNode> s) {
        while (x != null) {
            s.push(x);
            x = x.left;
        } //当前节点入栈后随即向左侧分支深入，迭代直到无左孩子
    }

    static void travIn_I(TreeNode root) {//二叉树先序遍历算法（迭代版）
        Stack<TreeNode> s = new Stack();
        while (true) {
            goAlongLeftBranch(root, s); //从当前节点出发，逐批入栈
            if (s.empty()) break; //直至所有节点处理完毕
            root = s.pop();
            System.out.print(root); //弹出栈顶节点并访问之
            root = root.right; //转向右子树
        }
    }

    static void travIn_I2(TreeNode root) { //二叉树中序遍历算法（迭代版#2）
        Stack<TreeNode> S = new Stack();
        while (true)
            if (root != null) {
                S.push(root); //根节点进栈
                root = root.left; //深入遍历左子树
            } else if (!S.empty()) {
                root = S.pop(); //尚未访问的最低祖先节点退栈
                System.out.print(root);
                root = root.right; //遍历祖先的右子树

            } else
                break; //遍历完成

    }

    //--------post----------------------------------------------------------------
    public static void posOrderTraverse(TreeNode head) {
        if (head != null) {
            Stack<TreeNode> stack1 = new Stack<>();
            Stack<TreeNode> stack2 = new Stack<>();     // 辅助栈，存储 根 -> 右 -> 左 的结果
            stack1.push(head);
            while (!stack1.isEmpty()) {
                head = stack1.pop();
                stack2.push(head);
                // 有左孩子就先压入左孩子
                if (head.left != null)
                    stack1.push(head.left);
                // 有右孩子就后压入右孩子
                if (head.right != null)
                    stack1.push(head.right);
            }
            // 逆序打印 根 -> 右 -> 左 的结果，就是后序遍历的结果
            while (!stack2.isEmpty())
                System.out.print(stack2.pop());
        }
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);   //首先将根节点压栈
        while (!stack.isEmpty()) {
            TreeNode ele = stack.pop(); //首先出栈的为根节点，其后先出右子节点，后出左子节点
            if (ele.left != null)
                stack.push(ele.left);  //将左子节点压栈
            if (ele.right != null) {
                stack.push(ele.right); //将右子节点压栈
            }
            result.add(0, ele.val); //因为出栈顺序为“根右左”，所以需要每次将元素插入list开头
        }
        return result;
    }

    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postorderTraversal(root, list);
        return list;
    }
    public void postorderTraversal(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            postorderTraversal(root.left, list);
        }
        if (root.right != null) {
            postorderTraversal(root.right, list);
        }
        list.add(root.val);
    }

    //--------层次遍历-------BFS-------------------------------------------------------
    static List<List<Integer>> travLevel(TreeNode root) {
        List<List<Integer>> temp = new LinkedList<>();
        if (root == null) return temp;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.addFirst(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> levelList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.pollFirst();
                levelList.add(node.val);
                if (node.left != null) {
                    queue.addLast(node.left);
                }
                if (node.right != null) {
                    queue.addLast(node.right);
                }
            }
            temp.add(0, levelList);
        }
        return temp;
    }

    //--------深度遍历-------DFS-------------------------------------------------------
    public static void DFS(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            // 移除最后一个
            TreeNode tempNode = stack.pop();
            System.out.print(tempNode);
            // 后进先出
            if (tempNode.right != null)
                stack.add(tempNode.right);
            if (tempNode.left != null)
                stack.add(tempNode.left);

        }
    }

    //--------深度遍历-------递归-------------------------------------------------------
    public static void dfsTraverse(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root);
        dfsTraverse(root.left);
        dfsTraverse(root.right);
    }

    // 108. 将有序数组转换为二叉搜索树 https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/
    public TreeNode sortedArrayToBST(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (end + start) / 2;
        TreeNode head = new TreeNode(nums[mid]);
        head.left = sortedArrayToBST(nums, start, mid - 1);
        head.right = sortedArrayToBST(nums, mid + 1, end);
        return head;
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length-1);
    }

}
