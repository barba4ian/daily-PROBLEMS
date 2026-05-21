class Solution {
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        Set<String> prefixes = new HashSet<>();

        for (int num : arr1) {
            String s = Integer.toString(num);
            for (int i = 1; i <= s.length(); i++)
                prefixes.add(s.substring(0, i));
        }

        int ans = 0;

        for (int num : arr2) {
            String s = Integer.toString(num);
            for (int i = 1; i <= s.length(); i++)
                if (prefixes.contains(s.substring(0, i)))
                    ans = Math.max(ans, i);
        }

        return ans;
    }
}