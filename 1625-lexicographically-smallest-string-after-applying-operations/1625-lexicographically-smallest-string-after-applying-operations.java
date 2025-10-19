class Solution {
    public String findLexSmallestString(String s, int a, int b) {
        int n = s.length();
        String ans = s;
        Queue<String> queue = new LinkedList<>();
        Set<String> seen = new HashSet<>();
        queue.offer(s);
        seen.add(s);
        while (!queue.isEmpty()) {
            String curr = queue.poll();
            if (curr.compareTo(ans) < 0) ans = curr;
            char[] arr = curr.toCharArray();
            for (int i = 1; i < n; i += 2) {
                int digit = arr[i] - '0';
                digit = (digit + a) % 10;
                arr[i] = (char)(digit + '0');
            }
            String addStr = new String(arr);
            if (!seen.contains(addStr)) {
                seen.add(addStr);
                queue.offer(addStr);
            }
            String rotateStr = curr.substring(n - b) + curr.substring(0, n - b);
            if (!seen.contains(rotateStr)) {
                seen.add(rotateStr);
                queue.offer(rotateStr);
            }
        }
        return ans;
    }
}