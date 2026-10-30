class Solution:
    def timeRequiredToBuy(self, t: List[int], k: int) -> int:
        return sum(min(v, t[k] - (i > k)) for i, v in enumerate(t))