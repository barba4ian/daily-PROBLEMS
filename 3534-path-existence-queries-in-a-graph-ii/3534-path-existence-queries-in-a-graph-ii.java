import java.util.*;

class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);

        int[] val = new int[n]; // sorted values

        int[] pos = new int[n];

        for (int i = 0; i < n; i++) {
            val[i] = arr[i][0];
            pos[arr[i][1]] = i;
        }

        // component id
        int[] comp = new int[n];
        for (int i = 1; i < n; i++)
            comp[i] = (val[i] - val[i - 1] <= maxDiff) ? comp[i - 1] : comp[i - 1] + 1;

        int[] far = new int[n];
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (j < i)
                j = i;
            while (j + 1 < n && val[j + 1] - val[i] <= maxDiff)
                j++;
            far[i] = j;
        }

        // binary lifting table
        int LOG = 1;
        while ((1 << LOG) < n)
            LOG++;
        LOG++;
        int[][] up = new int[LOG][n];
        up[0] = far;
        for (int k = 1; k < LOG; k++)
            for (int i = 0; i < n; i++)
                up[k][i] = up[k - 1][up[k - 1][i]];

        int q = queries.length;
        int[] ans = new int[q];
        for (int t = 0; t < q; t++) {
            int u = queries[t][0], v = queries[t][1];
            if (u == v) {
                ans[t] = 0;
                continue;
            }

            int pu = pos[u], pv = pos[v];
            if (pu > pv) {
                int tmp = pu;
                pu = pv;
                pv = tmp;
            }

            if (comp[pu] != comp[pv]) {
                ans[t] = -1;
                continue;
            }

            int cur = pu, steps = 0;
            for (int k = LOG - 1; k >= 0; k--) {
                if (up[k][cur] < pv) {
                    cur = up[k][cur];
                    steps += (1 << k);
                }
            }
            if (cur < pv)
                steps++;
            ans[t] = steps;
        }
        return ans;
    }
}