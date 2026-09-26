from collections import deque

class Solution:
    def orangesRotting(self, grid: List[List[int]]) -> int:
        ROWS, COLS = len(grid), len(grid[0])
        q = deque()
        fresh_oranges, minutes = 0, 0

       
        for r in range(ROWS):
            for c in range(COLS):
                if grid[r][c] == 2:
                    q.append((r, c))
                elif grid[r][c] == 1:
                    fresh_oranges += 1
        
        directions = [[1, 0], [-1, 0], [0, 1], [0, -1]]

       
        while q and fresh_oranges > 0:
            for _ in range(len(q)):
                r, c = q.popleft()
                for dr, dc in directions:
                    row, col = r + dr, c + dc
                    
                    if (0 <= row < ROWS and 0 <= col < COLS 
                        and grid[row][col] == 1):
                        grid[row][col] = 2
                        fresh_oranges -= 1
                        q.append((row, col))
            minutes += 1
        
        return minutes if fresh_oranges == 0 else -1