package dp;

import java.util.Arrays;

public class Solution {
    // https://blog.csdn.net/a515557595_xzb/article/details/88296989  最长公共子序列与最长公共子串【动态规划】  
    public static void main(String[] args) {
        Solution s = new Solution();
        s.climbStairs(5);
    }

    // 70. 爬楼梯 https://leetcode.cn/problems/climbing-stairs/?favorite=ex0k24j
    // 需要 n 阶你才能到达楼顶。每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        // 1 动态规划数组
//        int[] dp = new int[n + 1];
//        dp[1] = 1;
//        dp[2] = 2;
//        dp[3] = 3;
//        for (int i = 4; i <= n; i++) {
//            dp[i] = dp[i - 1] + dp[i - 2];
//        }
//        return dp[n];
        // 2 动态规划滚动
        int i = 3;
        int a = 1, b = 2, c = 3;
        while (i <= n) {
            c = a + b;
            a = b;
            b = c;
            i++;
        }
        return c;
    }

    // 把m个同样的苹果放在n个同样的盘子里，允许有的盘子空着不放，问共有多少种不同的分法？
    // 注意：如果有7个苹果和3个盘子，（5，1，1）和（1，5，1）被视为是同一种分法。
    public static int count(int apples, int n) {
        if (n == 1 || apples == 0) return 1;
        else if (n > apples) return count(apples, apples);
        else return count(apples, n - 1) + count(apples - n, n);
    }
}
