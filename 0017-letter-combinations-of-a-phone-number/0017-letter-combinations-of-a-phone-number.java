public class Solution {
    private String[] mapping = {
        "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) return res;
        backtrack(res, digits, 0, new StringBuilder());
        return res;
    }
    private void backtrack(List<String> res, String digits, int index, StringBuilder sb) {
        if (index == digits.length()) {
            res.add(sb.toString());
            return;
        }
        String letters = mapping[digits.charAt(index) - '0'];
        for (char c : letters.toCharArray()) {
            sb.append(c);
            backtrack(res, digits, index + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}