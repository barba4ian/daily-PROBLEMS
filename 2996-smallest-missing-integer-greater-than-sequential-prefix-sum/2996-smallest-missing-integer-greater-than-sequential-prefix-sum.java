class Solution {
    public int missingInteger(int[] nums) {

        // Step 1: Find the sum of the longest sequential prefix
        int sum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                sum += nums[i];
                continue;
            }

            // Sequential prefix has ended
            break;
        }

        // Step 2: Store all numbers for O(1) average lookup
        HashSet<Integer> set = new HashSet<>();

        for (int i : nums) {
            set.add(i);
        }

        // Step 3: Find the smallest missing integer
        while (set.contains(sum)) {
            sum++;
        }

        return sum;
    }
}