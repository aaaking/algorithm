import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode {
    public static void main(String[] args) {
        System.out.println("a".indexOf(""));
        LeetCode leetCode = new LeetCode();
        System.out.println(Arrays.toString(leetCode.getChars('2')));
        ;
        System.out.println(Arrays.toString(leetCode.getChars('4')));
        ;
        System.out.println(Arrays.toString(leetCode.getChars('9')));
        ;
    }

    // 16. 最接近的三数之和 https://leetcode-cn.com/problems/3sum-closest
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int optimal = nums[0] + nums[1] + nums[2];
        int n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            int b = i + 1;
            int c = n - 1;
            while (b < c) {
                int temp = nums[i] + nums[b] + nums[c];
                if (Math.abs(optimal - target) > Math.abs(temp - target)) {
                    optimal = temp;
                }
                if (temp > target) {
                    c--;
                } else if (temp < target) {
                    b++;
                } else {
                    return temp;
                }
            }
        }
        return optimal;
    }

    // 17. 电话号码的字母组合 https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits.length() <= 0) {
            return ans;
        }
        dfs17(digits, new StringBuilder(), ans, 0);
        return ans;
    }

    private void dfs17(String digits, StringBuilder path, List<String> ans, int start) {
        if (path.length() == digits.length()) {
            ans.add(String.valueOf(path));
        } else {
            char[] chars = getChars(digits.charAt(start));
            for (int i = 0; i < chars.length; i++) {
                path.append(chars[i]);
                dfs17(digits, path, ans, start + 1);
                path.deleteCharAt(path.length() - 1);
            }
        }
    }

    private char[] getChars(char c) {
        if (c == '2') {
            return new char[]{'a', 'b', 'c'};
        } else if (c == '3') {
            return new char[]{'d', 'e', 'f'};
        } else if (c == '4') {
            return new char[]{'g', 'h', 'i'};
        } else if (c == '5') {
            return new char[]{'j', 'k', 'l'};
        } else if (c == '6') {
            return new char[]{'m', 'n', 'o'};
        } else if (c == '7') {
            return new char[]{'p', 'q', 'r', 's'};
        } else if (c == '8') {
            return new char[]{'t', 'u', 'v'};
        } else if (c == '9') {
            return new char[]{'w', 'x', 'y', 'z'};
        }
        return null;
    }

}
