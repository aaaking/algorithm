import java.util.*;
import java.io.*;

public class Main2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 优先级maps
        HashMap<Character, Integer> maps = new HashMap<>();
        maps.put('+', 1);
        maps.put('-', 1);
        maps.put('*', 2);
        maps.put('/', 2);
        maps.put('%', 2);
        maps.put('^', 3);
        String str = "";
        while ((str = br.readLine()) != null) {
            // 双栈法
            // 数据栈
            Deque<Integer> nums = new LinkedList<>();
            nums.push(0);
            // 操作栈
            Deque<Character> ops = new LinkedList<>();
            // 预处理
            str = str.replaceAll(" ", "");
            str = str.replaceAll("\\(-", "\\(0-");
            str = str.replaceAll("\\(+", "\\(0+");
            if (str.charAt(0) == '-') { // 特判首字符是负数
                str = '0' + str;
            }
            // 转换成字符数组
            char[] chars = str.toCharArray();
            int n = str.length();
            for (int i = 0; i < n; i++) {
                if (chars[i] == '(') {
                    ops.push(chars[i]);
                } else if (chars[i] == ')') {
                    while (!ops.isEmpty()) {
                        if (ops.peek() == '(') {
                            ops.pop();
                            break;
                        } else {
                            calc(nums, ops);
                        }
                    }

                } else {
                    if (isNumber(chars[i])) {
                        // 当前遇到的是数字
                        int j = 0;
                        int k = i;
                        while (k < n && isNumber(chars[k])) {
                            j = j * 10 + (chars[k] - '0');
                            k++;
                        }
                        nums.push(j);
                        i = k - 1;
                    } else {
                        // 当前遇到的是加减乘除
                        while (!ops.isEmpty() && ops.peek() != '(') {
                            if (maps.get(ops.peek()) >= maps.get(chars[i])) {
                                calc(nums, ops);
                            } else {
                                break;
                            }
                        }
                        ops.push(chars[i]);
                    }
                }
            }
            while (!ops.isEmpty() && ops.peek() != '(') {
                calc(nums, ops);
            }
            System.out.println(nums.peek());
        }
    }

    // 判断是否是数字
    public static boolean isNumber(char cur) {
        return Character.isDigit(cur);
    }

    //  进行计算
    public static void calc(Deque<Integer> nums, Deque<Character> ops) {
        if (ops.isEmpty()) return;
        if (nums.isEmpty() || nums.size() < 2) return;
        int b = nums.pop();
        int a = nums.pop();
        int op = ops.pop();
        int res = 0;
        if (op == '+') {
            res = a + b;
        } else if (op == '-') {
            res = a - b;
        } else if (op == '*') {
            res = a * b;
        } else if (op == '/') {
            res = a / b;
        } else if (op == '^') {
            res = (int) Math.pow(a, b);
        } else if (op == '%') {
            res = a % b;
        }
        nums.push(res);
    }
}