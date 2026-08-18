import java.util.*;

class Solution {
    public int largestInteger(int[] nums, int k) {
        Map<Integer, Integer> windowCount = new HashMap<>();

        for (int start = 0; start <= nums.length - k; start++) {
            Set<Integer> seen = new HashSet<>();

            for (int i = start; i < start + k; i++) {
                seen.add(nums[i]);
            }

            for (int value : seen) {
                windowCount.put(value, windowCount.getOrDefault(value, 0) + 1);
            }
        }

        int answer = -1;

        for (Map.Entry<Integer, Integer> entry : windowCount.entrySet()) {
            if (entry.getValue() == 1) {
                answer = Math.max(answer, entry.getKey());
            }
        }

        return answer;
    }
}