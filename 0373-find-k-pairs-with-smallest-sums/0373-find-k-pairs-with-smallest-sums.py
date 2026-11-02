import heapq
class Solution:
    def kSmallestPairs(self, a, b, k):
        if not a or not b: return []
        pq=[(a[i]+b[0],i,0) for i in range(min(len(a),k))]
        heapq.heapify(pq); res=[]
        while k>0 and pq:
            s,i,j=heapq.heappop(pq)
            res.append([a[i],b[j]]); k-=1
            if j+1<len(b): heapq.heappush(pq,(a[i]+b[j+1],i,j+1))
        return res