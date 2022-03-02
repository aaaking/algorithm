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
}
