import java.util.*;

class Solution {
    public int[] avoidFlood(int[] rains) {
        int n = rains.length;
        int[] res = new int[n];
        Map<Integer, Integer> fullLakes = new HashMap<>();
        TreeSet<Integer> dryDays = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            if (rains[i] == 0) {
                dryDays.add(i);
                res[i] = 1;
            } else {
                int lake = rains[i];
                if (fullLakes.containsKey(lake)) {
                    int lastRainDay = fullLakes.get(lake);
                    Integer dryDay = dryDays.higher(lastRainDay);
                    if (dryDay == null) {
                        return new int[0];
                    }
                    res[dryDay] = lake;
                    dryDays.remove(dryDay);
                }
                fullLakes.put(lake, i);
                res[i] = -1;
            }
        }
        return res;
    }
}