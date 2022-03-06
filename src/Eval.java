import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class Eval {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<Character, Integer> maps = new HashMap<>();
        maps.put('+', 1);
        maps.put('-', 1);
        maps.put('*', 2);
        maps.put('/', 2);
        maps.put('%', 2);
        maps.put('^', 3);
        String s = scanner.nextLine()
                .replaceAll(" ", "")
                .replaceAll("\\(-", "\\(0-")
                .replaceAll("\\(+", "\\(0+");
        if (s.charAt(0) == '-') { // 特判首字符是负数
            s = '0' + s;
        }
        char[] chars = s.toCharArray();
        int n = chars.length;
        Stack<Integer> nums = new Stack<>();
        Stack<Character> ops = new Stack<>();
        // ((99+51)*14+26)*24      (7+5*4*3+6)    5-3+9*6*(6-10-2)  3*5+8-0*3-6+0+0  -1*(-1-1)
        for (int i = 0; i < n; i++) {
            char c = chars[i];
            if (c == '(') {
                ops.push(c);
            } else if (c == ')') {
                while (!ops.isEmpty()) {
                    if (ops.peek() == '(') {
                        ops.pop();
                        break;
                    } else {
                        calc(nums, ops);
                    }
                }
            } else if (Character.isDigit(c)) {
                int j = i;
                int num = 0;
                while (j < n && Character.isDigit(chars[j])) {
                    num = num * 10 + (chars[j] - '0');
                    j++;
                }
                nums.push(num);
                i=j-1;
            } else {
                // + - * / ^
                while (!ops.isEmpty() && ops.peek() != '(') {
                    if (maps.get(ops.peek()) >= maps.get(c)) {
                        calc(nums, ops);
                    } else {
                        break;
                    }
                }
                ops.push(c);
            }
        }
        while (!ops.isEmpty() && ops.peek() != '(') {
            calc(nums, ops);
        }
        System.out.println(nums.peek());
    }

    public static void calc(Stack<Integer> nums, Stack<Character> ops) {
        int a = nums.pop();
        int b = nums.pop();
        Character op = ops.pop();
        int value = 0;
        if (op == '+') {
            value = b + a;
        } else if (op == '-') {
            value = b - a;
        } else if (op == '*') {
            value = b * a;
        } else if (op == '/') {
            value = b / a;
        } else if (op == '^') {
            value = (int) Math.pow(b, a);
        }
        nums.push(value);
    }
}
