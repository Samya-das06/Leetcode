class Solution {
    public List<Integer> replaceNonCoprimes(int[] nums) {
        Deque<Integer> stack = new ArrayDeque<>();
        
        for (int num : nums) {
            while (!stack.isEmpty()) {
                int top = stack.peek();
                int gcd = gcd(top, num);
                if (gcd == 1) break;
                num = lcm(top, num);
                stack.pop();
            }
            stack.push(num);
        }
        
        List<Integer> result = new ArrayList<>(stack.size());
        while (!stack.isEmpty()) {
            result.add(stack.removeLast());
        }
        return result;
    }
    
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    
    private int lcm(int a, int b) {
        return a / gcd(a, b) * b;
    }
}