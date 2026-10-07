class Solution {
    public int[] replaceElements(int[] arr) {
        int m =-1;
        for(int i = arr.length-1;i>=0;i--){
            int c = arr[i];
            arr[i]=m;
            if(c>m){
                m=c;
            }
        }
        return arr;
    }
}