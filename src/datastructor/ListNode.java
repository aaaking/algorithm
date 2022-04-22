package datastructor;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
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

    }
}
