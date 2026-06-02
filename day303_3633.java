class Solution {
    public int earliestFinishTime(int[] lst, int[] ldu,
                                 int[] wst, int[] wdu) {

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < lst.length; i++) {
            int t = lst[i] + ldu[i];

            for (int j = 0; j < wst.length; j++) {
                ans = Math.min(ans, Math.max(t, wst[j]) + wdu[j]);
            }
        }

        for (int i = 0; i < wst.length; i++) {
            int t = wst[i] + wdu[i];

            for (int j = 0; j < lst.length; j++) {
                ans = Math.min(ans, Math.max(t, lst[j]) + ldu[j]);
            }
        }

        return ans;
    }
}