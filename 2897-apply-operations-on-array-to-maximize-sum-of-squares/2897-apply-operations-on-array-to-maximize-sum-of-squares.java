import java.util.*;

class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int n = nums.size();
        int[] bits = new int[31];
        long mod = 1000000007L;

        for (int i = 0; i < n; i++) {
            int num = nums.get(i);

            for (int b = 0; b < 31; b++) {
                if ((num & (1 << b)) != 0) {
                    bits[b]++;
                }
            }
        }

        long ans = 0;

        for (int i = 0; i < k; i++) {
            long num = 0;

            for (int b = 0; b < 31; b++) {
                if (bits[b] > 0) {
                    num |= (1L << b);
                    bits[b]--;
                }
            }

            ans = (ans + num * num) % mod;
        }

        return (int) ans;
    }
}