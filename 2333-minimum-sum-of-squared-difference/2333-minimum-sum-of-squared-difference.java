class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long operations = (long) k1 + k2;
        int[] diff = new int[n];

        int max = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);

            if (diff[i] > max) {
                max = diff[i];
            }
        }

        long total = 0;

        for (int i = 0; i < n; i++) {
            total += diff[i];
        }

        if (total <= operations) {
            return 0;
        }

        int low = 0;
        int high = max;

        while (low < high) {
            int mid = low + (high - low) / 2;
            long required = 0;

            for (int i = 0; i < n; i++) {
                if (diff[i] > mid) {
                    required += diff[i] - mid;
                }
            }

            if (required <= operations) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        int limit = low;
        long ans = 0;
        long remaining = operations;

        for (int i = 0; i < n; i++) {
            if (diff[i] > limit) {
                remaining -= diff[i] - limit;
                diff[i] = limit;
            }
        }

        for (int i = 0; i < n && remaining > 0; i++) {
            if (diff[i] == limit && limit > 0) {
                diff[i]--;
                remaining--;
            }
        }

        for (int i = 0; i < n; i++) {
            ans += (long) diff[i] * diff[i];
        }

        return ans;
    }
}