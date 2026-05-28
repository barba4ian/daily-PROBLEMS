class Solution {
    class TrieNode{
        TrieNode[] child;
        int idx;

        TrieNode(int idx){
            child = new TrieNode[26];
            this.idx = idx;
        }
    }

    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {

       
        int mini = 0, minlen = wordsContainer[0].length();

        for(int i = 1; i < wordsContainer.length; i++){
            if(wordsContainer[i].length() < minlen){
                mini = i;
                minlen = wordsContainer[i].length();
            }
        }

       
        TrieNode root = new TrieNode(mini);

    
        for(int i = 0; i < wordsContainer.length; i++){
            insert(wordsContainer, root, wordsContainer[i], i);
        }

    
        int res[] = new int[wordsQuery.length];

        for(int i = 0; i < wordsQuery.length; i++){
            res[i] = query(root, wordsQuery[i]);
        }

        return res;
    }

   
    public int query(TrieNode root, String word){

        TrieNode node = root;
        int n = word.length();

        for(int i = n - 1; i >= 0; i--){

            char ch = word.charAt(i);

           
            if(node.child[ch - 'a'] == null){
                return node.idx;
            }

            node = node.child[ch - 'a'];
        }

        return node.idx;
    }


    public void insert(String[] wordsContainer,
                       TrieNode root,
                       String word,
                       int index){

        TrieNode node = root;
        int n = word.length();

        for(int i = n - 1; i >= 0; i--){

            char ch = word.charAt(i);

            if(node.child[ch - 'a'] == null){
                node.child[ch - 'a'] = new TrieNode(index);
            }

            node = node.child[ch - 'a'];

           
            if(wordsContainer[node.idx].length() <= n)
                continue;

            node.idx = index;
        }
    }
}