class Solution:
    def rob(self, nums: List[int]) -> int:
        prev1, prev2 = 0, 0
        for cash in nums:
            cur = max(prev1, prev2 + cash )
            prev1, prev2 = cur, prev1
        return cur