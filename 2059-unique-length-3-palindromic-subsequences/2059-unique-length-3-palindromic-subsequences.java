class Solution {
    public int countPalindromicSubsequence(String s) {
        int n = s.length();
        char[] chArr = s.toCharArray();

        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        Arrays.fill(last, -1);

        for(int i = 0; i < n; i++){ 
            char ch = chArr[i];   
            if(first[ch - 'a'] == -1){
                first[ch - 'a'] = i;
            }
        }
        
        for(int i = n-1; i >= 0; i--){
            char ch = chArr[i];   
            if(last[ch - 'a'] == -1){
                last[ch - 'a'] = i;
            }
        }
        
        int res = 0;

        for(int i = 0; i < 26; i++){
            if(first[i] != -1){
                int f = first[i];
                int l = last[i];
                if(l-f > 1){
                    HashSet<Character> set = new HashSet<>();
                    for(int j = f+1; j < l; j++){
                        set.add(chArr[j]);
                    }
                    res += set.size();
                } 
            }
        }

        return res;
    }
}