class Solution {
    public int singleNumber(int[] nums) {
        Map<Integer,Integer>map=new HashMap<>();
        for(int val:nums){
            map.put(val,map.getOrDefault(val,0)+1);
        }
        for(Map.Entry<Integer,Integer>entrySet:map.entrySet()){
            if(entrySet.getValue()==1)return entrySet.getKey();
        }
        return -1;
    }
}