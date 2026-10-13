# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def maxProduct(self, root: Optional[TreeNode]) -> int:
        a = []
        def dfs(node):
            if not node:
                return 0
            l=dfs(node.left)
            r=dfs(node.right)
            t=node.val+l+r
            a.append(t)  
            return t
        t=dfs(root) 
        m=0
        for i in a:
            c=(t - i)*i
            m=max(m,c)
        return m %(10**9+7)