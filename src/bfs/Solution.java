package bfs;

import datastructor.TreeNode;

import java.util.*;

// https://mp.weixin.qq.com/s/WH_XGm1-w5882PnenymZ7g
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        TreeNode four = new TreeNode(4);
        TreeNode five = new TreeNode(5);
        one.left = two;
        one.right = three;
        two.left = four;
        two.right = five;
        solution.diameterOfBinaryTree(one);
    }

    // 752. 打开转盘锁 https://leetcode-cn.com/problems/open-the-lock/
    public int openLock(String[] deadends, String target) {
        Set<String> deads = new HashSet<>();
        for (String s : deadends) deads.add(s);
        // 用集合不用队列，可以快速判断元素是否存在
        Set<String> q1 = new HashSet<>();
        Set<String> q2 = new HashSet<>();
        Set<String> visited = new HashSet<>();

        int step = 0;
        q1.add("0000");
        q2.add(target);

        while (!q1.isEmpty() && !q2.isEmpty()) {
            // 哈希集合在遍历的过程中不能修改，用 temp 存储扩散结果
            Set<String> temp = new HashSet<>();

            /* 将 q1 中的所有节点向周围扩散 */
            for (String cur : q1) {
                /* 判断是否到达终点 */
                if (deads.contains(cur))
                    continue;
                if (q2.contains(cur))
                    return step;
                visited.add(cur);

                /* 将一个节点的未遍历相邻节点加入集合 */
                for (int j = 0; j < 4; j++) {
                    String up = stirUp(cur, j);
                    if (!visited.contains(up))
                        temp.add(up);
                    String down = stirDown(cur, j);
                    if (!visited.contains(down))
                        temp.add(down);
                }
            }
            /* 在这里增加步数 */
            step++;
            // temp 相当于 q1
            // 这里交换 q1 q2，下一轮 while 就是扩散 q2
            q1 = q2;
            q2 = temp;
        }
        return -1;
    }

    private String stirUp(String str, int i) {
        char[] chars = str.toCharArray();
        if (chars[i] == '9') {
            chars[i] = '0';
        } else {
            chars[i] += 1;
        }
        return new String(chars);
    }

    private String stirDown(String str, int i) {
        char[] chars = str.toCharArray();
        if (chars[i] == '0') {
            chars[i] = '9';
        } else {
            chars[i] -= 1;
        }
        return new String(chars);
    }

    // 322. 零钱兑换 https://leetcode-cn.com/problems/coin-change/
    public int coinChange(int[] coins, int amount) {
        if (amount == 0)
            return 0;
        Arrays.sort(coins);
        Deque<Integer> queue = new LinkedList<>();
        queue.add(amount);
        Set<Integer> visited = new HashSet<>();
        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int res = queue.pollFirst();
                int startIndex = findMax(coins, res);
                for (int j = startIndex; j >= 0; j--) {
                    int remain = res - coins[j];
                    if (remain == 0) {
                        return ++ans;
                    }
                    if (remain > 0 && !visited.contains(remain)) {
                        visited.add(remain);
                        queue.addLast(remain);
                    }
                }
            }

            ++ans;
        }
        return -1;
    }

    private int findMax(int[] coins, int amount) {
        for (int i = coins.length - 1; i >= 0; i--) {
            if (coins[i] <= amount) {
                return i;
            }
        }
        return 0;
    }

    // 543. 二叉树的直径  https://leetcode.cn/problems/diameter-of-binary-tree/
    int diameter = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = root.left == null ? 0 : maxDepth(root.left);
        int right = root.right == null ? 0 : maxDepth(root.right);
        System.out.println("root=" + root.val + " left=" + left + " right=" + right); // 1 2 3 4 5
        diameter = Math.max(left + right, diameter);

        diameterOfBinaryTree(root.left);
        diameterOfBinaryTree(root.right);
        return diameter;
    }
    // 104. 二叉树的最大深度 https://leetcode.cn/problems/maximum-depth-of-binary-tree/
    private int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int depth = 0;
        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode item = deque.pollFirst();
                if (item.left != null) {
                    deque.add(item.left);
                }
                if (item.right != null) {
                    deque.add(item.right);
                }
            }
            ++depth;
        }
        return depth;
        // 2 深度优先搜索
//        if (root == null) {
//            return 0;
//        } else {
//            int leftHeight = maxDepth(root.left);
//            int rightHeight = maxDepth(root.right);
//            return Math.max(leftHeight, rightHeight) + 1;
//        }
    }

}
