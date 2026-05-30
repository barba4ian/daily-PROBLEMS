class Solution {
    public int numIslands(char[][] grid) {
        int count = 0;

        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == '1'){
                    count++;
                    countIslands(grid, i, j);
                } 
            }
        }

        return count;
    }

    int direction[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public void countIslands(char[][] grid, int i, int j){
        grid[i][j] = '0';

        for(int k = 0; k < direction.length; k++){
            int newI = i + direction[k][0];
            int newJ = j + direction[k][1];

            if(!(newI < 0 || newI >= grid.length 
                || newJ < 0 || newJ >= grid[0].length 
                || grid[newI][newJ] == '0')){

                    countIslands(grid, newI, newJ);
            }
        }
    }
}