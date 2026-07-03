class Solution {
    public int findMaxPathScore(int[][] edges, boolean[] on, long k) {
        List<Integer> val = new ArrayList<>();
        int n = on.length;

        List<List<long[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            int w = e[2];

            val.add(w);

            if (on[u] && on[v]) {
                adj.get(u).add(new long[] { v, w });
            }
        }

        Collections.sort(val);

        int l = 0, r = val.size() - 1;
        int ans = -1;

        while (l <= r) {
            int m = (l + r) / 2;
            int x = val.get(m);

            long d = dijkstra(adj, n, x);

            if (d <= k) {
                ans = x;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }

        return ans;
    }

    long dijkstra(List<List<long[]>> adj, int n, int lim) {
        long[] dis = new long[n];
        Arrays.fill(dis, Long.MAX_VALUE);

        dis[0] = 0;

        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));

        pq.add(new long[] { 0, 0 });

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();

            long d = cur[0];
            long u = cur[1];

            if (u == n - 1)
                return d;

            if (d != dis[(int) u])
                continue;

            for (long[] e : adj.get((int) u)) {
                long v = e[0];
                long w = e[1];

                if (w < lim)
                    continue;

                if (d + w < dis[(int) v]) {
                    dis[(int) v] = d + w;
                    pq.add(new long[] { d + w, v });
                }
            }
        }

        return dis[n - 1];
    }
}