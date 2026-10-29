def largest_rectangle_area(heights):
    stack = []
    best = 0
    for i, current in enumerate(heights + [0]):
        while stack and heights[stack[-1]] >= current:
            height = heights[stack.pop()]
            left = stack[-1] + 1 if stack else 0
            best = max(best, height * (i - left))
        stack.append(i)
    return best


class Solution:
    def largestRectangleArea(self, heights: list[int]) -> int:
        return largest_rectangle_area(heights)