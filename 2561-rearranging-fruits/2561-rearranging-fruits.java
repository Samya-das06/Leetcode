class Solution {
    public long minCost(int[] basket1, int[] basket2) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int b : basket1) count.put(b, count.getOrDefault(b, 0) + 1);
        for (int b : basket2) count.put(b, count.getOrDefault(b, 0) - 1);

        List<Integer> surplus = new ArrayList<>();
        int minFruit = Integer.MAX_VALUE;

        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            int fruit = entry.getKey(), diff = entry.getValue();
            minFruit = Math.min(minFruit, fruit);
            if (diff % 2 != 0) return -1;

            int times = Math.abs(diff) / 2;
            for (int i = 0; i < times; i++) {
                surplus.add(fruit);
            }
        }

        Collections.sort(surplus);
        long cost = 0;
        int n = surplus.size();
        for (int i = 0; i < n / 2; i++) {
            cost += Math.min(surplus.get(i), 2 * minFruit);
        }
        return cost;
    }
}