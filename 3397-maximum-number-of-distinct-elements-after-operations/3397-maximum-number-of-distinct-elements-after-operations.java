class Solution {
    public int maxDistinctElements(int[] nums, int k) {
        Arrays.sort(nums);
        int distinctCount = 0;
        long prev = Long.MIN_VALUE;
        for (int num : nums) {
            long start = Math.max((long)num - k, prev + 1);
            long end = (long)num + k;
            if (start <= end) {
                prev = start;
                distinctCount++;
            }
            
        }
        return distinctCount;
    }
}