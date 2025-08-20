class Solution {
    static final int MOD = 1_000_000_007;

    public int[] productQueries(int n, int[][] queries) {
        List<Integer> powers = new ArrayList<>();
        for (int i = 0; i < 31; i++) {
            if ((n & (1 << i)) != 0) powers.add(i);
        }

        long[] prefix = new long[powers.size() + 1];
        prefix[0] = 1;
        for (int i = 0; i < powers.size(); i++) {
            prefix[i + 1] = (prefix[i] * modPow(2, powers.get(i), MOD)) % MOD;
        }

        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0], r = queries[i][1];
            long inv = modInverse(prefix[l], MOD);
            long val = (prefix[r + 1] * inv) % MOD;
            res[i] = (int) val;
        }
        return res;
    }

    private long modPow(long base, int exp, int mod) {
        long result = 1;
        long cur = base;
        while (exp > 0) {
            if ((exp & 1) == 1) result = (result * cur) % mod;
            cur = (cur * cur) % mod;
            exp >>= 1;
        }
        return result;
    }

    private long modInverse(long a, int m) {
        return modPow(a, m - 2, m);
    }
}