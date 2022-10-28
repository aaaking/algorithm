package doublePoint;

import datastructor.ListNode;

import java.util.*;

// https://mp.weixin.qq.com/s?__biz=Mzg3Mzc0NjUzMQ==&mid=2247497066&idx=1&sn=1b62c9b5305576a06208b1a2202c9ea7&source=41#wechat_redirect
public class Solution {

    // 27. 移除元素 https://leetcode-cn.com/problems/remove-element/
    public int removeElement(int[] nums, int val) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[j++] = nums[i];
            }
        }
        return j;
    }

    // 26. 删除有序数组中的重复项 https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/
    // 使每个元素 只出现一次
    public int removeDuplicatesIndividual(int[] nums) {
        return removeDuplicatesCommon(nums, 1);
    }

    // 80. 删除有序数组中的重复项 II https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii/
    // 使每个元素 最多出现两次
    public int removeDuplicates(int[] nums) {
        return removeDuplicatesCommon(nums, 2);
    }

    private int removeDuplicatesCommon(int[] nums, int k) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (j < k) {
                j++;
            } else {
                if (nums[i] != nums[j - k]) {
                    nums[j] = nums[i];
                    j++;
                }
            }
        }
        return j;
    }

    // 160. 相交链表 https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode tmpA = headA;
        ListNode tmpB = headB;
        while (tmpA != tmpB) {
            tmpA = tmpA == null ? headB : tmpA.next;
            tmpB = tmpB == null ? headA : tmpB.next;
        }
        return tmpB; //or tmpA
    }

    // 209. 长度最小的子数组 https://leetcode-cn.com/problems/minimum-size-subarray-sum/
    // 给定一个含有 n 个正整数的数组和一个正整数 target 。找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
    public int minSubArrayLen(int target, int[] nums) {
        int l = 0, r = 0;
        int minL = Integer.MAX_VALUE;
        int sums = 0;
        int n = nums.length;
        for (r = 0; r < n; r++) {
            sums += nums[r];
            while (sums >= target) {
                minL = Math.min(r - l + 1, minL);
                sums -= nums[l];
                l++;
            }
        }
        return minL == Integer.MAX_VALUE ? 0 : minL;
    }

    // 3. 无重复字符的最长子串 https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
    public int lengthOfLongestSubstring(String s) {
        int[] ascii = new int[256];
        int l = 0, r = 0;
        int max = 0, temp = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i > 0) {
                ascii[s.charAt(i - 1)] = 0;
            }
            while (r < s.length()) {
                char c = s.charAt(r);
                if (ascii[c] > 0) {
                    break;
                } else {
                    ascii[c] = 1;
                    r++;
                }
            }
            max = Math.max(max, r - i);
        }
        return max;
    }

    // 1208. 尽可能使字符串相等 https://leetcode-cn.com/problems/get-equal-substrings-within-budget/
    public int equalSubstring(String s, String t, int maxCost) {
        int left = 0, right = 0;
        int max = 0;
        int sum = 0;
        while (right < s.length()) {
            sum += Math.abs(s.charAt(right) - t.charAt(right));
            right++;
            while (sum > maxCost) {
                sum -= Math.abs(s.charAt(left) - t.charAt(left));
                left++;
            }
            max = Math.max(max, right - left);
        }
        return max;
    }

    // 424. 替换后的最长重复字符 https://leetcode-cn.com/problems/longest-repeating-character-replacement/
    public int characterReplacement(String s, int k) { // todo 看不懂
        int[] map = new int[26];
        int left = 0, right = 0;
        int max = 0;
        while (right < s.length()) {
            int index = s.charAt(right) - 'A';
            map[index]++;
            max = Math.max(max, map[index]);
            if (right - left + 1 - max > k) {
                map[s.charAt(left) - 'A']--;
                left++;
            }
            right++;
        }
        return right - left;
    }

    // 239. 滑动窗口最大值 https://leetcode-cn.com/problems/sliding-window-maximum/
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length - k + 1;
        int[] ret = new int[n];
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < k; i++) {
            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) {
                queue.pollLast();
            }
            queue.addLast(i);
        }
        int index = 0;
        ret[index++] = nums[queue.peekFirst()];

        for (int right = k; right < nums.length; right++) {
            if (queue.peekFirst() == right - k) {
                queue.pollFirst();
            }

            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[right]) {
                queue.pollLast();
            }
            queue.addLast(right);
            ret[index++] = nums[queue.peekFirst()];
        }
        return ret;
    }

    // 15. 三数之和 https://leetcode-cn.com/problems/3sum/
    // 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
    // 注意：答案中不可以包含重复的三元组。
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        // 枚举 a
        for (int first = 0; first < n; ++first) {
            // 需要和上一次枚举的数不相同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            // c 对应的指针初始指向数组的最右端
            int third = n - 1;
            int target = -nums[first];
            // 枚举 b
            for (int second = first + 1; second < n - 1; ++second) {
                // 需要和上一次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // 需要保证 b 的指针在 c 的指针的左侧
                while (second < third && nums[second] + nums[third] > target) {
                    --third;
                }
                // 如果指针重合，随着 b 后续的增加
                // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
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

    // 18. 四数之和 https://leetcode-cn.com/problems/4sum/
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> quadruplets = new ArrayList<List<Integer>>();
        if (nums == null || nums.length < 4) {
            return quadruplets;
        }
        Arrays.sort(nums);
        int length = nums.length;
        for (int i = 0; i < length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            if ((long) nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                break;
            }
            if ((long) nums[i] + nums[length - 3] + nums[length - 2] + nums[length - 1] < target) {
                continue;
            }
            for (int j = i + 1; j < length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                if ((long) nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
                    break;
                }
                if ((long) nums[i] + nums[j] + nums[length - 2] + nums[length - 1] < target) {
                    continue;
                }
                int left = j + 1, right = length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        quadruplets.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        left++;
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return quadruplets;
    }

    // 1456. 定长子串中元音的最大数目 https://leetcode-cn.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/
    public int maxVowels(String s, int k) {
        int max = 0;
        int sum = 0;
        int left = 0, right = 0;
        while (right < s.length()) {
            sum += isVowel(s.charAt(right));
            right++;
            if (right >= k) {
                max = Math.max(max, sum);
                sum -= isVowel(s.charAt(right - k));
            }
        }
        return max;
    }

    private int isVowel(char s) {
        return s == 'a' || s == 'e' || s == 'i' || s == 'o' || s == 'u' ? 1 : 0;
    }

    // 11. 盛最多水的容器  https://leetcode.cn/problems/container-with-most-water/
    public int maxArea(int[] height) {
        int i = 0, j = height.length - 1, res = 0;
        while (i < j) {
            res = height[i] < height[j] ?
                    Math.max(res, (j - i) * height[i++]) :
                    Math.max(res, (j - i) * height[j--]);
        }
        return res;
    }

    // 88. 合并两个有序数组  https://leetcode.cn/problems/merge-sorted-array/?favorite=ex0k24j
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p = m + n - 1;
        int p1 = m - 1;
        int p2 = n - 1;
        for (; p >= 0; p--) {
            int a = p1 < 0 ? Integer.MIN_VALUE : nums1[p1];
            int b = p2 < 0 ? Integer.MIN_VALUE : nums2[p2];
            if (a >= b && p1 >= 0) {
                nums1[p] = a;
                p1--;
            } else if (a < b) {
                nums1[p] = b;
                p2--;
            }
        }
    }
}
