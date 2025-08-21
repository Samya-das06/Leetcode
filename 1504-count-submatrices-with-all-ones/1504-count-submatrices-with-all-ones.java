public class Solution {
    public int numSubmat(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int res = 0;
        int[] height = new int[n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                height[j] = mat[i][j] == 0 ? 0 : height[j] + 1;
            }
            for (int j = 0; j < n; ++j) {
                if (height[j] == 0) continue;
                int minH = height[j];
                for (int k = j; k >= 0 && height[k] > 0; --k) {
                    minH = Math.min(minH, height[k]);
                    res += minH;
                }
            }
        }
        return res;
    }
}