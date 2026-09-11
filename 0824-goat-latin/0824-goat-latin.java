class Solution {
    public String toGoatLatin(String sentence) {

        String result = "";
        String w = "";
        int wordIndex = 1;

        for (int i = 0; i <= sentence.length(); i++) {

            if (i == sentence.length() || sentence.charAt(i) == ' ') {
                char first = w.charAt(0);

                if (first == 'a' || first == 'e' || first == 'i' || first == 'o' ||   first == 'u' || first == 'A' || first == 'E' || first == 'I' || first == 'O' || first == 'U') {

                    w = w + "ma";

                } else {

                    w = w.substring(1) + first + "ma";
                }

                for (int j = 0; j < wordIndex; j++) {
                    w = w + "a";
                }

                if (result.length() > 0) {
                    result = result + " ";
                }

                result = result + w;

                w = "";
                wordIndex++;
            }

            else {
                w = w + sentence.charAt(i);
            }
        }

        return result;
    }
}