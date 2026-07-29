class Solution {
    public String smallestPalindrome(String s, int k) {
        int n = s.length();
        int[] count = new int[26];
        for (char c : s.toCharArray()) count[c - 'a']++;

        int oddIdx = -1;
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) oddIdx = i;
        }

        int[] half = new int[26];
        int halfLen = 0;
        for (int i = 0; i < 26; i++) {
            half[i] = count[i] / 2;
            halfLen += half[i];
        }

        long LIMIT = 3_000_000_000L;

        if (countArrangements(half, halfLen, LIMIT) < k)
            return "";

        StringBuilder left = new StringBuilder();
        long need = k;
        int remain = halfLen;

        for (int pos = 0; pos < halfLen; pos++) {

            for (int c = 0; c < 26; c++) {

                if (half[c] == 0) continue;

                half[c]--;

                long ways = countArrangements(half, remain - 1, LIMIT);

                if (need <= ways) {
                    left.append((char) ('a' + c));
                    break;
                }

                need -= ways;
                half[c]++;
            }

            remain--;
        }

        StringBuilder ans = new StringBuilder();

        ans.append(left);

        if (oddIdx != -1)
            ans.append((char) ('a' + oddIdx));

        ans.append(new StringBuilder(left).reverse());

        return ans.toString();
    }

    private long countArrangements(int[] cnt, int total, long LIMIT) {

        long result = 1;
        int remaining = total;

        for (int i = 0; i < 26; i++) {

            int c = cnt[i];

            if (c == 0) continue;

            long comb = 1;

            for (int j = 1; j <= c; j++) {

                comb = comb * (remaining - c + j) / j;

                if (comb > LIMIT)
                    return LIMIT + 1;
            }

            result *= comb;

            if (result > LIMIT)
                return LIMIT + 1;

            remaining -= c;
        }

        return result;
    }
}