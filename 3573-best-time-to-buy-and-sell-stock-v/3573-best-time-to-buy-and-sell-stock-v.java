class Solution {
    public long maximumProfit(int[] prices, int k) {
        int n = prices.length;
        if (n == 0) 
            return 0;

        long[] profit = new long[k + 1];
        long[] buy = new long[k + 1];
        long[] sell = new long[k + 1];

        Arrays.fill(buy, Long.MIN_VALUE);

        for (int j = 0; j < n; j++) {
            long p = prices[j];
            for (int i = 0; i < k; i++) {
                profit[i] = Math.max(Math.max(profit[i], buy[i + 1] + p), sell[i + 1] - p);
                buy[i + 1] = Math.max(buy[i + 1], profit[i + 1] - p);
                sell[i + 1] = Math.max(sell[i + 1], profit[i + 1] + p);
            }
        }

        long maxProfit = 0;
        for (long p : profit) {
            maxProfit = Math.max(maxProfit, p);
        }

        return maxProfit;
    }
}