package dp;

public class Solution {
    public static void main(String[] args) {

    }

    // 把m个同样的苹果放在n个同样的盘子里，允许有的盘子空着不放，问共有多少种不同的分法？
    // 注意：如果有7个苹果和3个盘子，（5，1，1）和（1，5，1）被视为是同一种分法。
    public static int count(int apples, int n) {
        if (n == 1 || apples == 0) return 1;
        else if (n > apples) return count(apples, apples);
        else return count(apples, n - 1) + count(apples - n, n);
    }
}
