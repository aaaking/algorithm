import datastructor.ListNode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
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

    public static void HJ46(String s, int k) {
        System.out.println(s.substring(0, k));
    }

    public static void HJ40(String s) {
        int alphaCnt = 0;
        int spaceCnt = 0;
        int digitCnt = 0;
        int otherCnt = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == ' ') {
                spaceCnt++;
            } else if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                alphaCnt++;
            } else if (Character.isDigit(c)) {
                digitCnt++;
            } else {
                otherCnt++;
            }
        }
        System.out.println(alphaCnt);
        System.out.println(spaceCnt);
        System.out.println(digitCnt);
        System.out.println(otherCnt);
    }

}
