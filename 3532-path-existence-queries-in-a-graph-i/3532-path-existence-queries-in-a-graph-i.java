class Solution {
    class UnionFind{
        int[] parent;
        public UnionFind(int n){
            parent = new int[n];
            for(int i=0;i<n;i++) parent[i] = i;
        }
        public int prtFnd(int ch){
            if(parent[ch] == ch) return ch;
            return parent[ch] = prtFnd(parent[ch]);
        }
        public void union(int ch1,int ch2){
            int prt1 = prtFnd(ch1);
            int prt2 = prtFnd(ch2);
            parent[prt1] = prt2;
        }
    }
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int len = queries.length;
        UnionFind uf = new UnionFind(n);
        for(int i = 0;i < n-1;i++){
            if(Math.abs(nums[i]-nums[i+1]) <= maxDiff){
                uf.union(i, i+1);
            }
        }

        boolean[] res = new boolean[len];
        for(int i = 0;i < len;i++){
            res[i] = uf.prtFnd(queries[i][0]) == uf.prtFnd(queries[i][1]);
        }
        
        return res;
    }
}