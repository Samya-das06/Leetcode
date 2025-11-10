class Solution {
    public int minOperations(int[] nums) {
        int[] stack = new int[nums.length];
        int top = 0, res = 0;

        for (int i = 0; i < nums.length; i++) {
            int a = nums[i];
            while (top > 0 && stack[top - 1] > a) {
                top--;
            }
            if (a == 0) 
                continue;
            if (top == 0 || stack[top - 1] < a) {
                res++;
                stack[top++] = a;
            }
        }
        return res;
    }
}