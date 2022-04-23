package monotoneStack;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

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

    // 42. 接雨水 https://leetcode-cn.com/problems/trapping-rain-water/
    public int trap(int[] height) {
        int sum = 0;
        Stack<Integer> stack = new Stack<>();
        int current = 0;
        while (current < height.length) {
            //如果栈不空并且当前指向的高度大于栈顶高度就一直循环
            while (!stack.empty() && height[current] > height[stack.peek()]) {
                int h = height[stack.peek()]; //取出要出栈的元素
                stack.pop(); //出栈
                if (stack.empty()) { // 栈空就出去
                    break;
                }
                int distance = current - stack.peek() - 1; //两堵墙之前的距离。
                int min = Math.min(height[stack.peek()], height[current]);
                System.out.println("increase data=" + distance * (min - h));
                sum = sum + distance * (min - h);
            }
            stack.push(current); //当前指向的墙入栈
            current++; //指针后移
        }
        return sum;
    }
}
