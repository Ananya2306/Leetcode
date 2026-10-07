class Solution {
    public void moveZeroes(int[] nums) {
        /** 
        int[] temp = new int[nums.length];
        int k = 0;

        for (int num : nums) {
            if (num != 0) {
                temp[k++] = num;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            nums[i] = temp[i];
        }*/
        int  ind =0;
        for(int i = 0;i<nums.length;i++){
            if(nums[i]!=0){
                nums[ind]= nums[i];
                ind++;
            }
        }
        while(ind<nums.length){
            nums[ind]=0;
            ind++;
        }
    }
}