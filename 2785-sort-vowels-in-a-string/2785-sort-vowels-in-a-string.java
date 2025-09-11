class Solution {
    public String sortVowels(String s) {
        Set<Character> vowels = new HashSet<>(Arrays.asList(
            'a','e','i','o','u','A','E','I','O','U'
        ));
        
        List<Character> vowelList = new ArrayList<>();
        char[] arr = s.toCharArray();
        
        for (char c : arr) {
            if (vowels.contains(c)) {
                vowelList.add(c);
            }
        }
        
        vowelList.sort(Comparator.naturalOrder());
        
        int idx = 0;
        for (int i = 0; i < arr.length; i++) {
            if (vowels.contains(arr[i])) {
                arr[i] = vowelList.get(idx++);
            }
        }
        
        return new String(arr);
    }
}