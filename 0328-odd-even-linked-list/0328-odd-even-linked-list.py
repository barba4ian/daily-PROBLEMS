class Solution:
    def oddEvenList(self, head: Optional[ListNode]) -> Optional[ListNode]:
        a = head
        if head is None or head.next is None:
            return head
        b = head.next
        c = b
        while a.next is not None and b.next is not None:
            a.next = a.next.next
            b.next = b.next.next
            if a.next is None:
                break
            a = a.next
            if b.next is None:
                break
            b = b.next
        b.next = None
        a.next = c
        return head