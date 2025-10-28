class Solution {
    public int countValidSelections(int[] nums) {
        int n = nums.length;
        int[] ps = new int[n + 1];

        for (int i = 0; i < n; i++) {
            ps[i + 1] = ps[i] + nums[i];
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                if (ps[n] == 2 * ps[i]) {
                    res += 2;
                } else if (Math.abs(ps[n] - 2 * ps[i]) == 1) {
                    res += 1;
                }
            }
        }

        return res;
    }
}