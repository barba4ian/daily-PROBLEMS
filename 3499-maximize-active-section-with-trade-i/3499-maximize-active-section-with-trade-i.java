class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        char arr[] = s.toCharArray();
        int n=arr.length;
        int left=0;
        int right=0;
        int middle=0;
        int asum=0;
        int sum=0;
        int i=0;
        for(char c:arr){
            if(c=='1'){
                asum++;
            }
        }
        while(i<n){
            left=0;
            while(i<n && arr[i]=='1'){
                i++;
            }
            while(i<n && arr[i]=='0'){
                left++;
                i++;
            }
            middle=0;
            while(i<n && arr[i]=='1'){
                middle++;
                i++;
            }
            right=0;
            while(i<n && arr[i]=='0'){
                right++;
                i++;
            }
            if(i>=n && left==0 ){
                return Math.max(sum,asum);
            }
            if(i>=n && right==0 ){
                return Math.max(sum,asum);
            }
            sum=Math.max(sum,asum+left+right);
            i=i-right;
            
        }
        
        return sum;
    }
}