import java.util.*;
class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> dq = new ArrayDeque<>();
        long cost = 0;
        long ans = 0;
        int right = n - 1;
        for (int left = n - 1; left >= 0; left--) {
            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[left]) {
                int l = dq.pollLast();
                int r = dq.isEmpty() ? right + 1 : dq.peekLast();
                cost += (long) (r - l) * (nums[left] - nums[l]);
            }

            dq.offerLast(left);
            while (cost > k) {
                cost -= nums[dq.peekFirst()] - nums[right];
                if (dq.peekFirst() == right) {
                    dq.pollFirst();
                }
                right--;
            }
            ans += right - left + 1;
        }
        return ans;
    }
}