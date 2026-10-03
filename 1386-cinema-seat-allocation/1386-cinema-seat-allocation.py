class Solution:
    def maxNumberOfFamilies(self, n: int, reservedSeats: List[List[int]]) -> int:
        rows = {}

        for r, s in reservedSeats:
            rows[r] = rows.get(r, 0) | (1 << s)

        ans = (n - len(rows)) * 2

        for mask in rows.values():
            left = not (mask & 0b00111100)
            middle = not (mask & 0b11110000)
            right = not (mask & 0b1111000000)

            if left and right:
                ans += 2
            elif left or middle or right:
                ans += 1

        return ans