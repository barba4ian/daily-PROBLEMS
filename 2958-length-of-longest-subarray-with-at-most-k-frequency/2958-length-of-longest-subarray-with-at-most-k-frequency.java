class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        int n = nums.length;
        int[] coords = nums.clone();
        Arrays.sort(coords);
        int m = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0 || coords[i] != coords[i - 1]) coords[m++] = coords[i];
        }
        
        int[] freq = new int[m];
        int left = 0, ans = 0;
        
        for (int right = 0; right < n; right++) {
            int idx = Arrays.binarySearch(coords, 0, m, nums[right]);
            freq[idx]++;
            
            while (freq[idx] > k) {
                int leftIdx = Arrays.binarySearch(coords, 0, m, nums[left]);
                freq[leftIdx]--;
                left++;
            }
            
            ans = Math.max(ans, right - left + 1);
        }
        
        return ans;
    }
}