
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int max=-1,min=Integer.MAX_VALUE;
        int l1=-1,r=1,l2=-1;
        ListNode temp=head.next;
        int prev=head.val;
        while(temp!=null && temp.next!=null){
            r++;
             if((temp.val>prev && temp.val>temp.next.val) || (temp.val<prev && temp.val<temp.next.val)){
                if(l1==-1 && l2==-1){
                    l1=r;
                    l2=r;
                }
                max=r-l1;
                if(l1!=r)min=Math.min(min,r-l2);
                l2=r;
             }
             prev=temp.val;
             temp=temp.next;
        }
        if(min==Integer.MAX_VALUE) return new int[]{-1,-1};
        return new int[]{min,max};
    }
}