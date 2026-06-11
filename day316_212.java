class TrieNode{
    TrieNode[] children = new TrieNode[26];
    boolean endOfWord = false;
}
class Solution {
    public void insertWord(TrieNode root,String word)
    {
        TrieNode current = root;
        for(char ch : word.toCharArray())
        {
            int index = ch-'a';
            if(current.children[index] == null)
                current.children[index] = new TrieNode();
            current = current.children[index];
        }
        current.endOfWord = true;
    }
    public TrieNode buildTrie(String[] words)
    {
        TrieNode root = new TrieNode();
        for(String word : words)
        {
            insertWord(root,word);
        }
        return root;
    }
    public void DFS(int row,int col,String word,char[][] board,TrieNode root, HashSet<String> hashSet,boolean[][] visited)
    {
        if(row < 0 || col < 0 || row == board.length || col == board[0].length || visited[row][col] == true || root.children[board[row][col]-'a'] == null )
            return;
        visited[row][col] = true;
        root = root.children[board[row][col]-'a'];
        word = word + board[row][col];
        if(root.endOfWord == true)
            hashSet.add(word);
        DFS(row+1,col,word,board,root,hashSet,visited);
        DFS(row-1,col,word,board,root,hashSet,visited);
        DFS(row,col+1,word,board,root,hashSet,visited);
        DFS(row,col-1,word,board,root,hashSet,visited);

        visited[row][col] = false;

    }
    public List<String> findWords(char[][] board, String[] words) {
        TrieNode root = buildTrie(words);
        boolean[][] visited = new boolean[board.length][board[0].length];
        HashSet<String> hashSet = new HashSet<>();
        
        for(int row = 0;row<board.length;row++)
        {
            for(int col = 0;col<board[0].length;col++)
            {
                DFS(row,col,"",board,root,hashSet,visited);
            }
        }
        return new ArrayList<>(hashSet);
    }
}