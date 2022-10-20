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

    // 523. 连续的子数组和是k的倍数最小长度=2 https://leetcode-cn.com/problems/continuous-subarray-sum/
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int preSum = 0;
        for (int i = 0; i < nums.length; i++) {
            preSum += nums[i];
            int key = k == 0 ? preSum : preSum % k;
            if (map.containsKey(key)) {
                if (i - map.get(key) >= 2) {
                    return true;
                }
                continue;
            }
            map.put(key, i);
        }
        return false;
    }
    
    // 1893. 检查是否区域内所有整数都被覆盖 https://leetcode.cn/problems/check-if-all-the-integers-in-a-range-are-covered/solution/yi-ti-san-jie-bao-li-you-hua-chai-fen-by-w7xv/
    public boolean isCovered(int[][] ranges, int left, int right) {
        int[] diff = new int[52];   // 差分数组
        for (int[] range : ranges) {
            ++diff[range[0]];
            --diff[range[1] + 1];
        }
        // 前缀和
        int curr = 0;
        for (int i = 1; i <= 50; ++i) {
            curr += diff[i];
            if (i >= left && i <= right && curr <= 0) {
                return false;
            }
        }
        return true;
    }

    // 1310. 子数组异或查询	https://leetcode-cn.com/problems/xor-queries-of-a-subarray/
    public int[] xorQueries(int[] arr, int[][] queries) {
        int n = arr.length;
        int[] xors = new int[n + 1];
        for (int i = 0; i < n; i++) {
            xors[i + 1] = xors[i] ^ arr[i];
        }
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            ans[i] = xors[queries[i][0]] ^ xors[queries[i][1] + 1];
        }
        return ans;
    }

    // 1738. 找出第 K 大的异或坐标值	https://leetcode-cn.com/problems/find-kth-largest-xor-coordinate-value/
    public int kthLargestValue(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int[][] pre = new int[m + 1][n + 1];
        List<Integer> results = new ArrayList<Integer>();
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                pre[i][j] = pre[i - 1][j] ^ pre[i][j - 1] ^ pre[i - 1][j - 1] ^ matrix[i - 1][j - 1];
                results.add(pre[i][j]);
            }
        }

        Collections.sort(results, new Comparator<Integer>() {
            public int compare(Integer num1, Integer num2) {
                return num2 - num1;
            }
        });
        return results.get(k - 1);
    }

    // 1177. 构建回文串检测	https://leetcode-cn.com/problems/can-make-palindrome-from-substring/
    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        int[] sum = new int[s.length() + 1];
        for (int i = 1; i < sum.length; i++) {
            int index = s.charAt(i - 1) - 'a';
            sum[i] = sum[i - 1] ^ (1 << index);
        }

        List<Boolean> ans = new ArrayList<>();
        for (int i = 0; i < queries.length; i++) {
            ans.add(Integer.bitCount(sum[queries[i][1] + 1] ^ sum[queries[i][0]]) <= (1 + (queries[i][2] << 1)));
        }
        return ans;
    }
}
