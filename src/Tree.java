import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by 周智慧 on 2020/4/9.
 */
class BinTreeNode<T> {
    T value;
    BinTreeNode left;
    BinTreeNode right;

    BinTreeNode(T v) {
        value = v;
    }

    @Override
    public String toString() {
        return value + " ";
    }
}

public class Tree {
    public static void main(String[] args) {
        //参考：https://blog.csdn.net/stay_foolish12/article/details/89065550
        //https://blog.csdn.net/sdr_zd/article/details/82812823
        BinTreeNode four = new BinTreeNode(4);
        BinTreeNode five = new BinTreeNode(5);
        BinTreeNode six = new BinTreeNode(6);
        BinTreeNode seven = new BinTreeNode(7);
        BinTreeNode two = new BinTreeNode(2);
        BinTreeNode three = new BinTreeNode(3);
        BinTreeNode root = new BinTreeNode(1);
        root.left = two;
        root.right = three;
        two.left = four;
        two.right = five;
        three.left = six;
        three.right = seven;
        travPre(root);
        System.out.println("\n中序");
        travIn_I(root);
        System.out.println("\n中序2");
        travIn_I2(root);
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
    public static void travPreNormal(BinTreeNode root) {
        Stack<BinTreeNode> s = new Stack();
        s.push(root);
        while (!s.isEmpty()) {
            root = s.pop();
            System.out.print(root.toString());
            if (root.right != null) {
                s.push(root.right);
            }
            if (root.left != null) {
                s.push(root.left);
            }
        }
    }

    //--------pre--------------------------------------------------------------
    static void visitAlongLeftBranch(BinTreeNode x, Stack<BinTreeNode> s) {
        while (x != null) {
            System.out.print(x.toString());
            if (x.right != null) {
                s.push(x.right);
            }
            x = x.left;
        }
    }

    public static void travPre(BinTreeNode root) {
        Stack<BinTreeNode> s = new Stack();
        while (true) {
            visitAlongLeftBranch(root, s); //从当前节点出发，逐批访问
            if (s.isEmpty()) break; //直到栈空
            root = s.pop(); //弹出下一批的起点
        }
    }

    //--------in----------------------------------------------------------------
    static void goAlongLeftBranch(BinTreeNode x, Stack<BinTreeNode> s) {
        while (x != null) {
            s.push(x);
            x = x.left;
        } //当前节点入栈后随即向左侧分支深入，迭代直到无左孩子
    }

    static void travIn_I(BinTreeNode root) {//二叉树先序遍历算法（迭代版）
        Stack<BinTreeNode> s = new Stack();
        while (true) {
            goAlongLeftBranch(root, s); //从当前节点出发，逐批入栈
            if (s.empty()) break; //直至所有节点处理完毕
            root = s.pop();
            System.out.print(root); //弹出栈顶节点并访问之
            root = root.right; //转向右子树
        }
    }

    static void travIn_I2(BinTreeNode root) { //二叉树中序遍历算法（迭代版#2）
        Stack<BinTreeNode> S = new Stack();
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
    public static void posOrderTraverse(BinTreeNode head) {
        if (head != null) {
            Stack<BinTreeNode> stack1 = new Stack<>();
            Stack<BinTreeNode> stack2 = new Stack<>();     // 辅助栈，存储 根 -> 右 -> 左 的结果
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

    static void postorderTraversal(BinTreeNode root) {
        List<Object> result = new ArrayList<Object>();
        Stack<BinTreeNode> stack = new Stack<BinTreeNode>();
        stack.push(root);   //首先将根节点压栈
        while (!stack.isEmpty()) {
            BinTreeNode ele = stack.pop(); //首先出栈的为根节点，其后先出右子节点，后出左子节点
            if (ele.left != null)
                stack.push(ele.left);  //将左子节点压栈
            if (ele.right != null) {
                stack.push(ele.right); //将右子节点压栈
            }
            result.add(0, ele.value); //因为出栈顺序为“根右左”，所以需要每次将元素插入list开头
        }
        System.out.println(result);
    }

    //--------层次遍历-------BFS-------------------------------------------------------
    static void travLevel(BinTreeNode root) {
        LinkedList<BinTreeNode> q = new LinkedList<BinTreeNode>();
        q.add(root);
        while (!q.isEmpty()) {
            BinTreeNode x = q.pollFirst();
            System.out.print(x);
            if (x.left != null) q.add(x.left);
            if (x.right != null) q.add(x.right);
        }
    }

    public static void DFS(BinTreeNode root) {
        Stack<BinTreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            // 移除最后一个
            BinTreeNode tempNode = stack.pop();
            System.out.print(tempNode);
            // 后进先出
            if (tempNode.right != null)
                stack.add(tempNode.right);
            if (tempNode.left != null)
                stack.add(tempNode.left);

        }
    }

}
