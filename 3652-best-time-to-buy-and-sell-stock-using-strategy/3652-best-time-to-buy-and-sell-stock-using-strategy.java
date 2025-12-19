class Solution {
    public long maxProfit(int[] prices, int[] strategy, int k) {
        int n=prices.length;
        long actualP=0;
        long[] profit=new long[n];
        for(int i=0;i<n;i++){
            profit[i] =(long)(strategy[i] * prices[i]);
            actualP += profit[i];
        }
        long originalWindProfit=0;
        long modifiedWindProfit=0;
        long maxGain=0;
        int i=0,j=0;
        while(j<n){
            originalWindProfit += profit[j];
            if(j - i + 1 > k/2){
                modifiedWindProfit += prices[j];
            }

            if(j-i+1 > k){
                originalWindProfit -= profit[i];
                modifiedWindProfit -= prices[i + k/2];
                i++;
            }
            if(j-i +1 ==k){
                maxGain =Math.max(maxGain, modifiedWindProfit - originalWindProfit);
            }

            j++;
        } 
        return actualP + maxGain;
    }
}