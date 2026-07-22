class Solution {
    static { Thread.currentThread().setPriority(Thread.MAX_PRIORITY); }

    int[] zs, ze, V;
    int[][] sp;
    int m;

    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] qs) {
        int n = s.length(), ones = 0;
        int[] zsA = new int[n], zeA = new int[n];
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') { ones++; continue; }
            int j = i;
            while (j < n && s.charAt(j) == '0') j++;
            zsA[m] = i; zeA[m] = j - 1; m++;
            i = j - 1;
        }
        zs = Arrays.copyOf(zsA, m);
        ze = Arrays.copyOf(zeA, m);
        int vn = Math.max(m - 1, 0);
        V = new int[vn];
        for (int j = 0; j < vn; j++)
            V[j] = (ze[j]-zs[j]+1) + (ze[j+1]-zs[j+1]+1);
        build();
        List<Integer> ans = new ArrayList<>(qs.length);
        for (int[] q : qs) ans.add(ones + gain(q[0], q[1]));
        return ans;
    }

    void build() {
        int n = V.length, log = n == 0 ? 1 : 32 - Integer.numberOfLeadingZeros(n);
        sp = new int[log][];
        sp[0] = V;
        for (int k = 1, half = 1; half * 2 <= n; k++, half *= 2) {
            int[] p = sp[k-1];
            int[] c = new int[p.length - half];
            for (int i = 0; i < c.length; i++) c[i] = Math.max(p[i], p[i+half]);
            sp[k] = c;
        }
    }

    int rmq(int lo, int hi) {
        int k = 31 - Integer.numberOfLeadingZeros(hi - lo + 1);
        return Math.max(sp[k][lo], sp[k][hi - (1<<k) + 1]);
    }

    int clip(int j, int l, int r) {
        return V[j] - Math.max(0, l - zs[j]) - Math.max(0, ze[j+1] - r);
    }

    int gain(int l, int r) {
        if (m < 2) return 0;
        int a = lb(ze, l), b = ub(zs, r) - 2;
        if (a > b) return 0;
        int best = Math.max(clip(a, l, r), clip(b, l, r));
        if (b - a >= 2) best = Math.max(best, rmq(a+1, b-1));
        return best;
    }

    static int lb(int[] a, int x) {
        int lo=0, hi=a.length;
        while (lo<hi) { int mid=(lo+hi)>>>1; if (a[mid]<x) lo=mid+1; else hi=mid; }
        return lo;
    }
    static int ub(int[] a, int x) {
        int lo=0, hi=a.length;
        while (lo<hi) { int mid=(lo+hi)>>>1; if (a[mid]<=x) lo=mid+1; else hi=mid; }
        return lo;
    }
}