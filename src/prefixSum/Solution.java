package prefixSum;

import java.util.*;

// * https://mp.weixin.qq.com/s/r-6Yfp2qn_x8nKJMVirdyQ

public class Solution {

    // 724. 寻找数组的中心下标 https://leetcode-cn.com/problems/find-pivot-index/
    public int pivotIndex(int[] nums) {
        int length = nums.length;
        int[] preSum = new int[length + 1];
        for (int i = 0; i < length; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        int totalSum = preSum[length];
        for (int i = 0; i < length; i++) {
            if (preSum[i] * 2 + nums[i] == totalSum) {
                return i;
            }
        }
        return -1;
    }

    // 560. 和为 K 的子数组 https://leetcode-cn.com/problems/subarray-sum-equals-k/
    public int subarraySum(int[] nums, int k) {
        int cnt = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int presum = 0;
        for (int x : nums) {
            presum += x;
            cnt += map.getOrDefault(presum - k, 0);
            map.put(presum, map.getOrDefault(presum, 0) + 1);
        }
        return cnt;
    }

    // 930. 和相同的二元子数组 https://leetcode-cn.com/problems/binary-subarrays-with-sum/
    public int numSubarraysWithSum(int[] nums, int goal) {
        int cnt = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int preSum = 0;
        for (int x : nums) {
            preSum += x;
            cnt += map.getOrDefault(preSum - goal, 0);
            map.put(preSum, map.getOrDefault(preSum, 0) + 1);
        }
        return cnt;
    }

    // 1248. 统计「优美子数组」 https://leetcode-cn.com/problems/count-number-of-nice-subarrays/
    public int numberOfSubarrays(int[] nums, int k) {
        int cnt = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int preSum = 0;
        for (int x : nums) {
            if (x % 2 != 0) {
                preSum++;
            }
            cnt += map.getOrDefault(preSum - k, 0);
            map.put(preSum, map.getOrDefault(preSum, 0) + 1);
        }
        return cnt;
    }

    // 974. 和可被 K 整除的子数组 https://leetcode-cn.com/problems/subarray-sums-divisible-by-k/
    public int subarraysDivByK(int[] nums, int k) {
        int cnt = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int preSum = 0;
        for (int x : nums) {
            preSum += x;
            int key = (preSum % k + k) % k;
            cnt += map.getOrDefault(key, 0);
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        return cnt;
    }

    // 523. 连续的子数组和 https://leetcode-cn.com/problems/continuous-subarray-sum/
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int preSum = 0;
        for (int i = 0; i < nums.length; i++) {
            preSum += nums[i];
            int key = k == 0 ? preSum : preSum % k;
            if (map.containsKey(key) && i - map.get(key) >= 2) {
                return true;
            }
            map.put(key, i);
        }
        return false;
    }
}
