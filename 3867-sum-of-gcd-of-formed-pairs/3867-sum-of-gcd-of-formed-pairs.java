class Solution {

    private long g(long a, long b) {
        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }

        return a;
    }

    public long gcdSum(int[] a) {
        int n = a.length;
        int[] v = a;
        long[] p = new long[n];
        long m = 0;

        for (int i = 0; i < n; i++) {
            if (v[i] > m)
                m = v[i];
            p[i] = g(v[i], m);
        }

        Arrays.sort(p);

        long s = 0;

        int l = 0, r = n - 1;

        while (l < r) {
            s += g(p[l], p[r]);
            l++;
            r--;
        }

        return s;
    }
}