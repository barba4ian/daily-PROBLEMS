class Solution {
    public int subsequencePairCount(int[] nums) {

        final int MOD = 1_000_000_007;

        int max = 0;
        for (int i = 0; i < nums.length; i++)
            max = Math.max(max, nums[i]);

        int n = nums.length;
        int dp[][][] = new int[n][max + 1][max + 1];

        dp[0][nums[0]][0] = 1;
        dp[0][0][nums[0]] = 1;

        for (int i = 1; i < n; i++) {
            dp[i][nums[i]][0] = 1;
            dp[i][0][nums[i]] = 1;
            for (int j = 0; j <= max; j++) {
                for (int k = 0; k <= max; k++) {
                    if (dp[i - 1][j][k] > 0) {
                        int gcd1 = gcd(nums[i], j);
                        int gcd2 = gcd(nums[i], k);

                        dp[i][gcd1][k] += dp[i - 1][j][k];
                        dp[i][gcd1][k] %= MOD;

                        dp[i][j][gcd2] += dp[i - 1][j][k];
                        dp[i][j][gcd2] %= MOD;

                    }

                    dp[i][j][k] += dp[i - 1][j][k];
                    dp[i][j][k] %= MOD;
                }
            }
        }

        long total = 0;
        for (int j = 1; j <= max; j++) {
            total += dp[n - 1][j][j];
            total %= MOD;
        }

        return (int) total;

    }

    private int gcd(int a, int b) {
        if (a < b) {
            int temp = a;
            a = b;
            b = temp;
        }

        while (b != 0) {
            int n = a % b;
            a = b;
            b = n;
        }

        return a;
    }
}