class Solution {
    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        long mod = 1_000_000_007L;
        int[] h = Arrays.copyOf(hFences, hFences.length + 2);
        int[] v = Arrays.copyOf(vFences, vFences.length + 2);
        h[h.length - 2] = 1;
        h[h.length - 1] = m;
        v[v.length - 2] = 1;
        v[v.length - 1] = n;
        Arrays.sort(h);
        Arrays.sort(v);
        HashSet<Long> set = new HashSet<>();
        for (int i = 0; i < h.length; i++){
            for (int j = i + 1; j < h.length; j++){
                set.add((long)(h[j] - h[i]));
            }
        }
        long maxArea = -1;
        for (int i = 0; i < v.length; i++){
            for (int j = i + 1; j < v.length; j++){
                long d = v[j] - v[i];
                if (set.contains(d)){
                    maxArea = Math.max(maxArea, d);
                }
            }
        }
        if (maxArea == -1)
            return -1;
        return (int)((maxArea * maxArea) % mod);
    }
}