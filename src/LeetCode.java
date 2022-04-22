import java.util.Arrays;

public class LeetCode {
    public static void main(String[] args) {
        System.out.println("a".indexOf(""));
        LeetCode leetCode = new LeetCode();
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

}
