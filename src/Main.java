import datastructor.ListNode;

import java.util.*;
机试题： https://blog.csdn.net/Guesshat/article/details/122284160?utm_medium=distribute.pc_relevant.none-task-blog-2~default~baidujs_baidulandingword~default-0.queryctrv2&spm=1001.2101.3001.4242.1&utm_relevant_index=3
public class Main {
    public static void main(String[] args) {
        HJ61();
    }

    public static void HJ61() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int apples = scanner.nextInt();
            int plates = scanner.nextInt();
            System.out.println(place(apples, plates));
        }

    }

    public static int place(int apples, int plates) {
        if (apples < 0 || plates <= 0)
            return 0;
        if (apples <= 1 || plates <= 1) {
            return 1;
        }
        if (apples == 2) {
            return 2;
        }
        return place(apples - plates, plates) + place(apples, plates - 1);
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

    public static int HJ37(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 1;
        }
        if (n == 3) {
            return 2;
        }
        int pre = HJ37(n - 1);
        int pre2 = HJ37(n - 2);
        return pre2 + pre;
    }

    public static void encrypt(String s) {
        StringBuilder sb = new StringBuilder();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            sb.append(getOneChar(chars[i]));
        }
        System.out.println(sb);
    }

    public static String getOneChar(char c) {
        // low case
        if (c >= 'a' && c <= 'c') {
            return "2";
        } else if (c >= 'd' && c <= 'f') {
            return "3";
        } else if (c >= 'g' && c <= 'i') {
            return "4";
        } else if (c >= 'j' && c <= 'l') {
            return "5";
        } else if (c >= 'm' && c <= 'o') {
            return "6";
        } else if (c >= 'p' && c <= 's') {
            return "7";
        } else if (c >= 't' && c <= 'v') {
            return "8";
        } else if (c >= 'w' && c <= 'z') {
            return "9";
        } else if (c >= 'A' && c <= 'Z') {
            c += 33;
            if (c > 'z') {
                c = 'a';
            }
        }
        return Character.toString(c);
    }
}
