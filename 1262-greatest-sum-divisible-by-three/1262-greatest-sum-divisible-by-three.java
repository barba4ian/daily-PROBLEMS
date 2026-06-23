class Solution {
    public int maxSumDivThree(int[] nums) {
        int[] dp = new int[3];  
        
        for (int n : nums) {
            int[] tmp = dp.clone();
            for (int i = 0; i < 3; i++) {
                int sum = tmp[i] + n;
                int r = sum % 3;
                dp[r] = Math.max(dp[r], sum);
            }
        }
        
        return dp[0];
    }
}