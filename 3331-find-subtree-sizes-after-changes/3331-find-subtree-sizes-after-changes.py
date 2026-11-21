class Solution:
    def findSubtreeSizes(self, parent: List[int], s: str) -> List[int]:
        n = len(parent)
        adj = [[] for _ in range(n)]
        for i in range(1, n):
            adj[parent[i]].append(i)

        new_parent = list(parent)
        char_ancestor = [-1] * 26

        def dfs1(u: int) -> None:
            c = ord(s[u]) - ord('a')
            prev = char_ancestor[c]
            if prev != -1:
                new_parent[u] = prev
            char_ancestor[c] = u

            for v in adj[u]:
                dfs1(v)

            char_ancestor[c] = prev

        dfs1(0)

        new_adj = [[] for _ in range(n)]
        for i in range(1, n):
            new_adj[new_parent[i]].append(i)

        subtree_size = [0] * n

        def dfs2(u: int) -> int:
            sz = 1
            for v in new_adj[u]:
                sz += dfs2(v)
            subtree_size[u] = sz
            return sz

        dfs2(0)
        return subtree_size