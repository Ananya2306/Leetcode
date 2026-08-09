class Solution {
    public int minimumDistance(int[][] points) {
        int n = points.length;

        int maxSum1 = Integer.MIN_VALUE;
        int maxSum2 = Integer.MIN_VALUE;
        int minSum1 = Integer.MAX_VALUE;
        int minSum2 = Integer.MAX_VALUE;

        int maxDiff1 = Integer.MIN_VALUE;
        int maxDiff2 = Integer.MIN_VALUE;
        int minDiff1 = Integer.MAX_VALUE;
        int minDiff2 = Integer.MAX_VALUE;

        int maxSumIdx = -1;
        int minSumIdx = -1;
        int maxDiffIdx = -1;
        int minDiffIdx = -1;

        for (int i = 0; i < n; i++) {
            int sum = points[i][0] + points[i][1];
            int diff = points[i][0] - points[i][1];

            if (sum > maxSum1) {
                maxSum2 = maxSum1;
                maxSum1 = sum;
                maxSumIdx = i;
            } else if (sum > maxSum2) {
                maxSum2 = sum;
            }

            if (sum < minSum1) {
                minSum2 = minSum1;
                minSum1 = sum;
                minSumIdx = i;
            } else if (sum < minSum2) {
                minSum2 = sum;
            }

            if (diff > maxDiff1) {
                maxDiff2 = maxDiff1;
                maxDiff1 = diff;
                maxDiffIdx = i;
            } else if (diff > maxDiff2) {
                maxDiff2 = diff;
            }

            if (diff < minDiff1) {
                minDiff2 = minDiff1;
                minDiff1 = diff;
                minDiffIdx = i;
            } else if (diff < minDiff2) {
                minDiff2 = diff;
            }
        }

        int answer = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {

            int maxSum;
            int minSum;
            int maxDiff;
            int minDiff;

            if (i == maxSumIdx) {
                maxSum = maxSum2;
            } else {
                maxSum = maxSum1;
            }

            if (i == minSumIdx) {
                minSum = minSum2;
            } else {
                minSum = minSum1;
            }

            if (i == maxDiffIdx) {
                maxDiff = maxDiff2;
            } else {
                maxDiff = maxDiff1;
            }

            if (i == minDiffIdx) {
                minDiff = minDiff2;
            } else {
                minDiff = minDiff1;
            }

            int distance1 = maxSum - minSum;
            int distance2 = maxDiff - minDiff;

            int current = Math.max(distance1, distance2);

            answer = Math.min(answer, current);
        }

        return answer;
    }
}