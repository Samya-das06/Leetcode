class Solution {
    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        int[][] grid = new int[m][n];
        int[][] directions = {{0,1}, {0,-1}, {1,0}, {-1,0}};
        for(int[] wall: walls){
            grid[wall[0]][wall[1]] = 1;
        }
        for(int[] guard: guards){
            int x = guard[0];
            int y = guard[1];
            grid[guard[0]][guard[1]] = 1;
            for(int[] direction: directions){
                while(true){
                    x+= direction[0];
                    y+= direction[1];
                    if(x>=0 && x<m && y>=0 && y<n && grid[x][y]!=1){
                        if(direction[0]==1 || direction[0]==-1){
                            if(grid[x][y]==2){
                                x = guard[0];
                                y = guard[1];
                                break;
                            }
                            grid[x][y] = 2;
                        }
                        else{
                            if(grid[x][y]==3){
                                x = guard[0];
                                y = guard[1];
                                break;
                            }
                            grid[x][y] = 3;
                        }
                    }
                    else{
                        x = guard[0];
                        y = guard[1];
                        break;
                    }
                }
            }
        }
        int count = 0;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j]==0){
                    count++;
                }
            }
        }
        return count;
    }
}