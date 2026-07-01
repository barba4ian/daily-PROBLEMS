class Solution {
    public int maximumSafenessFactor(List<List<Integer>> g) {
        int n = g.size();
        if (g.get(n - 1).get(n - 1) == 1 || g.get(0).get(0) == 1) {
            return 0;
        }

        int[][] d = dist(g);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        boolean[][] v = new boolean[n][n];

        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        pq.offer(new int[]{d[0][0], 0, 0});
        v[0][0] = true;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int s = cur[0], i = cur[1], j = cur[2];

            if (i == n - 1 && j == n - 1) {
                return s;
            }

            for (int k = 0; k < 4; k++) {
                int ni = i + dr[k];
                int nj = j + dc[k];
                if (ok(ni, nj, n) && g.get(ni).get(nj) != 1 && !v[ni][nj]) {
                    v[ni][nj] = true;
                    pq.offer(new int[]{Math.min(s, d[ni][nj]), ni, nj});
                }
            }
        }

        return 0;
    }

    private boolean ok(int x, int y, int n) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    private int[][] dist(List<List<Integer>> g) {
        int n = g.size();
        int[][] d = new int[n][n];
        for (int[] row : d) Arrays.fill(row, Integer.MAX_VALUE);

        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (g.get(i).get(j) == 1) {
                    q.offer(new int[]{i, j});
                    d[i][j] = 0;
                }
            }
        }

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];

            for (int k = 0; k < 4; k++) {
                int nx = x + dr[k];
                int ny = y + dc[k];
                if (ok(nx, ny, n) && d[nx][ny] == Integer.MAX_VALUE) {
                    d[nx][ny] = d[x][y] + 1;
                    q.offer(new int[]{nx, ny});
                }
            }
        }

        return d;
    }
}