class Solution {
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] prefix = new int[m][n];
        for(int j=0; j<n; j++){
            int sum = 0;
            for(int i=0; i<m;i++){
                if(matrix[i][j] == '1')sum ++;
                else if(matrix[i][j] == '0') sum = 0; 
                prefix[i][j] = sum;
            }
        }
        int max = 0;
        for(int i=0; i<m; i++){
            max = Math.max(max, largestRectangle(prefix[i]));
        }
        return max;
    }
    int largestRectangle(int[] heights){
        Deque<Integer> stack = new ArrayDeque<>();
        int maxArea = 0;
        int n = heights.length;
        for(int i=0; i<=n; i++){
            int currHeight = (i == n) ? 0 : heights[i];
            while(!stack.isEmpty() && currHeight < heights[stack.peek()]){
                int height = heights[stack.pop()];
                int nse = i;
                int pse = -1;
                if(!stack.isEmpty()) pse = stack.peek();
                int width = nse - pse - 1;
                int area = (height * width);
                maxArea = Math.max(maxArea, area);
            }
            stack.push(i);
        }
        return maxArea;
    }
}