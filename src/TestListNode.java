import datastructor.ListNode;

public class TestListNode {

    public static void main(String[] args) {
        TestListNode test = new TestListNode();
//        Scanner sc = new Scanner(System.in);
//        String n = sc.next();
        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(3);
        ListNode a3 = new ListNode(4);
        a1.next = a2;
        a2.next = a3;
        ListNode b1 = new ListNode(1);
        ListNode b2 = new ListNode(4);
        ListNode b3 = new ListNode(5);
        b1.next = b2;
        b2.next = b3;
        ListNode c1 = new ListNode(2);
        ListNode c2 = new ListNode(6);
        c1.next = c2;
        System.out.println(test.mergeKLists(new ListNode[]{new ListNode(1), new ListNode(0)}));
        System.out.println(~10);
        System.out.println(~(-1));
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length <= 0) {
            return null;
        }
        ListNode[] record = new ListNode[lists.length];
        ListNode head = lists[0];
        int headI = 0;
        for (int i = 1; i < lists.length; i++) {
            if (head == null || (lists[i] != null && lists[i].val < head.val)) {
                head = lists[i];
                headI = i;
            }
        }
        if (head == null) {
            return null;
        }
        for (int i = 0; i < lists.length; i++) {
            record[i] = head == lists[i] ? lists[i].next : lists[i];
        }
//        System.out.println(head);
//        System.out.println(Arrays.toString(record));
        ListNode target = record[headI];//record[0];//headI > 0 ? record[headI - 1] : record[headI + 1];
//        int start = headI > 0 ? headI - 1 : headI + 1;
        int changeIndex = headI;
        int nilCnt = 0;//
        for (ListNode node: record) {
            if (node == null) {
                nilCnt++;
            }
        }
        ListNode next = head;
        while (true) {
            for (int i = 0; i < record.length; i++) {
                if (target == null || (record[i] != null && record[i].val < target.val)) {
                    target = record[i];
                    changeIndex = i;
                }
            }
            if (next != null) {
                next.next = (target != null ? new ListNode(target.val) : null);
                next = next.next;
            }
            target = (target != null ? target.next : null);
            record[changeIndex] = target;
//            System.out.println(Arrays.toString(record) + target + " " + nilCnt + " " +changeIndex);
            if (target == null) {
                nilCnt++;
                if (nilCnt >= record.length) {
                    break;
                }
            }
        }
        return head;
    }
}
