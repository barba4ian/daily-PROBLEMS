class Solution {
    public int missingMultiple(int[] nums, int k) {
        HashSet<Integer> set = new HashSet<>();

        for(int i: nums){
            set.add(i);
        }
        
        int mul = k;
        while(set.contains(mul)){
            mul += k;
        }

        return mul;
    }
}