class Solution {
    public int peopleAwareOfSecret(int n, int delay, int forget) {
        int MOD = 1_000_000_007;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int shareable = 0;
        for (int day = 2; day <= n; day++) {
            if (day - delay >= 1) {
                shareable = (shareable + dp[day - delay]) % MOD;
            }
            if (day - forget >= 1) {
                shareable = (shareable - dp[day - forget] + MOD) % MOD;
            }
            dp[day] = shareable;
        }
        int result = 0;
        for (int day = n - forget + 1; day <= n; day++) {
            if (day >= 1) {
                result = (result + dp[day]) % MOD;
            }
        }
        return result;
    }
}