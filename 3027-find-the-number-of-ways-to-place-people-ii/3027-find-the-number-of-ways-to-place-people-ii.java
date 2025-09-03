import java.util.*;

class Solution {
    public int numberOfPairs(int[][] points) {
        int n = points.length;
        int[] xs = new int[n];
        int[] ys = new int[n];
        for (int i = 0; i < n; i++) {
            xs[i] = points[i][0];
            ys[i] = points[i][1];
        }

        int[] sortedX = Arrays.stream(xs).distinct().sorted().toArray();
        int[] sortedY = Arrays.stream(ys).distinct().sorted().toArray();

        java.util.function.IntUnaryOperator compressX = x -> Arrays.binarySearch(sortedX, x) + 1;
        java.util.function.IntUnaryOperator compressY = y -> Arrays.binarySearch(sortedY, y) + 1;

        int maxX = sortedX.length;
        int maxY = sortedY.length;

        int[][] grid = new int[maxX + 1][maxY + 1];

        for (int i = 0; i < n; i++) {
            int cx = compressX.applyAsInt(points[i][0]);
            int cy = compressY.applyAsInt(points[i][1]);
            grid[cx][cy]++;
        }

        for (int i = 1; i <= maxX; i++) {
            for (int j = 1; j <= maxY; j++) {
                grid[i][j] += grid[i - 1][j] + grid[i][j - 1] - grid[i - 1][j - 1];
            }
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            int x1 = points[i][0], y1 = points[i][1];
            int cx1 = compressX.applyAsInt(x1);
            int cy1 = compressY.applyAsInt(y1);

            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                int x2 = points[j][0], y2 = points[j][1];
                if (x1 <= x2 && y1 >= y2) {
                    int cx2 = compressX.applyAsInt(x2);
                    int cy2 = compressY.applyAsInt(y2);

                    int inside = grid[cx2][cy1] - grid[cx1 - 1][cy1] - grid[cx2][cy2 - 1] + grid[cx1 - 1][cy2 - 1];
                    inside -= 2;

                    if (inside == 0) count++;
                }
            }
        }
        return count;
    }
}