class Solution {

    public int countCoveredBuildings(int n, int[][] b) {
        int size = n + 1;
        int[] rmax = new int[size];
        int[] rmin = new int[size];
        int[] cmax = new int[size];
        int[] cmin = new int[size];
        Arrays.fill(cmin, size);
        Arrays.fill(rmin , size);
        for (int[] point : b) {
            int x = point[0];
            int y = point[1];
            rmax[y] = Math.max(rmax[y], x);
            rmin[y] = Math.min(rmin[y], x);
            cmax[x] = Math.max(cmax[x], y);
            cmin[x] = Math.min(cmin[x], y);
        }
        int ans = 0;
        for (int[] point : b) {
            int x = point[0];
            int y = point[1];
            if (rmin[y] < x && x < rmax[y] && cmin[x] < y && y < cmax[x]) {
                ans++;
            }
        }
        return ans;
    }
}