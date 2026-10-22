from typing import List

class Solution:
    def minOperations(self, nums: List[int]) -> int:
        n = len(nums)
        if all(nums[i] == i for i in range(n)):
            return 0
            
        idx0 = nums.index(0)
        
        if all(nums[(idx0 + i) % n] == i for i in range(n)):
            return min(idx0, (n - idx0) + 2)
            
        if all(nums[(idx0 - i) % n] == i for i in range(n)):
            c1 = 1 + (n - 1 - idx0)
            c2 = 1 + ((idx0 + 1) % n)
            return min(c1, c2)
            
        return -1