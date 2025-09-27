class Solution {
    public double largestTriangleArea(int[][] points) {
        int n = points.length;
        double maxArea = 0.0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    int[] a = points[i];
                    int[] b = points[j];
                    int[] c = points[k];
                    double area = 0.5 * Math.abs(
                        a[0] * (b[1] - c[1]) +
                        b[0] * (c[1] - a[1]) +
                        c[0] * (a[1] - b[1])
                    );
                    
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        return maxArea;
    }
}