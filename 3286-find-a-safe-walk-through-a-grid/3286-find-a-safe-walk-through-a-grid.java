class Solution {
    boolean visited[][][];

    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        visited = new boolean[grid.size()][grid.get(0).size()][health + 1];
        boolean res = dfs(grid, health, 0, 0);
        return res;
    }

    private boolean dfs(List<List<Integer>> grid, int health, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.size() || j >= grid.get(i).size() || visited[i][j][health]) {
            return false;
        }
        visited[i][j][health] = true;
        if (grid.get(i).get(j) == 1) {
            health--;
        }
        if (health < 1) {
            return false;
        }
        if (i == grid.size() - 1 && j == grid.get(i).size() - 1) {
            return true;
        }
        boolean left = dfs(grid, health, i, j - 1);
        boolean right = dfs(grid, health, i, j + 1);
        boolean top = dfs(grid, health, i - 1, j);
        boolean bottom = dfs(grid, health, i + 1, j);
        return left || right || top || bottom;
    }
}