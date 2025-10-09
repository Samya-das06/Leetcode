import java.util.Arrays;

class Solution {
    public long minTime(int[] skill, int[] mana) {
        int n = skill.length;
        int m = mana.length;
        long[] prefixSkill = new long[n];
        prefixSkill[0] = skill[0];
        for (int i = 1; i < n; i++) {
            prefixSkill[i] = prefixSkill[i - 1] + skill[i];
        }

        long startTime = 0;
        for (int j = 1; j < m; j++) {
            long maxRequiredDelay = Long.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                long prevPrefix = (i == 0) ? 0 : prefixSkill[i - 1];
                long currentDelay = (long) mana[j - 1] * prefixSkill[i] - (long) mana[j] * prevPrefix;
                if (currentDelay > maxRequiredDelay) {
                    maxRequiredDelay = currentDelay;
                }
            }
            startTime += maxRequiredDelay;
        }
        return startTime + (long) mana[m - 1] * prefixSkill[n - 1];
    }
}