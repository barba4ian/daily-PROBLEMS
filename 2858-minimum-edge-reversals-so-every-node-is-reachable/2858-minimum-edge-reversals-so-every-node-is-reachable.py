class Solution:
    def minEdgeReversals(self, n: int, edges: List[List[int]]) -> List[int]:
        graph = {i : [] for i in range(n)}
        for u, v in edges:
            graph[u].append((v, 0))
            graph[v].append((u, 1))
        
        res = [0] * n

        def dfs1(node, par):
            total = 0
            for nei, cost in graph[node]:
                if nei == par:
                    continue
                total += cost + dfs1(nei, node)
            return total
        
        res[0] = dfs1(0, -1)

        def dfs2(node, par):
            for nei, cost in graph[node]:
                if nei == par:
                    continue
                if cost == 0:
                    res[nei] = res[node] + 1
                else:
                    res[nei] = res[node] - 1
                
                dfs2(nei, node)
        
        dfs2(0, -1)
        return res

        