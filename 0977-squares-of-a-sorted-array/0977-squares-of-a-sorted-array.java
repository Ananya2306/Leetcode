class Solution {
    public int[] sortedSquares(int[] nums) {
        int[] res = new int[nums.length];
        int l =0;
        int r = nums.length-1;
        for(int i = nums.length-1;i>=0;i--){
            int ls = nums[l]*nums[l];
            int rs = nums[r]*nums[r];

            if(ls>rs){
                res[i] = ls;
                l++;
            }else{
                res[i] = rs;
                r--;
            }
        }
        return res;
    }
}