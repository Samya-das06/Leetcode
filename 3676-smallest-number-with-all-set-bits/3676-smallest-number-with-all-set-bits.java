class Solution {
    public int smallestNumber(int n) {
        int i = n;
        while (true) {
            if ((i & (i + 1)) == 0)
                return i;
            i++;
        }
    }
}