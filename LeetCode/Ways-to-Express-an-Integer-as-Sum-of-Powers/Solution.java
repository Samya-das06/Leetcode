class Solution {
    public int numberOfWays(int n, int x) {
        final int mod = 1_000_000_007;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int a = 1; (int)Math.pow(a, x) <= n; ++a) {
            int v = (int)Math.pow(a, x);
            for (int i = n; i >= v; --i) {
                dp[i] = (dp[i] + dp[i - v]) % mod;
            }
        }
        return dp[n];
    }
}