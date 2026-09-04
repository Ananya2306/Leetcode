class Solution {
    public boolean possibleToStamp(int[][] grid, int stampHeight, int stampWidth) {

        int m = grid.length;
        int n = grid[0].length;

        int[][] prefix = new int[m + 1][n + 1];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                prefix[i + 1][j + 1] =
                        grid[i][j]
                        + prefix[i][j + 1]
                        + prefix[i + 1][j]
                        - prefix[i][j];
            }
        }

        int[][] diff = new int[m + 1][n + 1];

        for (int i = 0; i + stampHeight <= m; i++) {
            for (int j = 0; j + stampWidth <= n; j++) {

                int bottom = i + stampHeight;
                int right = j + stampWidth;

                int sum = prefix[bottom][right]
                        - prefix[i][right]
                        - prefix[bottom][j]
                        + prefix[i][j];

                if (sum == 0) {

                    diff[i][j]++;

                    diff[bottom][j]--;
                    diff[i][right]--;
                    diff[bottom][right]++;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (i > 0) {
                    diff[i][j] += diff[i - 1][j];
                }

                if (j > 0) {
                    diff[i][j] += diff[i][j - 1];
                }

                if (i > 0 && j > 0) {
                    diff[i][j] -= diff[i - 1][j - 1];
                }

                if (grid[i][j] == 0 && diff[i][j] <= 0) {
                    return false;
                }
            }
        }

        return true;
    }
}