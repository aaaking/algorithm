package dfs;

import datastructor.TreeNode;

import java.util.*;

// https://blog.csdn.net/weixin_44052055/article/details/120986127  Java实现回溯算法入门（排列+组合+子集）
public class Solution {
    int mVal = 0;
    int mSum = 0;

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.subsetsWithDup(new int[]{2, 2, 2}));
    }

    // 90. 子集 II https://leetcode-cn.com/problems/subsets-ii/
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i <= nums.length; i++) {
            dfs(nums, new ArrayList<>(), ans, 0, i);
        }
        return ans;
    }

    private void dfs(int[] nums, List<Integer> path, List<List<Integer>> ans, int start, int k) {
        if (path.size() == k) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i - 1] == nums[i]) {
                continue;
            }
            path.add(nums[i]);
            dfs(nums, path, ans, i + 1, k);
            path.remove(path.size() - 1);
        }
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

    // 17. 电话号码的字母组合 https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits.length() <= 0) {
            return ans;
        }
        dfs17(digits, new StringBuilder(), ans, 0);
        return ans;
    }

    private void dfs17(String digits, StringBuilder path, List<String> ans, int start) {
        if (path.length() == digits.length()) {
            ans.add(String.valueOf(path));
        } else {
            char[] chars = getChars(digits.charAt(start));
            for (int i = 0; i < chars.length; i++) {
                path.append(chars[i]);
                dfs17(digits, path, ans, start + 1);
                path.deleteCharAt(path.length() - 1);
            }
        }
    }

    private char[] getChars(char c) {
        if (c == '2') {
            return new char[]{'a', 'b', 'c'};
        } else if (c == '3') {
            return new char[]{'d', 'e', 'f'};
        } else if (c == '4') {
            return new char[]{'g', 'h', 'i'};
        } else if (c == '5') {
            return new char[]{'j', 'k', 'l'};
        } else if (c == '6') {
            return new char[]{'m', 'n', 'o'};
        } else if (c == '7') {
            return new char[]{'p', 'q', 'r', 's'};
        } else if (c == '8') {
            return new char[]{'t', 'u', 'v'};
        } else if (c == '9') {
            return new char[]{'w', 'x', 'y', 'z'};
        }
        return null;
    }
}
