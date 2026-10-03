class Solution {
    public long maxSumTrionic(int[] nums) {
        int n = nums.length;

        long NEG = Long.MIN_VALUE / 4;

        long inc = NEG;       
        long incDec = NEG;    
        long incDecInc = NEG; 

        long ans = NEG;

        for (int i = 1; i < n; i++) {

            long newInc = NEG;
            long newIncDec = NEG;
            long newIncDecInc = NEG;

            if (nums[i] > nums[i - 1]) {

                newInc = (long) nums[i - 1] + nums[i];

                if (inc != NEG) {
                    newInc = Math.max(newInc, inc + nums[i]);
                }

                if (incDec != NEG) {
                    newIncDecInc = incDec + nums[i];
                }

                if (incDecInc != NEG) {
                    newIncDecInc = Math.max(
                        newIncDecInc,
                        incDecInc + nums[i]
                    );
                }

            } else if (nums[i] < nums[i - 1]) {

                if (inc != NEG) {
                    newIncDec = inc + nums[i];
                }

                if (incDec != NEG) {
                    newIncDec = Math.max(
                        newIncDec,
                        incDec + nums[i]
                    );
                }

            }

            inc = newInc;
            incDec = newIncDec;
            incDecInc = newIncDecInc;

            if (incDecInc != NEG) {
                ans = Math.max(ans, incDecInc);
            }
        }

        return ans;
    }
}