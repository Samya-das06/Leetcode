class Solution {
    public int maxBottlesDrunk(int numBottles, int numExchange) {
        int totalDrunk = 0;
        int emptyBottles = 0;
        while (numBottles > 0) {
            totalDrunk += numBottles;
            emptyBottles += numBottles;
            numBottles = 0;

            if (emptyBottles >= numExchange) {
                numBottles = 1;
                emptyBottles -= numExchange;
                numExchange++;
            } else {
                break;
            }
        }
        return totalDrunk;
    }
}