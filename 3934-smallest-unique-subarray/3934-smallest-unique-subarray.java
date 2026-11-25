import java.util.*;

class Solution {
    static final long MOD1 = 1000000007L;
    static final long MOD2 = 1000000009L;
    static final long BASE = 100001L;

    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        long[] prefix1 = new long[n + 1];
        long[] prefix2 = new long[n + 1];
        long[] power1 = new long[n + 1];
        long[] power2 = new long[n + 1];
        power1[0] = 1;
        power2[0] = 1;

        for (int i = 0; i < n; i++) {
            prefix1[i + 1] = (prefix1[i] * BASE + nums[i]) % MOD1;
            prefix2[i + 1] = (prefix2[i] * BASE + nums[i]) % MOD2;
            power1[i + 1] = (power1[i] * BASE) % MOD1;
            power2[i + 1] = (power2[i] * BASE) % MOD2;
        }

        int low = 1;
        int high = n;
        int ans = n;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (isUnique(nums, mid, prefix1, prefix2, power1, power2)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private boolean isUnique(int[] nums, int len,long[] prefix1, long[] prefix2,long[] power1, long[] power2) {
        HashMap<Long, Integer> map = new HashMap<>();
        int n = nums.length;
        for (int i = 0; i + len <= n; i++) {
            int j = i + len;
            long h1 = (prefix1[j] - prefix1[i] * power1[len] % MOD1 + MOD1) % MOD1;
            long h2 = (prefix2[j] - prefix2[i] * power2[len] % MOD2 + MOD2) % MOD2;
            long key = h1 * MOD2 + h2;
            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        for (int count : map.values()) {
            if (count == 1) {
                return true;
            }
        }
        return false;
    }
}