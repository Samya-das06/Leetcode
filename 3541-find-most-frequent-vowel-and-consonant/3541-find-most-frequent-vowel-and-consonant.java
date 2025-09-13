class Solution {
    public int maxFreqSum(String s) {
        int[] freq = new int[26];
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        int maxVowelFreq = 0;
        int maxConsonantFreq = 0;
        for (int i = 0; i < 26; i++) {
            char ch = (char) (i + 'a');
            if (vowels.contains(ch)) {
                maxVowelFreq = Math.max(maxVowelFreq, freq[i]);
            } else {
                maxConsonantFreq = Math.max(maxConsonantFreq, freq[i]);
            }
        }
        return maxVowelFreq + maxConsonantFreq;
    }
}