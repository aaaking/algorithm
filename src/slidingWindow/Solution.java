package slidingWindow;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {

    // 1208. 尽可能使字符串相等 https://leetcode-cn.com/problems/get-equal-substrings-within-budget/
    public int equalSubstring(String s, String t, int maxCost) {
        int left = 0, right = 0;
        int max = 0;
        int sum = 0;
        while (right < s.length()) {
            sum += Math.abs(s.charAt(right) - t.charAt(right));
            right++;
            while (sum > maxCost) {
                sum -= Math.abs(s.charAt(left) - t.charAt(left));
                left++;
            }
            max = Math.max(max, right - left);
        }
        return max;
    }

    // 239. 滑动窗口最大值 https://leetcode-cn.com/problems/sliding-window-maximum/
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length - k + 1;
        int[] ret = new int[n];
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < k; i++) {
            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) {
                queue.pollLast();
            }
            queue.addLast(i);
        }
        int index = 0;
        ret[index++] = nums[queue.peekFirst()];

        for (int right = k; right < nums.length; right++) {
            if (queue.peekFirst() == right - k) {
                queue.pollFirst();
            }

            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[right]) {
                queue.pollLast();
            }
            queue.addLast(right);
            ret[index++] = nums[queue.peekFirst()];
        }
        return ret;
    }
}
