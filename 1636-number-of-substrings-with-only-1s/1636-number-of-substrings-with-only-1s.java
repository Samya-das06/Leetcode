class Solution {
    public int numSub(String s) {
		char[] chars = s.toCharArray();
		long ans = 0, count = 0;
		for (char c : chars) {
			if (c == '1') {
				count++;
			} else {
				ans += count * (count + 1) / 2;
				count = 0;
			}
		}
		ans += count * (count + 1) / 2;
		return (int) (ans % 1000000007);
	}
}