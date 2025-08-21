public class Solution {
    public int numSubmat(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int res = 0;
        int[][] h = new int[m][n];
        for(int i = 0; i < m; ++i){
            for(int j = 0; j < n; ++j){
                h[i][j] = mat[i][j] == 0 ? 0 : (i == 0 ? 1 : h[i-1][j] + 1);
            }
        }
        for(int i = 0; i < m; ++i){
            for(int j = 0; j < n; ++j){
                int min = h[i][j];
                for(int k = j; k >= 0 && min > 0; --k){
                    min = Math.min(min, h[i][k]);
                    res += min;
                }
            }
        }
        return res;
    }
    public static void main(String[] args) {
        Solution sol = new Solution();

        int[][] mat1 = {
            {1,0,1},
            {1,1,0},
            {1,1,0}
        };

        int[][] mat2 = {
            {0,1,1,0},
            {0,1,1,1},
            {1,1,1,0}
        };

        System.out.println(sol.numSubmat(mat1));
        System.out.println(sol.numSubmat(mat2));
    }
}