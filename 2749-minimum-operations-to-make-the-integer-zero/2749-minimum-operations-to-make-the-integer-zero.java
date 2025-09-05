class Solution {
    public int makeTheIntegerZero(int num1, int num2) {
        for (int k = 1; k <= 60; k++) {
            long total = (long)num1 - (long)k * num2;
            if (total < k) continue;
            int bits = Long.bitCount(total);
            if (bits <= k) {
                return k;
            }
        }
        return -1;
    }
}