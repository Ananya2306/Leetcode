
import java.util.*;

class Solution {

    class MedianWindow {

        TreeMap<Integer, Integer> left;
        TreeMap<Integer, Integer> right;

        int sizeLeft;
        int sizeRight;

        long sumLeft;
        long sumRight;

        MedianWindow() {
            left = new TreeMap<>();
            right = new TreeMap<>();

            sizeLeft = 0;
            sizeRight = 0;

            sumLeft = 0;
            sumRight = 0;
        }

        void add(int val) {

            if (left.isEmpty() || val <= left.lastKey()) {
                left.put(val, left.getOrDefault(val, 0) + 1);
                sizeLeft++;
                sumLeft += val;
            } else {
                right.put(val, right.getOrDefault(val, 0) + 1);
                sizeRight++;
                sumRight += val;
            }

            balance();
        }

        void remove(int val) {

            if (left.containsKey(val)) {

                int count = left.get(val);

                if (count == 1) {
                    left.remove(val);
                } else {
                    left.put(val, count - 1);
                }

                sizeLeft--;
                sumLeft -= val;

            } else {

                int count = right.get(val);

                if (count == 1) {
                    right.remove(val);
                } else {
                    right.put(val, count - 1);
                }

                sizeRight--;
                sumRight -= val;
            }

            balance();
        }

        void balance() {

            // Left should contain the median
            while (sizeLeft > sizeRight + 1) {

                int val = left.lastKey();

                removeFromLeft(val);
                addToRight(val);
            }

            while (sizeLeft < sizeRight) {

                int val = right.firstKey();

                removeFromRight(val);
                addToLeft(val);
            }
        }

        void removeFromLeft(int val) {

            int count = left.get(val);

            if (count == 1) {
                left.remove(val);
            } else {
                left.put(val, count - 1);
            }

            sizeLeft--;
            sumLeft -= val;
        }

        void addToLeft(int val) {

            left.put(val, left.getOrDefault(val, 0) + 1);

            sizeLeft++;
            sumLeft += val;
        }

        void removeFromRight(int val) {

            int count = right.get(val);

            if (count == 1) {
                right.remove(val);
            } else {
                right.put(val, count - 1);
            }

            sizeRight--;
            sumRight -= val;
        }

        void addToRight(int val) {

            right.put(val, right.getOrDefault(val, 0) + 1);

            sizeRight++;
            sumRight += val;
        }

        long getCost() {

            long median = left.lastKey();

            long costLeft = median * sizeLeft - sumLeft;
            long costRight = sumRight - median * sizeRight;

            return costLeft + costRight;
        }
    }

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;

        // Step 1: Calculate every window's cost

        long[] cost = new long[n - x + 1];

        MedianWindow window = new MedianWindow();

        for (int i = 0; i < x; i++) {
            window.add(nums[i]);
        }

        cost[0] = window.getCost();

        for (int i = x; i < n; i++) {

            window.remove(nums[i - x]);
            window.add(nums[i]);

            cost[i - x + 1] = window.getCost();
        }

        // Step 2: DP

        long INF = Long.MAX_VALUE / 4;

        long[][] dp = new long[k + 1][n + 1];

        for (int j = 0; j <= k; j++) {
            for (int i = 0; i <= n; i++) {
                dp[j][i] = INF;
            }
        }

        // Zero subarrays require zero operations
        for (int i = 0; i <= n; i++) {
            dp[0][i] = 0;
        }

        for (int j = 1; j <= k; j++) {

            for (int i = 1; i <= n; i++) {

                // Option 1: Skip current element
                dp[j][i] = dp[j][i - 1];

                // Option 2: Select a window of size x
                if (i >= x) {

                    long previous = dp[j - 1][i - x];

                    if (previous != INF) {

                        long candidate = previous + cost[i - x];

                        if (candidate < dp[j][i]) {
                            dp[j][i] = candidate;
                        }
                    }
                }
            }
        }

        return dp[k][n];
    }
}
