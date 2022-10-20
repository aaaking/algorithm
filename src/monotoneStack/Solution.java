package monotoneStack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;
// https://mp.weixin.qq.com/s/Y2QZZ-coEYZ6ItDRyrdwVw
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

    // 84. 柱状图中最大的矩形 https://leetcode-cn.com/problems/largest-rectangle-in-histogram/
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        if (len == 0) {
            return 0;
        }
        if (len == 1) {
            return heights[0];
        }

        int res = 0;
        Deque<Integer> stack = new ArrayDeque<>(len);
        for (int i = 0; i < len; i++) {
            // 这个 while 很关键，因为有可能不止一个柱形的最大宽度可以被计算出来
            while (!stack.isEmpty() && heights[i] < heights[stack.peekLast()]) {
                int curHeight = heights[stack.pollLast()];
                // while (!stack.isEmpty() && heights[stack.peekLast()] == curHeight) {
                //     stack.pollLast();
                // }

                int curWidth;
                if (stack.isEmpty()) {
                    curWidth = i;
                } else {
                    curWidth = i - stack.peekLast() - 1;
                }

                // System.out.println("curWidth = " + curWidth + " " + curHeight * curWidth);
                res = Math.max(res, curHeight * curWidth);
            }
            stack.addLast(i);
            // System.out.println("stack=" + stack + " res=" + res);
        }

        while (!stack.isEmpty()) {
            int curHeight = heights[stack.pollLast()];
            // while (!stack.isEmpty() && heights[stack.peekLast()] == curHeight) {
            //     stack.pollLast();
            // }
            int curWidth;
            if (stack.isEmpty()) {
                curWidth = len;
            } else {
                curWidth = len - stack.peekLast() - 1;
            }
            res = Math.max(res, curHeight * curWidth);
        }
        return res;
    }
}
