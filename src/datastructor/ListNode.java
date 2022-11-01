package datastructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        one.next = two;
        two.next = three;
        three.next = four;
        four.next = five;
        one.rotateRight(one, 2);
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

    public ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        head.next = swapPairs2(next.next);
        next.next = head;
        return next;
    }

    public ListNode swapPairs3(ListNode head) {
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode temp = pre;
        while (temp.next != null && temp.next.next != null) {
            ListNode start = temp.next;
            ListNode end = temp.next.next;
            temp.next = end;
            start.next = end.next;
            end.next = start;
            temp = start;
        }
        return pre.next;
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
            } else if (headB.val < headA.val) {
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

    public ListNode oddEvenList2(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode evenHead = head.next;
        ListNode odd = head, even = evenHead;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }

    // 141. 环形链表  https://leetcode.cn/problems/linked-list-cycle/
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return false;
        }
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        while (slow != null && fast != null) {
            if (slow == fast) {
                return true;
            }
            slow = slow.next;
            fast = fast.next == null ? null : fast.next.next;
        }
        return false;
    }

    // 142. 环形链表 II https://leetcode.cn/problems/linked-list-cycle-ii/
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (set.contains(head)) {
                return head;
            }
            set.add(head);
            head = head.next;
        }
        return null;
    }
    public ListNode detectCycle2(ListNode head) { // 双指针法
        ListNode fast = head, slow = head;
        while (true) {
            if (fast == null || fast.next == null) return null;
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) break;
        }
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }

    // 61. 旋转链表 https://leetcode.cn/problems/rotate-list/ ,
    // 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return head;
        }
        int n = 0;
        ListNode tmp = head;
        while (tmp != null) {
            n++;
            tmp = tmp.next;
        }
        System.out.println("n=" + n);
        while (k >= n) {
            k = k - n;
        }
        if (k == 0) {
            return head;
        }
        tmp = head;
        int i = 0;
        ListNode start = null, end = null;
        while (true) {
            i++;
            if (i == n - k) {
                start = tmp.next;
                end = tmp;
                System.out.println("i=" + i + " start=" + start.val);
            }
            if (tmp.next == null) {
                tmp.next = head;
                break;
            }
            tmp = tmp.next;
        }
        if (end != null) {
            end.next = null;
        }
        System.out.println("tmp=" + tmp.val);
        return start;
    }
    // 特别地，当链表长度不大于 1，或者 k 为 n 的倍数时，新链表将与原链表相同，我们无需进行任何处理。
    public ListNode rotateRight2(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null) {
            return head;
        }
        int n = 1;
        ListNode iter = head;
        while (iter.next != null) {
            iter = iter.next;
            n++;
        }
        int add = n - k % n;
        if (add == n) {
            return head;
        }
        iter.next = head;
        while (add-- > 0) {
            iter = iter.next;
        }
        ListNode ret = iter.next;
        iter.next = null;
        return ret;
    }
}
