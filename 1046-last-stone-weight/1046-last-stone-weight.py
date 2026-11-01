import heapq
class Solution:
    def lastStoneWeight(self, stones: List[int]) -> int:

        heap = [-stone for stone in stones]
        heapq.heapify(heap)

        while len(heap)>1:
            diff = heapq.heappop(heap)-heapq.heappop(heap)
            if diff!=0:
                heapq.heappush(heap, diff)
        
        return -heapq.heappop(heap) if heap else 0