class Solution {
    public int pairSum(ListNode head) {
        // Step 1: 
        ListNode yolo = head;
        ListNode holo = head;
        
        while (holo != null && holo.next != null) {
            yolo = yolo.next;
            holo = holo.next.next;
        }
        
        // Step 2  Reverse second half
        ListNode mid = yolo;  // second half
        ListNode prev = null;
        ListNode curr = mid;
        
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        
        // Step 3  Compare twins 
        ListNode first = head;
        ListNode second = prev;  // head of reversed second half
        int maxSum = 0;
        
        while (second != null) {
            int sum = first.val + second.val;
            maxSum = Math.max(maxSum, sum);
            first = first.next;
            second = second.next;
        }
        
        return maxSum;
    }
}