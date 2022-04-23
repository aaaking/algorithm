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

    public ListNode sortList(ListNode head) {
        return sortList(head, null);
    }

    // 148. 排序链表 https://leetcode-cn.com/problems/sort-list/
    private ListNode sortList(ListNode head, ListNode tail) {
        if (head == null) {
            return head;
        }
        if (head.next == tail) {
            head.next = null;
            return head;
        }
        ListNode slow = head, fast = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }
        ListNode mid = slow;
        ListNode list1 = sortList(head, mid);
        ListNode list2 = sortList(mid, tail);
        return merge(list1, list2);
    }

    private ListNode merge(ListNode a, ListNode b) {
        ListNode dummyNode = new ListNode(0);
        ListNode temp = dummyNode, headA = a, headB = b;
        while (headA != null && headB != null) {
            if (headA.val <= headB.val) {
                temp.next = headA;
                headA = headA.next;
            } else if (headB.val < headA.val ) {
                temp.next = headB;
                headB = headB.next;
            }
            temp = temp.next;
        }
        if (headA != null) {
            temp.next = headA;
        }
        if (headB != null) {
            temp.next = headB;
        }
        return dummyNode.next;
    }
}
