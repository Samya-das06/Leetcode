class Solution {
    public String largestGoodInteger(String num) {
        String res = "";
        for (int i = 0; i <= num.length() - 3; i++) {
            if (num.charAt(i) == num.charAt(i+1) && num.charAt(i) == num.charAt(i+2)) {
                String candidate = num.substring(i, i + 3);
                if (candidate.compareTo(res) > 0) res = candidate;
            }
        }
        return res;
    }
}