package prefixSum;

/*
* 560. 和为 K 的子数组
给你一个整数数组 nums 和一个整数 k ，请你统计并返回该数组中和为 k 的连续子数组的个数。



示例 1：

输入：nums = [1,1,1], k = 2
输出：2
示例 2：

输入：nums = [1,2,3], k = 3
输出：2


提示：

1 <= nums.length <= 2 * 104
-1000 <= nums[i] <= 1000
-107 <= k <= 107
* 提示：

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/subarray-sum-equals-k
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

import java.util.Arrays;

public class SubArraySum {
    public static void main(String[] args) {
        SubArraySum demo = new SubArraySum();
        int[] a1 = new int[]{1, 1, 1};
        int[] a2 = new int[]{1, 2, 3};
        System.out.println(demo.subarraySum(a1, 2)); // 2
        System.out.println(demo.subarraySum(a2, 3)); // 2
    }

    public int subarraySum(int[] nums, int k) {
        System.out.println("-------------");
        int length = nums.length;
        int[] preSum = new int[length + 1];
        for (int i = 0; i < length; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }

        System.out.println(Arrays.toString(preSum));
        int hitTimes = 0;
        for (int i = 0; i < length + 1; i++) {
            for (int j = i+1; j < length + 1; j++) {
                if (preSum[j] - preSum[i] == k) {
                    hitTimes++;
                }
            }
        }
        return hitTimes;
    }
}
