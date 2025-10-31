class Solution {
    public int[] getSneakyNumbers(int[] nums) {
        HashSet<Integer> hset = new HashSet<>();
        int ans[] = new int[2];
        int index = 0;
        for(int num : nums) {
            if(hset.contains(num)) {
                ans[index++] = num;
            }
            hset.add(num);
        }
        return ans;
    }
}