class Solution:
    def projectionArea(self, grid: List[List[int]]) -> int:
        from_top = sum(v > 0 for row in grid for v in row)
        from_side = sum(max(row) for row in grid)
        from_front = sum(max(col) for col in zip(*grid))
        return from_top + from_side + from_front