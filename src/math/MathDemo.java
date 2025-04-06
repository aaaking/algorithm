package math;

import java.util.ArrayList;
import java.util.List;

public class MathDemo {
    public static void main(String[] args) {
        testEgyptFraction(2, 15);
        testEgyptFraction(5, 6);
        testEgyptFraction(8, 11);

        testEgyptFraction(17, 43);
        testEgyptFraction(4, 83);
    }

    public static List<Integer> testEgyptFraction(int a, int b) {
        List<Integer> ret = new ArrayList<>();
        while (a > 1) {
            int p = b / a;
            if (b % a == 0) {
                b = p;
                a = 1;
                break;
            } else {
                ret.add(p + 1);
                a = a + a * p - b;
                b = b * (p + 1);
            }
//            int gcd = gcd(a, b);
//            if (gcd > 1) {
//                a = a / gcd;
//                b = b / gcd;
//            }
        }
        ret.add(b);
        System.out.println(ret);
        return ret;
    }

    public static int gcd(int a, int b) {
        if (a < b) {
            int temp = a;
            a = b;
            b = temp;
        }
        if (a < 0 || b < 0) {
            return -1;
        }
        if (b == 0) {
            return a;
        }
        while (a % b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return b;
    }
}
