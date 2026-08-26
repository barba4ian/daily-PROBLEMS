class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int len = Integer.MAX_VALUE;
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            int curr_count = 0;
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(j) == '1')
                    curr_count++;
                if (curr_count == k && ((j - i + 1) <= len)) {
                    String new_Str = s.substring(i, j + 1);
                    if ((j - i + 1) == len) {
                        if (new_Str.compareTo(res) < 0) {
                            len = (j - i + 1);
                            res = new_Str;
                        }
                    } else {
                        len = (j - i + 1);
                        res = s.substring(i, j + 1);
                    }

                    break;
                }
            }
        }
        // System.out.println("len "+ len);
        return res;
    }
}