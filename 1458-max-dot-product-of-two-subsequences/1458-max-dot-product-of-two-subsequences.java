class Solution {
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int max1 = Arrays.stream(nums1).max().getAsInt();
        int min1 = Arrays.stream(nums1).min().getAsInt();
        int max2 = Arrays.stream(nums2).max().getAsInt();
        int min2 = Arrays.stream(nums2).min().getAsInt();
        if (max1 < 0 && min2 > 0) return max1 * min2;
        if (min1 > 0 && max2 < 0) return min1 * max2;
        int n1 = nums1.length;
        int n2 = nums2.length;
        int[][] dp = new int[n1 + 1][n2 + 1];
        for (int i = n1 - 1; i >= 0; i--) {
            for (int j = n2 - 1; j >= 0; j--) {
                dp[i][j] = Math.max(nums1[i] * nums2[j] + dp[i + 1][j + 1],
                                    Math.max(dp[i + 1][j], dp[i][j + 1]));
            }
        }
        return dp[0][0];
    }
}