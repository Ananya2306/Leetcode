
import java.util.*;

class Solution {
    static final long MOD = 1000000007L;

    int n;
    long[] sum;
    long[] square;
    long[] lazy;

    public int sumCounts(int[] nums) {
        n = nums.length;

        sum = new long[4 * n];
        square = new long[4 * n];
        lazy = new long[4 * n];

        int[] last = new int[100001];
        Arrays.fill(last, -1);

        long ans = 0;

        for (int i = 0; i < n; i++) {
            int value = nums[i];
            int prev = last[value];

            update(1, 0, n - 1, prev + 1, i, 1);

            ans = (ans + square[1]) % MOD;

            last[value] = i;
        }

        return (int) ans;
    }

    void update(int node, int start, int end,
                int left, int right, long value) {
        if (left > end || right < start) {
            return;
        }

        if (left <= start && end <= right) {
            apply(node, start, end, value);
            return;
        }

        push(node, start, end);

        int mid = start + (end - start) / 2;

        update(node * 2, start, mid, left, right, value);
        update(node * 2 + 1, mid + 1, end, left, right, value);

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        square[node] = (square[node * 2] + square[node * 2 + 1]) % MOD;
    }

    void apply(int node, int start, int end, long value) {
        long len = end - start + 1L;

        square[node] = (
            square[node] + 2L * value % MOD * sum[node] % MOD
            + value * value % MOD * len % MOD
        ) % MOD;

        sum[node] = (sum[node] + value * len) % MOD;
        lazy[node] = (lazy[node] + value) % MOD;
    }

    void push(int node, int start, int end) {
        if (lazy[node] == 0 || start == end) {
            return;
        }

        int mid = start + (end - start) / 2;

        apply(node * 2, start, mid, lazy[node]);
        apply(node * 2 + 1, mid + 1, end, lazy[node]);

        lazy[node] = 0;
    }
}
