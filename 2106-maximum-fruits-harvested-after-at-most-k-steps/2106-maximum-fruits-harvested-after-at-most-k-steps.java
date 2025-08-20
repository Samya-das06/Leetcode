class Solution {
    public int maxTotalFruits(int[][] fruits, int startPos, int k) {
        int n = fruits.length, maxFruit = 0, sum = 0, left = 0;
        for (int right = 0; right < n; right++) {
            sum += fruits[right][1];
            while (left <= right && !canReach(fruits, left, right, startPos, k)) {
                sum -= fruits[left][1];
                left++;
            }
            maxFruit = Math.max(maxFruit, sum);
        }
        return maxFruit;
    }

    private boolean canReach(int[][] fruits, int left, int right, int startPos, int k) {
        int leftPos = fruits[left][0], rightPos = fruits[right][0];
        int distLeftFirst = Math.abs(startPos - leftPos) + (rightPos - leftPos);
        if (distLeftFirst <= k) return true;
        int distRightFirst = Math.abs(rightPos - startPos) + (rightPos - leftPos);
        return distRightFirst <= k;
    }
}