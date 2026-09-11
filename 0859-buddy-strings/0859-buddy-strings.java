class Solution {
    public boolean buddyStrings(String s, String goal) {

        if (s.length() != goal.length()) {
            return false;
        }

        int first = -1;
        int second = -1;
        int mis = 0;

        boolean[] seen = new boolean[26];
        boolean duplicate = false;

        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);

            if (seen[c - 'a']) {
                duplicate = true;
            }

            seen[c - 'a'] = true;

            if (s.charAt(i) != goal.charAt(i)) {

                mis++;

                if (mis == 1) {
                    first = i;
                } 
                else if (mis == 2) {
                    second = i;
                }
                else {
                    return false;
                }
            }
        }

        if (mis == 0) {
            return duplicate;
        }

        if (mis != 2) {
            return false;
        }

        if (s.charAt(first) == goal.charAt(second) &&
            s.charAt(second) == goal.charAt(first)) {

            return true;
        }

        return false;
    }
}