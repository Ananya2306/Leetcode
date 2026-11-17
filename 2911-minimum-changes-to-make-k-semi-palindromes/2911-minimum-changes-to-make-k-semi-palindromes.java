import java.util.*;
class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        int INF = 1_000_000_000;
        List<Integer>[] divisors = new ArrayList[n + 1];
        for (int len = 2; len <= n; len++) {
            divisors[len] = new ArrayList<>();
            for (int d = 1; d < len; d++) {
                if (len % d == 0) {
                    divisors[len].add(d);
                }
            }
        }

        int[][] cost = new int[n][n];
        for (int[] row : cost) {
            Arrays.fill(row, INF);
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                cost[i][j] = getCost(s, i, j, divisors[j - i + 1]);
            }
        }

        int[][] dp = new int[n + 1][k + 1];
        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }
        dp[0][0] = 0;

        for (int p = 1; p <= k; p++) {
            for (int i = 2 * p; i <= n; i++) {
                for (int prev = 2 * (p - 1); prev <= i - 2; prev++) {
                    if (dp[prev][p - 1] != INF) {
                        dp[i][p] = Math.min(dp[i][p], dp[prev][p - 1] + cost[prev][i - 1]);
                    }
                }
            }
        }
        return dp[n][k];
    }

    private int getCost(String s, int i, int j, List<Integer> divs) {
        int minChanges = Integer.MAX_VALUE;
        for (int d : divs) {
            int changes = 0;
            for (int rem = 0; rem < d; rem++) {
                int left = i + rem;
                int right = j - d + 1 + rem;
                while (left < right) {
                    if (s.charAt(left) != s.charAt(right)) {
                        changes++;
                    }
                    left += d;
                    right -= d;
                }
            }
            minChanges = Math.min(minChanges, changes);
        }
        return minChanges;
    }
}