import heapq

class Solution:
    def isPossible(self, target: List[int]) -> bool:
        heapq.heapify_max(target)

        cur_sum = 0
        for num in target:
            cur_sum += num

        if len(target) == 1:
            return True if cur_sum == 1 else False

        while True:

            old_val = heapq.heappop_max(target)
            if old_val < cur_sum - old_val:
                return False

            new_val = old_val % (cur_sum - old_val)
            if new_val == 0:
                return False if cur_sum - old_val > 1 else True

            cur_sum = cur_sum - old_val + new_val
            heapq.heappush_max(target, new_val)
            if cur_sum == len(target):
                return True