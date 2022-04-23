package dfs;

import datastructor.TreeNode;

import java.util.*;

// https://blog.csdn.net/weixin_44052055/article/details/120986127  Java实现回溯算法入门（排列+组合+子集）
// https://blog.csdn.net/qq_43709922/article/details/109999024
public class Solution {
    int mVal = 0;
    int mSum = 0;

    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    // 剑指 Offer 34. 二叉树中和为某一值的路径 https://leetcode-cn.com/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof/
    // 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<Integer> track = new ArrayDeque<Integer>();
        track.add(root.val);
        mVal += root.val;
        backTraverse(root, target, track, res);
        return res;
    }

    private void backTraverse(TreeNode node, int target, Deque<Integer> track, List<List<Integer>> res) {
        if (node.left == null && node.right == null) {
            if (mVal == target) {
                res.add(new ArrayList<>(track));
            }
            return;
        }
        if (node.left != null) {
            track.add(node.left.val);
            mVal += node.left.val;
            backTraverse(node.left, target, track, res);
            track.pollLast();
            mVal -= node.left.val;
        }
        if (node.right != null) {
            track.add(node.right.val);
            mVal += node.right.val;
            backTraverse(node.right, target, track, res);
            track.pollLast();
            mVal -= node.right.val;
        }
    }

    /**
     * 3
     * 2       3
     * 3        1
     * <p>
     * 3
     * 4       5
     * 1   3        1
     * <p>
     * 4
     * 1
     * 2
     * 3
     *
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
        if (root == null) return 0;
        Deque<Integer> track = new ArrayDeque<Integer>();
        track.add(root.val);
        mSum += root.val;
        backTraverse(root, track);
        mVal = Math.max(mVal, mSum);
        mSum = 0;
        if (root.left != null) {
            track.clear();
            track.add(root.left.val);
            mSum += root.left.val;
            backTraverse(root.left, track);
            mVal = Math.max(mVal, mSum);
        }
        if (root.right != null) {
            track.clear();
            track.add(root.right.val);
            mSum += root.right.val;
            backTraverse(root.right, track);
            mVal = Math.max(mVal, mSum);
        }
        return mVal;
    }

    private void backTraverse(TreeNode node, Deque<Integer> track) {
        if (node.left == null && node.right == null) {
//            mVal = Math.max(mVal, mSum);
            return;
        }
        if (node.left != null && node.left.left != null) {
            track.add(node.left.left.val);
            mSum += node.left.left.val;
            backTraverse(node.left.left, track);
            track.pollLast();
//            mSum -= node.left.left.val;
        }
        if (node.left != null && node.left.right != null) {
            track.add(node.left.right.val);
            mSum += node.left.right.val;
            backTraverse(node.left.right, track);
            track.pollLast();
//            mSum -= node.left.right.val;
        }
        if (node.right != null && node.right.right != null) {
            track.add(node.right.right.val);
            mSum += node.right.right.val;
            backTraverse(node.right.right, track);
            track.pollLast();
//            mSum -= node.right.right.val;
        }
        if (node.right != null && node.right.left != null) {
            track.add(node.right.left.val);
            mSum += node.right.left.val;
            backTraverse(node.right.left, track);
            track.pollLast();
//            mSum -= node.right.left.val;
        }
    }

    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        // root 本身就是一层，depth 初始化为 1
        int depth = 1;

        while (!q.isEmpty()) {
            int sz = q.size();
            /* 将当前队列中的所有节点向四周扩散 */
            for (int i = 0; i < sz; i++) {
                TreeNode cur = q.poll();
                /* 判断是否到达终点 */
                if (cur.left == null && cur.right == null)
                    return depth;
                /* 将 cur 的相邻节点加入队列 */
                if (cur.left != null)
                    q.offer(cur.left);
                if (cur.right != null)
                    q.offer(cur.right);
            }
            /* 这里增加步数 */
            depth++;
        }
        return depth;
    }
}
