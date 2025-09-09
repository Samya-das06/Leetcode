class Solution {
    public int[] sumZero(int n) {
        int[] result = new int[n];
        int idx = 0;
        if (n % 2 == 1) {
            result[idx++] = 0;
        }
        for (int i = 1; idx < n; i++) {
            result[idx++] = i;
            if (idx < n) {
                result[idx++] = -i;
            }
        }
        return result;
    }
}