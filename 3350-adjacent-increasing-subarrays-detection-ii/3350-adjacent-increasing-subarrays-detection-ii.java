class Solution {
    public int maxIncreasingSubarrays(List<Integer> nums) {
        int n = nums.size();
        int[] inc = new int[n];
        inc[0] = 1;
        for (int i = 1; i < n; i++) {
            if (nums.get(i) > nums.get(i - 1)) {
                inc[i] = inc[i - 1] + 1;
            } else {
                inc[i] = 1;
            }
        }

        int low = 1, high = n / 2;
        int res = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            boolean found = false;
            for (int i = mid - 1; i + mid < n; i++) {
                if (inc[i] >= mid && inc[i + mid] >= mid) {
                    found = true;
                    break;
                }
            }
            if (found) {
                res = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return res;
    }
}