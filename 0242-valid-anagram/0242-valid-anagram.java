/*class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] ss = s.toCharArray();
        char[] tt = t.toCharArray();
        
        for (int i = 0; i < ss.length - 1; i++) {
            for (int j = 0; j < ss.length - i - 1; j++) {
                if (ss[j] > ss[j + 1]) {
                    char temp = ss[j];
                    ss[j] = ss[j + 1];
                    ss[j + 1] = temp;
                }
            }
        } 
        for (int i = 0; i < tt.length - 1; i++) {
            for (int j = 0; j < tt.length - i - 1; j++) {
                if (tt[j] > tt[j + 1]) {
                    char temp = tt[j];
                    tt[j] = tt[j + 1];
                    tt[j + 1] = temp;
                }
            }
        }
        for (int i = 0; i < ss.length; i++) {
            if (ss[i] != tt[i]) {
                return false; 
            }
        }
        return true; 
    }
}
*/
import java.util.Arrays;

class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        
        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();
        
        Arrays.sort(sArray);
        Arrays.sort(tArray);
        
        return Arrays.equals(sArray, tArray);
    }
}
