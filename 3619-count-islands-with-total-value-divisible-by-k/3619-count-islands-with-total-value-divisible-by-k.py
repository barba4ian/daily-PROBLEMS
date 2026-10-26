class Solution:
    def countIslands(self, grid: List[List[int]], k: int) -> int:
        m = len(grid)
        n = len(grid[0])
        res = 0
        visited = [[False] * n for _ in range(m)]

        directions = [(0, 1), (1, 0), (0, -1), (-1, 0)]

        for i in range(m):
            for j in range(n):
                if grid[i][j] > 0 and not visited[i][j]:
                    q = deque([(i, j)])
                    visited[i][j] = True
                    curSum = 0

                    while q:
                        r, c = q.popleft()
                        curSum += grid[r][c]

                        for dr, dc in directions:
                            nr, nc = r + dr, c + dc
                            if 0 <= nr < m and 0 <= nc < n and grid[nr][nc] > 0 and not visited[nr][nc]:
                                visited[nr][nc] = True
                                q.append((nr, nc))
                    
                    if curSum % k == 0:
                        res += 1
        return res

