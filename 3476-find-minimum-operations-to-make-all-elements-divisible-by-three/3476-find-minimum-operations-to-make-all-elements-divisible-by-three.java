class Solution {
    public int minimumOperations(int[] nums) {
        int minOperations = 0;
        for(int n : nums){
            if(n % 3 != 0)
                minOperations++;
        }
        return minOperations;
    }
}