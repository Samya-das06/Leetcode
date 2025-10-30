class Solution {
    public int minNumberOperations(int[] target) {
        int totalOperations = target[0];
        int operationsWeCanReuse = target[0];
        
        for (int i = 1; i < target.length; ++i) {
            if (target[i] <= operationsWeCanReuse) {
                operationsWeCanReuse = target[i];
            } else {
                totalOperations += target[i] - operationsWeCanReuse; 
                operationsWeCanReuse = target[i];
            }
        }
        return totalOperations;
    }
}