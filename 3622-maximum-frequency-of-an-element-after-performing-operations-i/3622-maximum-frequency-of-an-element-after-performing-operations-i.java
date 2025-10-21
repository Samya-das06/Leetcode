class Solution {
    public int maxFrequency(int[] nums, int k, int numOperations) {
        int max = Arrays.stream(nums).max().getAsInt();
        int n = max + k + 2;
        int[] freq = new int[n];
        for (int num : nums) {
            freq[num]++;
        }
        int[] pre = new int[n];
        pre[0] = freq[0];
        for (int i = 1; i < n; i++) {
            pre[i] = pre[i - 1] + freq[i];
        }
        int result = 0; 
        for (int i = 0; i < n; i++) {
            if (freq[i] == 0 && numOperations == 0) {
                continue;
            }
            int l = Math.max(0, i - k);
            int r = Math.min(n - 1, i + k);
            int tot = pre[r] - (l > 0 ? pre[l - 1] : 0);
            int adj = tot - freq[i];
            int val = freq[i] + Math.min(numOperations, adj);
            result = Math.max(result, val);
        }
        return result;
    }
}