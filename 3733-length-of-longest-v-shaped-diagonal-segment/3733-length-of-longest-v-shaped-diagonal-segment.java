class Solution {
    private static final int[][] DIRS = { { -1, 1 }, { 1, 1 }, { 1, -1 }, { -1, -1 } };

    public int lenOfVDiagonal(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Integer[][][][][] memo = new Integer[m][n][2][2][4];
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 1)
                    continue;
                for (int d = 0; d < 4; d++) {
                    int ni = i + DIRS[d][0], nj = j + DIRS[d][1];
                    ans = Math.max(ans, 1 + dfs(grid, ni, nj, false, 2, d, memo));
                }
            }
        }
        return ans;
    }

    private int dfs(int[][] grid, int i, int j, boolean turned,
            int num, int dir, Integer[][][][][] memo) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length)
            return 0;
        if (grid[i][j] != num)
            return 0;
        int t = turned ? 1 : 0;
        int hashNum = Math.max(0, num - 1);
        if (memo[i][j][t][hashNum][dir] != null)
            return memo[i][j][t][hashNum][dir];

        int nextNum = (num == 2) ? 0 : 2;
        int res = 1 + dfs(grid, i + DIRS[dir][0], j + DIRS[dir][1],
                turned, nextNum, dir, memo);

        if (!turned) {
            int nextDir = (dir + 1) % 4;
            res = Math.max(res, 1 + dfs(grid, i + DIRS[nextDir][0],
                    j + DIRS[nextDir][1],
                    true, nextNum, nextDir, memo));
        }

        return memo[i][j][t][hashNum][dir] = res;
    }
}