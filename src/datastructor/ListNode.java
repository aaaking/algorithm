package datastructor;

import java.util.ArrayList;
import java.util.List;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    public ListNode(int x, ListNode next) {
        val = x;
        this.next = next;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(val + "");
        ListNode haha = next;
        while (true) {
            if (haha == null) {
                break;
            }
            sb.append("->" + String.valueOf(haha.val));
            haha = haha.next;
        }
        return sb.toString();
    }

    public static void main(String[] args) {

    }

    // 19. 删除链表的倒数第 N 个结点 , https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode first = head;
        ListNode second = dummy;
        for (int i = 0; i < n; ++i) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        ListNode ans = dummy.next;
        return ans;
    }

    // 24. 两两交换链表中的节点 https://leetcode-cn.com/problems/swap-nodes-in-pairs/
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode ans = head.next;
        ListNode pre = head;
        ListNode parent = null;
        while (pre != null) {
            ListNode mid = pre.next;
            ListNode next = mid == null ? null : mid.next;
            pre.next = next;
            if (mid != null) {
                mid.next = pre;
            }
            if (parent != null) {
                parent.next = mid == null ? pre : mid;
            }
            parent = pre;
            pre = next;
        }
        return ans;
    }
}
