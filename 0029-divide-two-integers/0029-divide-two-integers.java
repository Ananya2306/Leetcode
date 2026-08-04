class Solution {
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) 
            return Integer.MAX_VALUE;
        boolean neg = (dividend < 0) ^ (divisor < 0);
        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);
        long ans = 0;
        for (int i = 31; i >= 0; i--) {
            if ((b << i) <= a) { 
                a -= (b << i); 
                ans |= (1L << i); 
                }
        }
        return (int) (neg ? -ans : ans);
    }
}
