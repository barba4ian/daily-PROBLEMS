class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int maxsumending = nums[0];
        int maxsum = nums[0];
        int sum = nums[0];
        int minsumending = nums[0];
        int minsum = nums[0];
        int ans = 0;

        for(int i = 1;i<nums.length;i++){
            int v1 = nums[i];
            int v2 = maxsumending + nums[i];
            int v3 = minsumending + nums[i];

            maxsumending = Math.max(v1,v2);
            minsumending = Math.min(v1,v3);

            maxsum = Math.max(maxsum,maxsumending);
            minsum = Math.min(minsum,minsumending);
            sum = sum+nums[i];
        }
        if(maxsum<0) return maxsum;
        ans = Math.max(sum-minsum,maxsum);
        return ans;
    }
}