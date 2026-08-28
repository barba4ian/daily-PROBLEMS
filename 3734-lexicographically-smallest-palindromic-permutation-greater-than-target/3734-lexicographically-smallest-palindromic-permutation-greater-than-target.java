class Solution {
    String ans = "";
    Map<String, Boolean> memo = new HashMap<>();

    int check(int[] map) {
        int index = -1, oddCount = 0;
        for (int i = 0; i < 26; ++i) {
            if (map[i] % 2 != 0) {
                oddCount++;
                index = i;
            }
        }
        return (oddCount == 1) ? index : -1;
    }

    boolean helper(StringBuilder first, StringBuilder second, String target, int[] map, int index, boolean isGreater) {
        if (index >= (target.length() + 1) / 2) {
            StringBuilder rev = new StringBuilder(second).reverse();
            String candidate = first.toString() + rev.toString();
            if (candidate.compareTo(target) > 0) {
                ans = candidate;
                return true;
            }
            return false;
        }

        String key = index + "," + isGreater + "," + Arrays.toString(map);
        if (memo.containsKey(key)) return memo.get(key);

        int oddIdx = check(map);

        if (target.length() % 2 == 1 && index == target.length() / 2 && oddIdx != -1) {
            first.append((char)(oddIdx + 'a'));
            map[oddIdx]--;
            boolean res = helper(first, second, target, map, index + 1, isGreater);
            map[oddIdx]++;
            first.deleteCharAt(first.length() - 1);
            memo.put(key, res);
            return res;
        }

        char targetChar = target.charAt(index);

        for (int i = 0; i < 26; ++i) {
            if (map[i] < 2) continue;
            char ch = (char)(i + 'a');

            if (!isGreater && ch < targetChar) continue;

            boolean nextGreater = isGreater || (ch > targetChar);

            first.append(ch);
            second.append(ch);
            map[i] -= 2;

            if (helper(first, second, target, map, index + 1, nextGreater)) {
                memo.put(key, true);
                map[i] += 2;
                first.deleteCharAt(first.length() - 1);
                second.deleteCharAt(second.length() - 1);
                return true;
            }

            map[i] += 2;
            first.deleteCharAt(first.length() - 1);
            second.deleteCharAt(second.length() - 1);
        }

        memo.put(key, false);
        return false;
    }

    public String lexPalindromicPermutation(String s, String target) {
        int[] map = new int[26];
        for (char c : s.toCharArray()) map[c - 'a']++;

        int odd = 0;
        for (int c : map) if (c % 2 != 0) odd++;
        if (odd > 1) return ""; 

        ans = "";
        memo.clear();

        helper(new StringBuilder(), new StringBuilder(), target, map, 0, false);
        return ans;
    }
}