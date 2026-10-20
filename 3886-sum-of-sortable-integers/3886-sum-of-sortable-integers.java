class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int answer = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) {
                continue;
            }

            boolean sortable = true;
            int previousMax = -1;

            for (int start = 0; start < n; start += k) {
                int min = nums[start];
                int max = nums[start];
                int descents = 0;

                for (int j = start; j < start + k; j++) {
                    if (nums[j] < min) {
                        min = nums[j];
                    }

                    if (nums[j] > max) {
                        max = nums[j];
                    }

                    int next = j + 1;

                    if (next == start + k) {
                        next = start;
                    }

                    if (nums[j] > nums[next]) {
                        descents++;
                    }
                }

                if (descents > 1) {
                    sortable = false;
                    break;
                }

                if (previousMax != -1 && previousMax > min) {
                    sortable = false;
                    break;
                }

                previousMax = max;
            }

            if (sortable) {
                answer += k;
            }
        }

        return answer;
    }
}