import java.util.HashMap;
import java.util.Map;

public class Arr {

    public static int subarraysWithKDistinct(int[] A, int K) {
        /*
        * 这道题采用双滑动窗口，但这里巧妙的隐藏了一个滑动窗口。
下面这个文章的解释比较清楚。
https://www.cnblogs.com/godweiyang/p/12203881.html

现在考虑右边界为 j 的情况，左边界 i 有什么规律呢？ 我们可以证明，满足 [i, j] 正好包含 K 个不同整数的 i 的取值是一段连续的区间。 假设 [i, j]包含 K 个不同整数，同时 [i', j] 也包含 K 个不同整数（i < i'），因为从 i 移动到 i' 每个数的数量是非增的，所以这过程中没有增加新的数，也没有任何一个数的数量降到了0。

有了这个性质之后，对于任意的 j ，我们只需要求出左边界 i 的取值范围就行了。同样这里还是不能暴力求，不然就和一开始没区别了嘛。 既然这样，想想如果 j 的左边界 i 的范围得到了，这时候我们继续求 j + 1 的左边界范围，能不能利用一下之前得到的结果？而不用重新计算。 很容易发现，如果 j 右移了， i 的取值范围也会右移，因为 j 右移有两种结果：一是引入了新的数，二是某个存在的数的数量加 1 。 第一种情况对左边界没有任何影响，因为不同整数数量没有变化，还是 K 。第二种情况不同整数数量变成 K + 1 了，这时候左边界一定要右移，删掉点数，才可能使区间符合题意。

有了上述的性质之后就好做了，因为左边界的取值范围也是不断右移的，所以我们只需要维护两个指针 left 和 right 就行了，一个保存取值范围的最小值，一个保存最大值。然后每次对于一个 j ，符合题意的子区间数量就是 right - left + 1 。而 j 右移一个数之后， left 需要右移，直到 [left, j] 中正好有 K 个不同整数， right 也继续右移，直到[right + 1, j] 中正好有 K - 1 个不同整数。

因为 left 和 right 最多只会移动 n 次，而 j 也只移动了 n 次，所以总体时间复杂度降到了 O(n) 。
        * */
        int len;
        if (K == 0 || A == null || (len = A.length) == 0) {
            return 0;
        }
        int[] cacheArr = new int[len + 1];
        int count = 0;
        int prefix = 0;
        int result = 0;
        for (int left = 0, right = 0; right < len; ++right) {
            // 存储右指针的值到缓存数组中，并加1，如果值等于1，表示第一次存储该值，则将计数 +1.
            if (++cacheArr[A[right]] == 1) {
                ++count;
            }

            // 如果计数大于 K，前缀变为 0，去掉缓存数组中的最左边的一个元素，并将计数 -1.
            if (count > K) {
                prefix = 0;
                --cacheArr[A[left++]];
                --count;
            }

            // 循环处理缓存数组中，最左边的重复元素，去掉多少个，前缀数就加几个，便于后面累加结果.
            while (cacheArr[A[left]] > 1) {
                --cacheArr[A[left++]];
                ++prefix;
            }

            // 如果计数等于 K，说明满足条件，将 prefix数 和本身满足条件的数（1）累加到 result 结果中，
            if (count == K) {
                result += prefix + 1;
            }
        }

        return result;
    }

    /*
    * https://leetcode-cn.com/problems/subarrays-with-k-different-integers/
    * 至多包含 K 个不同字符的最长子串
    * 转化为至多有K个不同整数的子数组的个数 - 至多有K-1个不同整数的子数组
    * 的个数，那么用滑动窗口即解: sum(A[i:j])\leq Ksum(A[i:j])≤K，则 ans += j - i + 1。时间：O(n)O(n), 空间：O(1)O(1)
    * */
    private static int atMostK(int[] A, int K) {
        int i = 0, res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int j = 0; j < A.length; ++j) {
            if (map.getOrDefault(A[j], 0) == 0)
                --K;
            map.put(A[j], map.getOrDefault(A[j], 0) + 1);
            while (K < 0) {
                map.put(A[i], map.get(A[i]) - 1);
                if (map.get(A[i]) == 0)
                    ++K;
                ++i;
            }
            System.out.println(map);
            res += j - i + 1;
        }
        return res;
    }

    public static int subarraysWithKDistinct2(int[] A, int K) {
        return atMostK(A, K) - atMostK(A, K - 1);
    }

    public static void main(String[] args) {//subarraysWithKDistinct
        System.out.println(atMostK(new int[]{1, 2, 1, 2, 3}, 2));
        String fs = "";
    }
}
