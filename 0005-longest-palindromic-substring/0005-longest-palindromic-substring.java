class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        char[] t = preprocess(s);
        int[] p = new int[t.length];
        int center = 0, right = 0;
        int maxLen = 0, centerIndex = 0;

        for (int i = 1; i < t.length - 1; i++) {
            int mirror = 2 * center - i;
            if (right > i)
                p[i] = Math.min(right - i, p[mirror]);

            while (t[i + 1 + p[i]] == t[i - 1 - p[i]])
                p[i]++;

            if (i + p[i] > right) {
                center = i;
                right = i + p[i];
            }

            if (p[i] > maxLen) {
                maxLen = p[i];
                centerIndex = i;
            }
        }

        int start = (centerIndex - maxLen) / 2;
        return s.substring(start, start + maxLen);
    }

    private char[] preprocess(String s) {
        char[] ret = new char[s.length() * 2 + 3];
        ret[0] = '^';
        for (int i = 0; i < s.length(); i++) {
            ret[2 * i + 1] = '#';
            ret[2 * i + 2] = s.charAt(i);
        }
        ret[ret.length - 2] = '#';
        ret[ret.length - 1] = '$';
        return ret;
    }
}