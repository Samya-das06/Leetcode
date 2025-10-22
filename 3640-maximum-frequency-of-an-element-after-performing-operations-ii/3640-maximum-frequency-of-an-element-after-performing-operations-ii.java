class Solution {
    public int maxFrequency(int[] nums, int k, int numOperations) {
        Arrays.sort(nums);
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0)+1);
        }
        int max = 0;
        for (int i = 0;i < nums.length;i++) {
            int lowerBound = binarySearchLower(nums, nums[i]-k);
            int upperBound = binarySearchUpper(nums, nums[i]+k)+1;
            int totalOfCurrentNumber = freq.get(nums[i]);
            max = Math.max(max, totalOfCurrentNumber);
            int onesCanApplyOperationOn = upperBound - lowerBound - totalOfCurrentNumber;
        max = Math.max(max, Math.min(onesCanApplyOperationOn, numOperations) + totalOfCurrentNumber);
            lowerBound = binarySearchLower(nums, nums[i] - k * 2);
            max = Math.max(max, Math.min(i - lowerBound + 1, numOperations));
            }
        return max;
    }

    private int binarySearchUpper(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;
        int ans = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            
            if (nums[mid] <= target) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
        
    }

    private int binarySearchLower(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
        
    }
}