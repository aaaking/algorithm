package datastructor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
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
        System.out.println("----后序遍历----");
        System.out.println("\n后序stack");
        posOrderTraverse(root);
        System.out.println("\n后序list");
        postorderTraversal(root);
        System.out.println("\nlevel-BFS'");
        travLevel(root);
        System.out.println("\n'DFS'");
        DFS(root);
    }

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
    }

    //--------pre--------------------------------------------------------------
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

    static void postorderTraversal(TreeNode root) {
        List<Object> result = new ArrayList<Object>();
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
        System.out.println(result);
    }

    //--------层次遍历-------BFS-------------------------------------------------------
    static void travLevel(TreeNode root) {
        LinkedList<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode x = q.pollFirst();
            System.out.print(x);
            if (x.left != null) q.add(x.left);
            if (x.right != null) q.add(x.right);
        }
    }

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

}
