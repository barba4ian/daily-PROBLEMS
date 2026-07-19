class Solution {
    public String smallestSubsequence(String s) {
        int[] last = new int[26];
        boolean[] visited = new boolean[26];

        for (int i = 0; i < s.length(); i++) {
            last[s.charAt(i) - 'a'] = i;
        }

        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (visited[ch - 'a']) continue;

            while (ans.length() > 0 &&
                   ans.charAt(ans.length() - 1) > ch &&
                   last[ans.charAt(ans.length() - 1) - 'a'] > i) {
                visited[ans.charAt(ans.length() - 1) - 'a'] = false;
                ans.deleteCharAt(ans.length() - 1);
            }

            ans.append(ch);
            visited[ch - 'a'] = true;
        }

        return ans.toString();
    }
}