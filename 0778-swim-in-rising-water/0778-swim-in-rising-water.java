class Solution {
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        boolean[][] visited = new boolean[n][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[]{grid[0][0], 0, 0});
        int[][] dirs = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        visited[0][0] = true;
        int result = 0;

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int elevation = current[0];
            int x = current[1];
            int y = current[2];
            result = Math.max(result, elevation);

            if (x == n - 1 && y == n - 1) {
                return result;
            }

            for (int[] dir : dirs) {
                int nx = x + dir[0];
                int ny = y + dir[1];
                if (nx >= 0 && ny >= 0 && nx < n && ny < n && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    pq.offer(new int[]{grid[nx][ny], nx, ny});
                }
            }
        }

        return -1;
    }
}