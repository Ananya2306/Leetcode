
import java.util.*;

class Solution {
    
    public int uniquePaths(int[][] grid) {
        
        int m = grid.length;
        int n = grid[0].length;
        
        int mod = 1000000007;
        
        int[][] right = new int[m][n];
        int[][] down = new int[m][n];
        
        // Starting position
        right[0][0] = 1;
        
        for (int i = 0; i < m; i++) {
            
            for (int j = 0; j < n; j++) {
                
                int r = right[i][j];
                int d = down[i][j];
                
                if (grid[i][j] == 0) {
                    
                    // Empty cell: robot can move right or down
                    int ways = (int)(((long)r + d) % mod);
                    
                    if (j + 1 < n) {
                        right[i][j + 1] = (int)(
                            ((long)right[i][j + 1] + ways) % mod
                        );
                    }
                    
                    if (i + 1 < m) {
                        down[i + 1][j] = (int)(
                            ((long)down[i + 1][j] + ways) % mod
                        );
                    }
                    
                } else {
                    
                    // Mirror cell:
                    // Entering from right direction -> down
                    if (i + 1 < m) {
                        down[i + 1][j] = (int)(
                            ((long)down[i + 1][j] + r) % mod
                        );
                    }
                    
                    // Entering from down direction -> right
                    if (j + 1 < n) {
                        right[i][j + 1] = (int)(
                            ((long)right[i][j + 1] + d) % mod
                        );
                    }
                }
            }
        }
        
        return (int)(
            ((long)right[m - 1][n - 1] +
             down[m - 1][n - 1]) % mod
        );
    }
}
