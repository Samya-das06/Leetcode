class Solution {
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        PriorityQueue<ClassInfo> pq = new PriorityQueue<>((a, b) -> Double.compare(b.gain(), a.gain()));

        for (int[] c : classes) {
            pq.offer(new ClassInfo(c[0], c[1]));
        }

        while (extraStudents > 0) {
            ClassInfo top = pq.poll();
            top.pass++;
            top.total++;
            pq.offer(top);
            extraStudents--;
        }

        double sum = 0.0;
        while (!pq.isEmpty()) {
            ClassInfo c = pq.poll();
            sum += (double) c.pass / c.total;
        }

        return sum / classes.length;
    }

    static class ClassInfo {
        int pass, total;

        ClassInfo(int pass, int total) {
            this.pass = pass;
            this.total = total;
        }

        double gain() {
            return (double)(pass + 1) / (total + 1) - (double) pass / total;
        }
    }
}