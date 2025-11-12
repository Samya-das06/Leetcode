class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int oneCount = 0;
        int g = 0;

        for (int x : nums) {
            if (x == 1) oneCount++;
            g = gcd(g, x);
        }

        if (oneCount > 0) {
            return n - oneCount;
        }
        if (g > 1) {
            return -1;
        }

        int minLen = n;
        for (int i = 0; i < n; i++) {
            g = 0;
            for (int j = i; j < n; j++) {
                g = gcd(g, nums[j]);
                if (g == 1) {
                    minLen = Math.min(minLen, j - i + 1);
                    break;
                }
            }
        }
        return minLen + n - 2;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}