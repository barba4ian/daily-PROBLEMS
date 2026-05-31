class Solution {
    boolean[][] visited;
    int m, n;
    int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public void solve(char[][] board) {
        if (board == null || board.length == 0) return;
        m = board.length;
        n = board[0].length;
        visited = new boolean[m][n];

       
        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O' && !visited[0][j]) dfs(board, 0, j);
            if (board[m - 1][j] == 'O' && !visited[m - 1][j]) dfs(board, m - 1, j);
        }

       
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O' && !visited[i][0]) dfs(board, i, 0);
            if (board[i][n - 1] == 'O' && !visited[i][n - 1]) dfs(board, i, n - 1);
        }   

       
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(board[i][j] == 'O' && !visited[i][j]) {
                    board[i][j] = 'X';
                }
        
            }
        }
    }

    void dfs(char[][] board, int i, int j) {
        if (visited[i][j]) return;
        visited[i][j] = true;
        
        for (int[] dir : directions) {
            int newR = i + dir[0];
            int newC = j + dir[1];
            
            // Check bounds, ensure it's an 'O', and is unvisited
            if (newR >= 0 && newR < m && newC >= 0 && newC < n && 
                board[newR][newC] == 'O' && !visited[newR][newC]) {
                dfs(board, newR, newC);
            }
        }
    }
}