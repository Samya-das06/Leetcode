class Solution {
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        int m = languages.length;

        List<Set<Integer>> userLanguages = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            Set<Integer> langSet = new HashSet<>();
            for (int lang : languages[i]) {
                langSet.add(lang);
            }
            userLanguages.add(langSet);
        }

        Set<Integer> usersToConsider = new HashSet<>();
        for (int[] friendship : friendships) {
            int u = friendship[0] - 1;
            int v = friendship[1] - 1;
            
            boolean canCommunicate = false;
            for (int lang : userLanguages.get(u)) {
                if (userLanguages.get(v).contains(lang)) {
                    canCommunicate = true;
                    break;
                }
            }
            if (!canCommunicate) {
                usersToConsider.add(u);
                usersToConsider.add(v);
            }
        }

        if (usersToConsider.isEmpty()) return 0;

        int[] languageKnownCount = new int[n + 1];
        for (int user : usersToConsider) {
            for (int lang : userLanguages.get(user)) {
                languageKnownCount[lang]++;
            }
        }

        int maxKnown = 0;
        for (int i = 1; i <= n; i++) {
            maxKnown = Math.max(maxKnown, languageKnownCount[i]);
        }

        return usersToConsider.size() - maxKnown;
    }
}