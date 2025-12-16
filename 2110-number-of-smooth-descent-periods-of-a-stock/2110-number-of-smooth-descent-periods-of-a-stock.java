class Solution {
    public long getDescentPeriods(int[] prices) {
        List<Integer> consecutiveDecreasingSeries = new ArrayList<>();
        long result = prices.length;
        for(int i=1; i<prices.length; i++) {
            int j = 1;
            while(i<prices.length && prices[i-1]-1 == prices[i]) {
                j++;
                i++;
            }
            if(j > 1) {
                consecutiveDecreasingSeries.add(j);
            }
        }

        for(int i=0; i<consecutiveDecreasingSeries.size(); i++) {
            int currInt = consecutiveDecreasingSeries.get(i);
            result += (long) currInt * (currInt-1) / 2; 
        }
        return result;
    }
}