package DFS;

import datastructor.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Solution {
    int sum = 0;
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     * 剑指 Offer 34. 二叉树中和为某一值的路径
     * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
     *
     * 叶子节点 是指没有子节点的节点。
     *
     *  https://leetcode-cn.com/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof/
     *
     * 示例 1：
     *
     * 树中节点总数在范围 [0, 5000] 内
     * -1000 <= Node.val <= 1000
     * -1000 <= targetSum <= 1000
     * 注意：本题与主站 113 题相同：https://leetcode-cn.com/problems/path-sum-ii/
     *
     * @param root
     * @param target
     * @return res
     */
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<Integer> track =  new ArrayDeque<Integer>();
        track.add(root.val);
        sum += root.val;
        backTraverse(root, target, track, res);
        return res;
    }

    private void backTraverse(TreeNode node, int target, Deque<Integer> track, List<List<Integer>> res) {
        if (node.left == null && node.right == null) {
            if (sum == target) {
                res.add(new ArrayList<>(track));
            }
            return;
        }
        if (node.left != null) {
            track.add(node.left.val);
            sum += node.left.val;
            backTraverse(node.left, target, track, res);
            track.pollLast();
            sum -= node.left.val;
        }
        if (node.right != null) {
            track.add(node.right.val);
            sum += node.right.val;
            backTraverse(node.right, target, track, res);
            track.pollLast();
            sum -= node.right.val;
        }
    }

}
