package monotoneStack;

import java.util.Deque;
import java.util.LinkedList;

public class Solution {

    // 739. 每日温度 https://leetcode-cn.com/problems/daily-temperatures/
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] ret = new int[n];
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            int v = temperatures[i];
            while (!stack.isEmpty() && temperatures[stack.peekLast()] < v) {
                int j = stack.pollLast();
                ret[j] = i - j;
            }
            stack.addLast(i);
        }
        return ret;
    }
}
