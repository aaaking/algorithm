package monotoneStack;

import java.util.*;

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
    
    // 496. 下一个更大元素 I	https://leetcode-cn.com/problems/next-greater-element-i/
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        Deque<Integer> stack = new ArrayDeque<Integer>();
        for (int i = nums2.length - 1; i >= 0; --i) {
            int num = nums2[i];
            while (!stack.isEmpty() && num >= stack.peek()) {
                stack.pop();
            }
            map.put(num, stack.isEmpty() ? -1 : stack.peek());
            stack.push(num);
        }
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; ++i) {
            res[i] = map.get(nums1[i]);
        }
        return res;
    }

    // 1475. 商品折扣后的最终价格	https://leetcode-cn.com/problems/final-prices-with-a-special-discount-in-a-shop/
    public int[] finalPrices(int[] prices) {
        int n = prices.length;
        int[] ans = new int[n];
        Deque<Integer> stack = new ArrayDeque<Integer>();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() > prices[i]) {
                stack.pop();
            }
            ans[i] = stack.isEmpty() ? prices[i] : prices[i] - stack.peek();
            stack.push(prices[i]);
        }
        return ans;
    }

    // 1504. 统计全 1 子矩形	https://leetcode-cn.com/problems/count-submatrices-with-all-ones/
    // 暴力：
    public int numSubmat(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int[][] row = new int[n][m];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (j == 0) {
                    row[i][j] = mat[i][j];
                } else if (mat[i][j] != 0) {
                    row[i][j] = row[i][j - 1] + 1;
                } else {
                    row[i][j] = 0;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                int col = row[i][j];
                for (int k = i; k >= 0 && col != 0; --k) {
                    col = Math.min(col, row[k][j]);
                    ans += col;
                }
            }
        }
        return ans;
    }

    // 单调栈
    public int numSubmat2(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int[][] row = new int[n][m];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (j == 0) {
                    row[i][j] = mat[i][j];
                } else if (mat[i][j] != 0) {
                    row[i][j] = row[i][j - 1] + 1;
                } else {
                    row[i][j] = 0;
                }
            }
        }
        int ans = 0;
        for (int j = 0; j < m; ++j) {
            int i = 0;
            Deque<int[]> Q = new LinkedList<int[]>();
            int sum = 0;
            while (i <= n - 1) {
                int height = 1;
                while (!Q.isEmpty() && Q.peekFirst()[0] > row[i][j]) {
                    // 弹出的时候要减去多于的答案
                    sum -= Q.peekFirst()[1] * (Q.peekFirst()[0] - row[i][j]);
                    height += Q.peekFirst()[1];
                    Q.pollFirst();
                }
                sum += row[i][j];
                ans += sum;
                Q.offerFirst(new int[]{row[i][j], height});
                i++;
            }
        }
        return ans;
    }

    // 907. 子数组的最小值之和	https://leetcode-cn.com/problems/sum-of-subarray-minimums/
}
