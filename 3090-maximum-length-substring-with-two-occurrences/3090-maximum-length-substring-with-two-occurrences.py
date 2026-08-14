class Solution(object):
    def maximumLengthSubstring(self, s):
        """
        :type s: str
        :rtype: int
        """
        cnt = {}
        l = 0
        ans = 0

        for r in range(len(s)):
            c = s[r]
            cnt[c] = cnt.get(c, 0) + 1

            while cnt[c] > 2:
                lc = s[l]
                cnt[lc] -= 1
                l += 1

            ans = max(ans, r - l + 1)

        return ans