import java.util.*;

class Solution {
    public int numberOfPairs(int[][] points) {
        int n = points.length;
        
        // Extract unique xs and ys
        TreeSet<Integer> xsSet = new TreeSet<>();
        TreeSet<Integer> ysSet = new TreeSet<>();
        for (int[] p : points) {
            xsSet.add(p[0]);
            ysSet.add(p[1]);
        }
        
        // Compress coordinates
        List<Integer> xs = new ArrayList<>(xsSet);
        List<Integer> ys = new ArrayList<>(ysSet);
        
        Map<Integer, Integer> xMap = new HashMap<>();
        Map<Integer, Integer> yMap = new HashMap<>();
        
        for (int i = 0; i < xs.size(); i++) xMap.put(xs.get(i), i);
        for (int i = 0; i < ys.size(); i++) yMap.put(ys.get(i), i);
        
        int X = xs.size();
        int Y = ys.size();
        
        // Create grid
        int[][] grid = new int[X][Y];
        for (int[] p : points) {
            int cx = xMap.get(p[0]);
            int cy = yMap.get(p[1]);
            grid[cx][cy] = 1;
        }
        
        // Build prefix sums
        int[][] prefix = new int[X + 1][Y + 1];
        for (int i = 1; i <= X; i++) {
            for (int j = 1; j <= Y; j++) {
                prefix[i][j] = prefix[i-1][j] + prefix[i][j-1] - prefix[i-1][j-1] + grid[i-1][j-1];
            }
        }
        
        int count = 0;
        // For each pair (Alice, Bob)
        for (int i = 0; i < n; i++) {
            int[] alice = points[i];
            int ax = xMap.get(alice[0]);
            int ay = yMap.get(alice[1]);
            
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                int[] bob = points[j];
                int bx = xMap.get(bob[0]);
                int by = yMap.get(bob[1]);
                
                // Alice must be upper-left, so
                // Alice x <= Bob x and Alice y >= Bob y in original coordinates
                if (alice[0] > bob[0] || alice[1] < bob[1]) continue;
                
                // Query rectangle in compressed grid:
                // rectangle [ax, bx] x [by, ay] in compressed indices
                
                // but ys are sorted ascending, so
                // y goes from by to ay, but since y is sorted ascending,
                // by may be smaller index than ay, so we need min and max
                
                int x1 = Math.min(ax, bx);
                int x2 = Math.max(ax, bx);
                int y1 = Math.min(by, ay);
                int y2 = Math.max(by, ay);
                
                int inside = prefix[x2 + 1][y2 + 1] - prefix[x1][y2 + 1] - prefix[x2 + 1][y1] + prefix[x1][y1];
                
                // inside count includes Alice and Bob themselves
                // If inside == 2, then no other points inside
                if (inside == 2) count++;
            }
        }
        
        return count;
    }
}