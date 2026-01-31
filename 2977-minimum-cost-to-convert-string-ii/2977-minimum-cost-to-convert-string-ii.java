class Solution {
    public long minimumCost(String source, String target, String[] original, String[] changed, int[] cost) {
        Map<String, Integer> idx = new HashMap<>();
        int cnt = 0;
        for (String s : original)
            if (!idx.containsKey(s)) idx.put(s, cnt++);
        for (String s : changed)
            if (!idx.containsKey(s)) idx.put(s, cnt++);
        long INF = Long.MAX_VALUE / 4;
        long[][] dist = new long[cnt][cnt];
        for (int i = 0; i < cnt; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }
        for (int i = 0; i < cost.length; i++) {
            int u = idx.get(original[i]);
            int v = idx.get(changed[i]);
            dist[u][v] = Math.min(dist[u][v], cost[i]);
        }
        for (int k = 0; k < cnt; k++)
            for (int i = 0; i < cnt; i++)
                for (int j = 0; j < cnt; j++)
                    if (dist[i][k] + dist[k][j] < dist[i][j])
                        dist[i][j] = dist[i][k] + dist[k][j];

        int n = source.length();
        long[] dp = new long[n + 1];
        Arrays.fill(dp, INF);
        dp[n] = 0;
        Set<Integer> lens = new HashSet<>();
        for (String s : original) 
            lens.add(s.length());
        for (int i = n - 1; i >= 0; i--) {
            if (source.charAt(i) == target.charAt(i))
                dp[i] = dp[i + 1];

            for (int L : lens) {
                if (i + L > n) 
                    continue;
                String s1 = source.substring(i, i + L);
                String s2 = target.substring(i, i + L);
                if (idx.containsKey(s1) && idx.containsKey(s2)) {
                    int u = idx.get(s1), v = idx.get(s2);
                    if (dist[u][v] < INF && dp[i + L] < INF)
                        dp[i] = Math.min(dp[i], dist[u][v] + dp[i + L]);
                }
            }
        }
        return dp[0] == INF ? -1 : dp[0];
    }
}