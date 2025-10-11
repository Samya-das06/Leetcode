class Solution {
    public long maximumTotalDamage(int[] power) {
        Map<Integer, Long> damageSum = new HashMap<>();
        for (int p : power) {
            damageSum.put(p, damageSum.getOrDefault(p, 0L) + p);
        }

        List<Integer> uniqueDamages = new ArrayList<>(damageSum.keySet());
        Collections.sort(uniqueDamages);
        int n = uniqueDamages.size();
        long[] dp = new long[n];

        for (int i = 0; i < n; i++) {
            long val = damageSum.get(uniqueDamages.get(i));
            int j = i - 1;
            while (j >= 0 && uniqueDamages.get(i) - uniqueDamages.get(j) <= 2) {
                j--;
            }
            long include = val + (j >= 0 ? dp[j] : 0);
            long exclude = i > 0 ? dp[i - 1] : 0;
            dp[i] = Math.max(include, exclude);
        }
        return dp[n - 1];
    }
}