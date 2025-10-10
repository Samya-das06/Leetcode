class Solution {
    public int maximumEnergy(int[] energy, int k) {
        int n = energy.length;
        int[] dp = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = energy[i];
            if (i + k < n) {
                dp[i] += dp[i + k];
            }
        }

        int maxEnergy = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            maxEnergy = Math.max(maxEnergy, dp[i]);
        }
        return maxEnergy;
    }
}