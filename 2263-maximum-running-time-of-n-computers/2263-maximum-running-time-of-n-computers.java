class Solution {
    public long maxRunTime(int n, int[] batteries) {
        long sum = 0;
        for(int battery : batteries) {
            sum += battery;
        }
        long lo = 1;
        long hi = sum / n;
        while(lo <= hi) {
            long mid = lo + (hi - lo) / 2;
            if(canRun(n, batteries, mid)) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return hi;
    }

    private boolean canRun(int n, int[] batteries, long maxRunningTime) {
        long totalPower = 0;
        for(int battery : batteries) {
            totalPower += Math.min(battery, maxRunningTime);
        }
        return totalPower >= n * maxRunningTime;
    }
}