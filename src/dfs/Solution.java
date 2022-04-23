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

    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<String>();
        backtrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }

    // 22. 括号生成 https://leetcode-cn.com/problems/generate-parentheses/
    public void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            ans.add(cur.toString());
            return;
        }
        if (open < max) {
            cur.append('(');
            backtrack(ans, cur, open + 1, close, max);
            cur.deleteCharAt(cur.length() - 1);
        }
        if (close < open) {
            cur.append(')');
            backtrack(ans, cur, open, close + 1, max);
            cur.deleteCharAt(cur.length() - 1);
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

    // 198. 打家劫舍 https://leetcode-cn.com/problems/house-robber/
    public int robO(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        int[] dp = new int[length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[length - 1];
    }

    // 213. 打家劫舍 II https://leetcode-cn.com/problems/house-robber-ii/
    public int rob(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        } else if (length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        return Math.max(robRange(nums, 0, length - 2), robRange(nums, 1, length - 1));
    }

    public int robRange(int[] nums, int start, int end) {
        int first = nums[start], second = Math.max(nums[start], nums[start + 1]);
        for (int i = start + 2; i <= end; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
    }

    // 337. 打家劫舍 III https://leetcode-cn.com/problems/house-robber-iii/
    public int rob(TreeNode root) {
        Map<TreeNode, Integer> selected = new HashMap<>();
        Map<TreeNode, Integer> unSelected = new HashMap<>();
        traverse(root, selected, unSelected);
        return Math.max(selected.getOrDefault(root, 0), unSelected.getOrDefault(root, 0));
    }

    private void traverse(TreeNode node, Map<TreeNode, Integer> selected, Map<TreeNode, Integer> unSelected) {
        if (node == null) {
            return;
        }
        traverse(node.left, selected, unSelected);
        traverse(node.right, selected, unSelected);
        selected.put(node, node.val + unSelected.getOrDefault(node.left, 0) + unSelected.getOrDefault(node.right, 0));

        int left = Math.max(selected.getOrDefault(node.left, 0), unSelected.getOrDefault(node.left, 0));
        int right = Math.max(selected.getOrDefault(node.right, 0), unSelected.getOrDefault(node.right, 0));
        unSelected.put(node, (left + right));
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
