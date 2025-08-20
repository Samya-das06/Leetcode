class Solution {
    int[] baskets;
    int n;
    int[] segtree;

    void build(int idx, int start, int end) {
        if (start == end) {
            segtree[idx] = baskets[start];
            return;
        }
        int mid = (start + end) / 2;
        build(2*idx+1, start, mid);
        build(2*idx+2, mid+1, end);
        segtree[idx] = Math.max(segtree[2*idx+1], segtree[2*idx+2]);
    }

    void update(int idx, int start, int end, int pos) {
        if (start == end) {
            segtree[idx] = 0; // mark basket used
            return;
        }
        int mid = (start + end) / 2;
        if (pos <= mid) update(2*idx+1, start, mid, pos);
        else update(2*idx+2, mid+1, end, pos);
        segtree[idx] = Math.max(segtree[2*idx+1], segtree[2*idx+2]);
    }

    // Find leftmost basket index with capacity >= val
    int query(int idx, int start, int end, int val) {
        if (segtree[idx] < val) return -1;
        if (start == end) return start;
        int mid = (start + end) / 2;
        if (segtree[2*idx+1] >= val)
            return query(2*idx+1, start, mid, val);
        else
            return query(2*idx+2, mid+1, end, val);
    }

    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        this.baskets = baskets;
        this.n = baskets.length;
        this.segtree = new int[4 * n];
        build(0, 0, n - 1);

        int unplaced = 0;
        for (int f : fruits) {
            int idx = query(0, 0, n - 1, f);
            if (idx == -1) {
                unplaced++;
            } else {
                update(0, 0, n - 1, idx);
            }
        }
        return unplaced;
    }
}