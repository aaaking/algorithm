package prefixSum;

import java.util.Arrays;

/*
* 930. 和相同的二元子数组
给你一个二元数组 nums ，和一个整数 goal ，请你统计并返回有多少个和为 goal 的 非空 子数组。

子数组 是数组的一段连续部分。

示例 1：

输入：nums = [1,0,1,0,1], goal = 2
输出：4
解释：
有 4 个满足题目要求的子数组：[1,0,1]、[1,0,1,0]、[0,1,0,1]、[1,0,1]
示例 2：

输入：nums = [0,0,0,0,0], goal = 0
输出：15
 

提示：

1 <= nums.length <= 3 * 104
nums[i] 不是 0 就是 1
0 <= goal <= nums.length


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/binary-subarrays-with-sum
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
public class NumSubarraysWithSum {
    public static void main(String[] args) {
        NumSubarraysWithSum demo = new NumSubarraysWithSum();
        int[] a1 = new int[]{1, 0, 1, 0, 1};
        int[] a2 = new int[]{0, 0, 0, 0, 0};
        int[] a3 = new int[]{0, 1, 1, 1, 1};
        System.out.println(demo.numSubarraysWithSum(a1, 2)); // 4
        System.out.println(demo.numSubarraysWithSum(a2, 0)); // 15
        System.out.println(demo.numSubarraysWithSum(a3, 3)); // 3
    }

    public int numSubarraysWithSum(int[] nums, int goal) {
        System.out.println("-------------");
        int len = nums.length;
        int[] preSum = new int[len + 1];
        for (int i = 0; i < len; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        System.out.println(Arrays.toString(preSum));
        int hitTimes = 0;
        int sum = preSum[len];
        for (int i = 0; i < len; i++) {
            if (nums[i] == goal) {
                hitTimes++;
            }
            if (i > 0 && preSum[i] == goal) {
                hitTimes++;
            }
            if (i > 0 && preSum[i] + nums[i] == goal) {
                hitTimes++;
            }
            if (preSum[i + 1] - nums[i] == goal) {
                hitTimes++;
            }
//            if (sum - preSum[i] == goal) {
//                hitTimes++;
//            }
        }

        return hitTimes;
    }
}
