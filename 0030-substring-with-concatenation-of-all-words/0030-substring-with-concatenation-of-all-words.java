class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || words == null || words.length == 0) return result;
        int wordLen = words[0].length();
        int numWords = words.length;
        int totalLen = wordLen * numWords;
        if (s.length() < totalLen) return result;
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }
        for (int i = 0; i < wordLen; i++) {
            int left = i, right = i;
            Map<String, Integer> windowCount = new HashMap<>();
            while (right + wordLen <= s.length()) {
                String word = s.substring(right, right + wordLen);
                right += wordLen;
                if (wordCount.containsKey(word)) {
                    windowCount.put(word, windowCount.getOrDefault(word, 0) + 1);
                    while (windowCount.get(word) > wordCount.get(word)) {
                        String leftWord = s.substring(left, left + wordLen);
                        windowCount.put(leftWord, windowCount.get(leftWord) - 1);
                        left += wordLen;
                    }
                    if (right - left == totalLen) {
                        result.add(left);
                    }
                } else {
                    windowCount.clear();
                    left = right;
                }
            }
        }
        return result;
    }
}