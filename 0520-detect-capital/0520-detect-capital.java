class Solution {
    public boolean detectCapitalUse(String word) {

        int uppercase = 0;

        for (int i = 0; i < word.length(); i++) {

            char ch = word.charAt(i);

            if (ch >= 'A' && ch <= 'Z') {
                uppercase++;
            }
        }

        if (uppercase == 0) {
            return true;
        }

        if (uppercase == word.length()) {
            return true;
        }

        if (uppercase == 1 &&
            word.charAt(0) >= 'A' &&
            word.charAt(0) <= 'Z') {

            return true;
        }

        return false;
    }
}