class Solution:
    def minSumSquareDiff(self, nums1: list[int], nums2: list[int], k1: int, k2: int) -> int:
        diffs = [abs(a - b) for a, b in zip(nums1, nums2)]
        k = k1 + k2

        if sum(diffs) <= k:
            return 0

        left, right = 0, max(diffs)

        while left < right:
            mid = (left + right) // 2
            needed = sum(max(0, d - mid) for d in diffs)

            if needed <= k:
                right = mid
            else:
                left = mid + 1

        x = left
        remaining = k

        for i in range(len(diffs)):
            reduction = max(0, diffs[i] - x)
            diffs[i] -= reduction
            remaining -= reduction

        for i in range(len(diffs)):
            if remaining == 0:
                break
            if diffs[i] == x:
                diffs[i] -= 1
                remaining -= 1

        return sum(d * d for d in diffs)
