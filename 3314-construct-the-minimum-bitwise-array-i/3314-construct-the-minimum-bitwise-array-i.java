class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] res = new int[n];
        for (int idx = 0; idx < n; idx++) {
            int i = nums.get(idx);
            if ((i & 1) != 0) {
                int bit = ((i + 1) & ~i) >> 1;
                res[idx] = i & ~bit;
            } else {
                res[idx] = -1;
            }
        }
        return res;
    }
}