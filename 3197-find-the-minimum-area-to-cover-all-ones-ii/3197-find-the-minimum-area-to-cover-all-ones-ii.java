class Solution {
    private int[][] grid;
    private int m, n;
    
    public int minimumSum(int[][] grid) {
        this.grid = grid;
        this.m = grid.length;
        this.n = grid[0].length;
        
        int minArea = Integer.MAX_VALUE;
        
        for (int split1 = 1; split1 < m; split1++) {
            for (int split2 = split1 + 1; split2 < m; split2++) {
                int area1 = getMinRectArea(0, split1, 0, n);
                int area2 = getMinRectArea(split1, split2, 0, n);
                int area3 = getMinRectArea(split2, m, 0, n);
                
                if (area1 > 0 && area2 > 0 && area3 > 0) {
                    minArea = Math.min(minArea, area1 + area2 + area3);
                }
            }
        }
        
        for (int split1 = 1; split1 < n; split1++) {
            for (int split2 = split1 + 1; split2 < n; split2++) {
                int area1 = getMinRectArea(0, m, 0, split1);
                int area2 = getMinRectArea(0, m, split1, split2);
                int area3 = getMinRectArea(0, m, split2, n);
                
                if (area1 > 0 && area2 > 0 && area3 > 0) {
                    minArea = Math.min(minArea, area1 + area2 + area3);
                }
            }
        }
        
        for (int rowSplit = 1; rowSplit < m; rowSplit++) {
            for (int colSplit = 1; colSplit < n; colSplit++) {
                int area1 = getMinRectArea(0, rowSplit, 0, n);
                int area2 = getMinRectArea(rowSplit, m, 0, colSplit);
                int area3 = getMinRectArea(rowSplit, m, colSplit, n);
                
                if (area1 > 0 && area2 > 0 && area3 > 0) {
                    minArea = Math.min(minArea, area1 + area2 + area3);
                }
            }
        }
        
        for (int rowSplit = 1; rowSplit < m; rowSplit++) {
            for (int colSplit = 1; colSplit < n; colSplit++) {
                int area1 = getMinRectArea(0, rowSplit, 0, colSplit);
                int area2 = getMinRectArea(0, rowSplit, colSplit, n);
                int area3 = getMinRectArea(rowSplit, m, 0, n);
                
                if (area1 > 0 && area2 > 0 && area3 > 0) {
                    minArea = Math.min(minArea, area1 + area2 + area3);
                }
            }
        }
        
        for (int colSplit = 1; colSplit < n; colSplit++) {
            for (int rowSplit = 1; rowSplit < m; rowSplit++) {
                int area1 = getMinRectArea(0, m, 0, colSplit);
                int area2 = getMinRectArea(0, rowSplit, colSplit, n);
                int area3 = getMinRectArea(rowSplit, m, colSplit, n);
                
                if (area1 > 0 && area2 > 0 && area3 > 0) {
                    minArea = Math.min(minArea, area1 + area2 + area3);
                }
            }
        }
        
        for (int colSplit = 1; colSplit < n; colSplit++) {
            for (int rowSplit = 1; rowSplit < m; rowSplit++) {
                int area1 = getMinRectArea(0, rowSplit, 0, colSplit);
                int area2 = getMinRectArea(rowSplit, m, 0, colSplit);
                int area3 = getMinRectArea(0, m, colSplit, n);
                
                if (area1 > 0 && area2 > 0 && area3 > 0) {
                    minArea = Math.min(minArea, area1 + area2 + area3);
                }
            }
        }
        
        return minArea;
    }
    
    private int getMinRectArea(int r1, int r2, int c1, int c2) {
        int minR = Integer.MAX_VALUE;
        int maxR = -1;
        int minC = Integer.MAX_VALUE;
        int maxC = -1;
        
        for (int i = r1; i < r2; i++) {
            for (int j = c1; j < c2; j++) {
                if (grid[i][j] == 1) {
                    minR = Math.min(minR, i);
                    maxR = Math.max(maxR, i);
                    minC = Math.min(minC, j);
                    maxC = Math.max(maxC, j);
                }
            }
        }
        
        if (maxR == -1) {
            return 0;
        }
        
        return (maxR - minR + 1) * (maxC - minC + 1);
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        int[][] grid1 = {{1,0,1},{1,1,1}};
        System.out.println("Example 1 result: " + solution.minimumSum(grid1));
        
        int[][] grid2 = {{1,0,1,0},{0,1,0,1}};
        System.out.println("Example 2 result: " + solution.minimumSum(grid2));
        
        int[][] grid3 = {{1,1},{1,1}};
        System.out.println("Additional test result: " + solution.minimumSum(grid3));
    }
}