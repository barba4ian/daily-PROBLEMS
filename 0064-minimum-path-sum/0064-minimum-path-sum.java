class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[] dp = new int[n];

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(i == 0 && j == 0){
                    dp[j] = grid[i][j];
                    continue;
                }
                int up = Integer.MAX_VALUE;
                int left = Integer.MAX_VALUE;

                if(i > 0) up = dp[j];
                if(j > 0) left = dp[j-1];

                dp[j] = grid[i][j] + Math.min(up,left);
            }
        }
        return dp[n-1];
    }
}