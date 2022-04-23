package slidingWindow;

import datastructor.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {

    // 27. 移除元素 https://leetcode-cn.com/problems/remove-element/
    public int removeElement(int[] nums, int val) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[j++] = nums[i];
            }
        }
        return j;
    }

    // 141. 环形链表 https://leetcode-cn.com/problems/linked-list-cycle/
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    // 160. 相交链表 https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode tmpA = headA;
        ListNode tmpB = headB;
        while (tmpA != tmpB) {
            tmpA = tmpA == null ? headB : tmpA.next;
            tmpB = tmpB == null ? headA : tmpB.next;
        }
        return tmpB; //or tmpA
    }

    // 328. 奇偶链表 https://leetcode-cn.com/problems/odd-even-linked-list/
    // 给定单链表的头节点 head ，将所有索引为奇数的节点和索引为偶数的节点分别组合在一起，然后返回重新排序的列表。
    public ListNode oddEvenList(ListNode head) {
        if (head == null) return head;
        ListNode l = head, r = head.next, evenHead = head.next;
        while (l != null) {
            l.next = r == null ? null : r.next;
            if (r == null || r.next == null) {
                l.next = evenHead;
            }
            l = r == null ? null : r.next;
            if (r != null) {
                r.next = r.next == null ? null : r.next.next;
            }
            r = l == null ? null : l.next;
        }
        return head;
    }

    // 209. 长度最小的子数组 https://leetcode-cn.com/problems/minimum-size-subarray-sum/
    public int minSubArrayLen(int target, int[] nums) {
        int l = 0, r = 0;
        int minL = Integer.MAX_VALUE;
        int sums = 0;
        int n = nums.length;
        for (r = 0; r < n; r++) {
            sums += nums[r];
            while (sums >= target) {
                minL = Math.min(r - l + 1, minL);
                sums -= nums[l];
                l++;
            }
        }
        return minL == Integer.MAX_VALUE ? 0 : minL;
    }

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

    // 1456. 定长子串中元音的最大数目 https://leetcode-cn.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/
    public int maxVowels(String s, int k) {
        int max = 0;
        int sum = 0;
        int left = 0, right = 0;
        while (right < s.length()) {
            sum += isVowel(s.charAt(right));
            right++;
            if (right >= k) {
                max = Math.max(max, sum);
                sum -= isVowel(s.charAt(right - k));
            }
        }
        return max;
    }

    private int isVowel(char s) {
        return s == 'a' || s == 'e' || s == 'i' || s == 'o' || s == 'u' ? 1 : 0;
    }
}
