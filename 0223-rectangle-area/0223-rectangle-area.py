class Solution:
    def computeArea(self, ax1: int, ay1: int, ax2: int, ay2: int, bx1: int, by1: int, bx2: int, by2: int) -> int:
        area_of_first = (ax2 - ax1) * (ay2 - ay1)
        area_of_second = (bx2 - bx1) * (by2 - by1)

        width = min(ax2, bx2) - max(ax1, bx1)
        height = min(ay2, by2) - max(ay1, by1)

        overlapping_area = max(0, width) * max(0, height)

        return area_of_first + area_of_second - overlapping_area