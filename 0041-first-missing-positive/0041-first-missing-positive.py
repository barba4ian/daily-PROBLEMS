class Solution:
    def firstMissingPositive(self, nums: list[int]) -> int:
        n = len(nums)

        # Step 1: Place each valid number at its correct index (value - 1)
        for i in range(n):
            while 1 <= nums[i] <= n and nums[nums[i] - 1] != nums[i]:
                crt_idx = nums[i] - 1
                nums[i], nums[crt_idx] = nums[crt_idx], nums[i]

        # Step 2: Find the first index where the value doesn't match index + 1
        for i in range(n):
            if nums[i] != i + 1:
                return i + 1

        # Step 3: All of 1..n are present, so the answer is n + 1
        return n + 1