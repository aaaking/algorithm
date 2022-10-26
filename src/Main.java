import datastructor.ListNode;
import datastructor.TreeNode;

import java.util.*;

public class Main {

    public String longest(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        int max = 0;
        int start = 0;
        for (int i = 0; i < len - 1; i++) {
            int oddLen = get(s, i, i);
            int evenLen = get(s, i, i + 1);
            int curMaxLen = Math.max(oddLen, evenLen);
            if (curMaxLen > max) {
                max = curMaxLen;
                start = i - (max - 1) / 2;
            }
            if((len-i-2)*2+1 <max){
                return s.substring(start, start + max);
            }
        }
        return s.substring(start, start + max);
    }

    public int get(String s, int i, int j) {
        char[] chars = s.toCharArray();
        while (i >= 0 && j < chars.length && chars[i] == chars[j]) {
            i--;
            j++;
        }
        return j - i - 1;
    }

    public static void main(String[] args) {
        Main main = new Main();
        int[][] datas = new int[][]{
                new int[]{1, 2},
                {1, 2}
        };
//        datas[0] = new int[]{1, 4};
//        datas[1] = new int[]{0, 2};
//        datas[2] = new int[]{3, 5};

//        datas[0] = new int[]{1, 3};
//        datas[1] = new int[]{2, 6};
//        datas[2] = new int[]{8, 10};
//        datas[3] = new int[]{15, 18};

        datas[0] = new int[]{1, 4};
        datas[1] = new int[]{2, 3};
        TreeNode root = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        TreeNode four = new TreeNode(4);
        TreeNode five = new TreeNode(5);
        root.left = two;
//        root.right = three;
//        two.left = four;
//        two.right = five;
        System.out.println(main.diameterOfBinaryTree(root));
        System.out.println( 1 > 2 ? 0 : 1+9);
    }

    public int diameterOfBinaryTree(TreeNode root) {
        int max = 0;
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addLast(root);
        while (!deque.isEmpty()) {
            int n = deque.size();
            while ((n--) > 0) {
                TreeNode node = deque.pollLast();
                max = Math.max(max, getMax(node));
                if (node.left != null) {
                    deque.addLast(node.left);
                }
                if (node.right != null) {
                    deque.addLast(node.right);
                }
            }
        }
        return max;
    }

    private int getMax(TreeNode root) {
        int left = root == null || root.left == null ? 0 : (1 + dfs(root.left));
        int right = root == null || (root.right == null) ? 0 : (1 + dfs(root.right));
        return left + right;
    }

    private int dfs(TreeNode node) {
        if (node == null || (node.left == null && node.right == null)) {
            return 0;
        }
        return 1 + Math.max(dfs(node.left), dfs(node.right));
    }


    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 1) {
            return intervals;
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        List<int[]> helperList = new ArrayList<>();
        int n = intervals.length;
        int i = 0, j = 1;
        boolean[] added = new boolean[n];
        for (; i < n - 1; i++) {
            j = i + 1;
            int[] merge = merge2(intervals[i], intervals[j]);
            if (merge == null) {
                if (!added[i]) {
                    added[i] = true;
                    helperList.add(intervals[i]);
                }
                if (i == n - 2) {
                    helperList.add(intervals[n - 1]);
                    break;
                }
            } else {
                helperList.remove(intervals[i]);
                helperList.add(merge);
                intervals[j] = merge;
                added[j] = true;
            }
            System.out.println("222  i=" + i + " merge=" + Arrays.toString(merge));
//            if ((i == datas.length-1 && merge == null) || i == datas.length) {
//                helperList.add(datas[datas.length-1]);
//                break;
//            }
        }
        //
        int[][] result = new int[helperList.size()][];
        for (int k = 0; k < helperList.size(); k++) {
            result[k] = helperList.get(k);
        }
        return result;
    }

    private int[] merge2(int[] a, int[] b) {
        if (b[0] > a[1]) {
            return null;
        }
        return new int[]{a[0], Math.max(a[1], b[1])};
    }


    public int getMinimumDifference(TreeNode root) {
        int minDiff = Integer.MAX_VALUE;
        List<TreeNode> path = new ArrayList<>();
        inTravel(root, path);
        for (int i = 0; i < path.size() - 1; i++) {
            minDiff = Math.min(minDiff, Math.abs(path.get(i).val - path.get(i + 1).val));
        }
        return minDiff;
    }

    /**
     * 236
     * 104       701
     * 227        991
     */
    // [236,104,701,null,227,null,911]
    private void inTravel(TreeNode node, List<TreeNode> path) {
        if (node != null) {
            inTravel(node.left, path);
            path.add(node);
            inTravel(node.right, path);
        }
    }

    public int numMatchingSubseq(String S, String[] words) {
        int ans = 0;
        ArrayList<Node>[] heads = new ArrayList[26];
        for (int i = 0; i < 26; ++i)
            heads[i] = new ArrayList<Node>();

        for (String word : words)
            heads[word.charAt(0) - 'a'].add(new Node(word, 0));

        for (char c : S.toCharArray()) {
            ArrayList<Node> old_bucket = heads[c - 'a'];
            heads[c - 'a'] = new ArrayList<Node>();

            for (Node node : old_bucket) {
                node.index++;
                if (node.index == node.word.length()) {
                    ans++;
                } else {
                    heads[node.word.charAt(node.index) - 'a'].add(node);
                }
            }
            old_bucket.clear();
        }
        return ans;
    }

    class Node {
        String word;
        int index;

        public Node(String w, int i) {
            word = w;
            index = i;
        }
    }

    private static boolean isIp(String s, int[] ipFlags, boolean checkMask) {
        int dotCnt = 0;
        boolean ok = true;
        boolean maskValid = true;
        long num1 = -1;
        long num2 = -1;
        long num3 = -1;
        long num4 = -1;
        out:
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                int num = c - '0';
                int j = i + 1;
                while (j < s.length() && Character.isDigit(s.charAt(j))) {
                    if (num == 0) {
                        ok = false;
                        break out;
                    }
                    num = 10 * num + s.charAt(j) - '0';
                    j++;
                }
                i = j - 1;
                if (num < 0 || num > 255) {
                    ok = false;
                    break;
                }
                if (dotCnt == 0) {
                    num1 = num;
                } else if (dotCnt == 1) {
                    num2 = num;
                } else if (dotCnt == 2) {
                    num3 = num;
                } else if (dotCnt == 3) {
                    num4 = num;
                }
            } else {
                if (i == s.length() - 1 || i == 0 || c != '.' || !Character.isDigit(s.charAt(i - 1)) || !Character.isDigit(s.charAt(i + 1))) {
                    ok = false;
                    break;
                }
                dotCnt++;
                if (dotCnt > 3) {
                    ok = false;
                    break;
                }
            }
        }
        if (dotCnt != 3) {
            ok = false;
        }
        // 判断A、B、C、D、E、非法、私网 七种情况
        if (ok) {
            if (checkMask) {
                long maskNum = (num1 << 24) + (num2 << 16) + (num3 << 8) + num4;
                String maskStr = Long.toBinaryString(maskNum);
                maskValid = maskStr.contains("1") && maskStr.contains("0") && !maskStr.contains("01");
                if (!maskValid) {
                    maskValid = false;
                    ipFlags[5]++;
                }
            } else {
                if (num1 >= 1 && num1 <= 126) {
                    ipFlags[0]++; // A
                } else if (num1 >= 128 && num1 <= 191) {
                    ipFlags[1]++; // B
                } else if (num1 >= 192 && num1 <= 223) {
                    ipFlags[2]++; // C
                } else if (num1 >= 224 && num1 <= 239) {
                    ipFlags[3]++; // D
                } else if (num1 >= 240 && num1 <= 255) {
                    ipFlags[4]++; // E
                }
                // 私有ip
                if (num1 == 10 || (num1 == 172 && num2 >= 16 && num2 <= 31) || (num1 == 192 && num2 == 168)) {
                    ipFlags[6]++;
                }
            }
        } else {
            ipFlags[5]++;
        }
        return checkMask ? maskValid : ok;
    }

    public void HJ24(int[] arr) {
        int n = arr.length;
        int[] left = new int[n]; //存储每个数左边小于其的数的个数
        int[] right = new int[n];//存储每个数右边小于其的数的个数
        left[0] = 1;            //最左边的数设为1
        right[n - 1] = 1;        //最右边的数设为1
        //计算每个位置左侧的最长递增
        for (int i = 0; i < n; i++) {
            left[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {   //动态规划
                    left[i] = Math.max(left[j] + 1, left[i]);
                }
            }
        }
        //计算每个位置右侧的最长递减
        for (int i = n - 1; i >= 0; i--) {
            right[i] = 1;
            for (int j = n - 1; j > i; j--) {
                if (arr[i] > arr[j]) {   //动态规划
                    right[i] = Math.max(right[i], right[j] + 1);
                }
            }
        }
        // 记录每个位置的值
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            //位置 i计算了两次 所以需要－1
            result[i] = left[i] + right[i] - 1; //两个都包含本身
        }

        //找到最大的满足要求的值
        int max = 1;
        for (int i = 0; i < n; i++) {
            max = Math.max(result[i], max);
        }
        System.out.println(n - max);
    }


    public static void HJ60() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int even = scanner.nextInt();
            int diff = Integer.MAX_VALUE;
            int minI = 0;
            for (int i = even / 2; i >= 2; i--) {
                int remain = even - i;
                if (isPrime(i) && isPrime(remain)) {
                    System.out.println(i);
                    System.out.println(remain);
                    break;
                }
            }
//            System.out.println(minI);
//            System.out.println(even - minI);
        }
    }

    public static boolean isPrime(int num) {
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void HJ56() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            int cnt = 0;
            for (int i = 6; i < n; i++) {
                int sum = 0;
                // check
                for (int j = 1; j < i; j++) {
                    if (i % j == 0) {
                        sum += j;
                    }
                    if (sum > i) {
                        break;
                    }
                }
                if (sum == i) {
                    cnt++;
                }
            }
            System.out.println(cnt);
        }
    }

    public static void HJ54() {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        char[] chars = s.toCharArray();
        Stack<String> stack = new Stack<>();
        StringBuilder numBuilder = new StringBuilder();
        // ((99+51)*14+26)*24      (7+5*4*3+6)    5-3+9*6*(6-10-2)
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (Character.isDigit(c)) {
                numBuilder.append(c);
                if (i == chars.length - 1) {
                    stack.push(numBuilder.toString());
                }
            } else {
                if (numBuilder.length() > 0) {
                    stack.push(numBuilder.toString());
                }
                stack.push(String.valueOf(c));
                if (c == ')') {
                    stack.pop(); // pop )
                    String cal = calculate(stack);
//                    stack.pop(); // pop (
                    stack.push(cal);
                }
                numBuilder = new StringBuilder();
            }
        }
        System.out.println("ori=" + stack);
        if (stack.size() > 1) {
            String cal = calculate(stack);
            stack.push(cal);
        }
        System.out.println("after cal=" + stack);
        String result = stack.pop();
        System.out.println(result);
    }

    public static String calculate(Stack<String> stack) {
        if (stack.size() < 3) {
            return stack.pop();
        }
        Stack<String> temp = new Stack<>();
        while (!stack.empty()) {
            String s = stack.pop();
            if (isPriority(s)) {
                int a = Integer.parseInt(temp.pop());
                int b = Integer.parseInt(stack.pop());
                int value = calculate(a, b, s);
                temp.push(String.valueOf(value));
            } else if ("(".equals(s)) {
                break;
            } else {
                temp.push(s);
            }
        }
        System.out.println("temp=" + temp);
        while (temp.size() >= 3) {
            int a = Integer.parseInt(temp.pop());
            String s = temp.pop();
            int b = Integer.parseInt(temp.pop());
            int value = calculate(a, b, s);
            temp.push(String.valueOf(value));
        }
        return temp.pop();
    }

    public static int calculate(int a, int b, String operator) {
        int ret = 0;
        if ("+".equals(operator)) {
            ret = a + b;
        } else if ("-".equals(operator)) {
            ret = a - b;
        } else if ("*".equals(operator)) {
            ret = a * b;
        } else if ("/".equals(operator)) {
            ret = a / b;
        }
        return ret;
    }

    public static boolean isPriority(String s) {
        return "*".equals(s) || "/".equals(s);
    }

    public static boolean isNumeric(final String cs) {
        if (cs == null || cs.length() <= 0) {
            return false;
        }
        final int sz = cs.length();
        for (int i = 0; i < sz; i++) {
            if (!Character.isDigit(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static void HJ53() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int num = scanner.nextInt();
            if (num == 1 || num == 2) {
                System.out.println(-1);
                continue;
            } else if (num % 4 == 1 || num % 4 == 3) {
                System.out.println(2);
                continue;
            } else if (num % 4 == 0) {
                System.out.println(3);
                continue;
            } else if (num % 4 == 2) {
                System.out.println(4);
                continue;
            }
        }
    }

    public static void HJ51() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            ListNode[] array = new ListNode[n];
            for (int i = 0; i < n; i++) {
                ListNode node = new ListNode(scanner.nextInt());
                array[i] = node;
                if (i > 0) {
                    array[i - 1].next = node;
                }
            }
            int k = scanner.nextInt();
            if (k <= 0) {
                System.out.println(0);
            } else {
                ListNode node = array[n - k];
                System.out.println(node.val);
            }
        }
    }

}
