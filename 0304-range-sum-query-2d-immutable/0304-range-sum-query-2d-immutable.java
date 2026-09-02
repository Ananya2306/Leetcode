class NumMatrix {
    private int[][] m;

    public NumMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        m = new int[rows + 1][cols + 1];
        
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                m[r + 1][c + 1] = matrix[r][c] + m[r][c + 1] + m[r + 1][c] - m[r][c];
            }
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return m[row2 + 1][col2 + 1]- m[row1][col2 + 1] - m[row2 + 1][col1] + m[row1][col1];
    }
}
