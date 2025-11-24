class Solution {
    public int maxSumDivThree(int[] nums) {
        int sum = 0;
        int min1mod1 = Integer.MAX_VALUE, min2mod1 = Integer.MAX_VALUE;
        int min1mod2 = Integer.MAX_VALUE, min2mod2 = Integer.MAX_VALUE;
        for (int x : nums) {
            sum += x;
            int r = x % 3;
            if (r == 1) {
                if (x < min1mod1) {
                    min2mod1 = min1mod1;
                    min1mod1 = x;
                } else if (x < min2mod1) {
                    min2mod1 = x;
                }
            } else if (r == 2) {
                if (x < min1mod2) {
                    min2mod2 = min1mod2;
                    min1mod2 = x;
                } else if (x < min2mod2) {
                    min2mod2 = x;
                }
            }
        }
        int r = sum % 3;
        if (r == 0) return sum;
        int res = 0;
        if (r == 1) {
            int option1 = min1mod1;
            int option2 = (min1mod2 == Integer.MAX_VALUE || min2mod2 == Integer.MAX_VALUE) ? Integer.MAX_VALUE : min1mod2 + min2mod2;
            int subtract = Math.min(option1, option2);
            if (subtract == Integer.MAX_VALUE) return 0;
            res = sum - subtract;
        } else {
            int option1 = min1mod2;
            int option2 = (min1mod1 == Integer.MAX_VALUE || min2mod1 == Integer.MAX_VALUE) ? Integer.MAX_VALUE : min1mod1 + min2mod1;
            int subtract = Math.min(option1, option2);
            if (subtract == Integer.MAX_VALUE) return 0;
            res = sum - subtract;
        }
        return res;
    }
}