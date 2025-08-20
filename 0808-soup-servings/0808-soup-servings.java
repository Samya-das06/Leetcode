class Solution {
    Double[][] memo;

    public double soupServings(int n) {
        if (n > 4800) return 1.0;
        memo = new Double[n + 1][n + 1];
        return dfs(n, n);
    }

    private double dfs(int a, int b) {
        if (a <= 0 && b <= 0) return 0.5;
        if (a <= 0) return 1.0;
        if (b <= 0) return 0.0;
        if (memo[a][b] != null) return memo[a][b];
        double res = 0.25 * (dfs(Math.max(0, a - 100), b) +
                             dfs(Math.max(0, a - 75), Math.max(0, b - 25)) +
                             dfs(Math.max(0, a - 50), Math.max(0, b - 50)) +
                             dfs(Math.max(0, a - 25), Math.max(0, b - 75)));
        memo[a][b] = res;
        return res;
    }
}