class Solution {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        boolean[] used = new boolean[baskets.length];
        int unplaced = 0;
        for (int f : fruits) {
            boolean placed = false;
            for (int i = 0; i < baskets.length; i++) {
                if (!used[i] && baskets[i] >= f) {
                    used[i] = true;
                    placed = true;
                    break;
                }
            }
            if (!placed) unplaced++;
        }
        return unplaced;
    }
}