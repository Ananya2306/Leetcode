class Solution {
    public int minKBitFlips(int[] nums, int k) {

        int n = nums.length;

        int flip = 0;
        int count = 0;

        for (int i = 0; i < n; i++) {

            if (i >= k && nums[i - k] == 2)
                flip ^= 1;

            if ((nums[i] ^ flip) == 0) {

                if (i + k > n)
                    return -1;

                nums[i] = 2;
                flip ^= 1;
                count++;
            }
        }

        return count;
    }
}