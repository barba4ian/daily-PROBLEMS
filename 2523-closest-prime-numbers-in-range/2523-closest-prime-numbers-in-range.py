class Solution:
    p = sorted({*range(2,1000001)}-{p*q for p in range(2,1001) for q in range(2,1000000//p+1)})
    def closestPrimes(self, l: int, r: int) -> List[int]:
        a = self.p[bisect_left(self.p,l):bisect_right(self.p,r)]
        return max(pairwise(a),key=lambda p:sub(*p),default=(-1,-1))