class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        if (n <= 1) return s;

        int st = 0; // start index of palindrome
        int max = 1; // length of palindrome

        for (int i = 0; i < n; i++) {
            // odd length palindrome
            int l = i, r = i;
            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
                if (r - l + 1 > max) {
                    st = l;
                    max = r - l + 1;
                }
                l--;
                r++;
            }

            // even length palindrome
            l = i;
            r = i + 1;
            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
                if (r - l + 1 > max) {
                    st = l;
                    max = r - l + 1;
                }
                l--;
                r++;
            }
        }

        return s.substring(st, st + max);
    }
}