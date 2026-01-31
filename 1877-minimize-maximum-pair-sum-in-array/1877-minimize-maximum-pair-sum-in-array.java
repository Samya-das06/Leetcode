class Solution {
    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int res = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            int pairSum = nums[i] + nums[nums.length - 1 - i];
            res = Math.max(res, pairSum);
        }
        return res;
    }
}