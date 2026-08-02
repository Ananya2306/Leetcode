class Solution {
    public int minChanges(int n, int k) {
        int ans = 0;

        while (n > 0 || k > 0) {
            int b1 = n & 1;
            int b2 = k & 1;

            if (b1 == 0 && b2 == 1) {
                return -1;
            }

            if (b1 == 1 && b2 == 0) {
                ans++;
            }

            n >>= 1;
            k >>= 1;
        }

        return ans;
    }
}