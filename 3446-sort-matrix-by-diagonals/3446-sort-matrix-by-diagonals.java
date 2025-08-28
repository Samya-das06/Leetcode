public class Solution {
    public int[][] sortMatrix(int[][] grid) {
        int n = grid.length;

        for (int row = n - 1; row >= 0; row--) {
            List<Integer> diagonal = new ArrayList<>();
            int r = row, c = 0;
            while (r < n && c < n) {
                diagonal.add(grid[r][c]);
                r++;
                c++;
            }
            diagonal.sort(Collections.reverseOrder());

            r = row; c = 0;
            int idx = 0;
            while (r < n && c < n) {
                grid[r][c] = diagonal.get(idx++);
                r++;
                c++;
            }
        }

        for (int col = 1; col < n; col++) {
            List<Integer> diagonal = new ArrayList<>();
            int r = 0, c = col;
            while (r < n && c < n) {
                diagonal.add(grid[r][c]);
                r++;
                c++;
            }
            Collections.sort(diagonal);

            r = 0; c = col;
            int idx = 0;
            while (r < n && c < n) {
                grid[r][c] = diagonal.get(idx++);
                r++;
                c++;
            }
        }

        return grid;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        int[][] grid1 = {
            {1,7,3},
            {9,8,2},
            {4,5,6}
        };
        int[][] res1 = sol.sortMatrix(grid1);
        System.out.println(Arrays.deepToString(res1));

        int[][] grid2 = {
            {0,1},
            {1,2}
        };
        int[][] res2 = sol.sortMatrix(grid2);
        System.out.println(Arrays.deepToString(res2));

        int[][] grid3 = {
            {1}
        };
        int[][] res3 = sol.sortMatrix(grid3);
        System.out.println(Arrays.deepToString(res3));
    }
}