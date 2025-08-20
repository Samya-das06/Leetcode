class Solution {
    public boolean judgePoint24(int[] cards) {
        double[] nums = new double[4];
        for (int i = 0; i < 4; i++) nums[i] = cards[i];
        return dfs(nums);
    }
    
    private boolean dfs(double[] nums) {
        int n = nums.length;
        if (n == 1) return Math.abs(nums[0] - 24) < 1e-6;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                double[] next = new double[n - 1];
                int idx = 0;
                for (int k = 0; k < n; k++) {
                    if (k != i && k != j) next[idx++] = nums[k];
                }
                
                for (double val : compute(nums[i], nums[j])) {
                    next[idx] = val;
                    if (dfs(next)) return true;
                }
            }
        }
        return false;
    }
    
    private double[] compute(double a, double b) {
        return new double[]{
            a + b,
            a - b,
            b - a,
            a * b,
            b != 0 ? a / b : Double.NaN,
            a != 0 ? b / a : Double.NaN
        };
    }
}