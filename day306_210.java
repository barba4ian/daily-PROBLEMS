class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for(int i=0;i<numCourses;i++){
            list.add(new ArrayList<Integer>());
        }

        for(int i[] : prerequisites){
            list.get(i[1]).add(i[0]);
        }

        int[] inDegree = new int[numCourses];

        for(ArrayList<Integer> adj: list){
            for(int i:adj){
                inDegree[i]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i=0;i<numCourses;i++){
            if(inDegree[i]==0){
                queue.add(i);
            }
        }

        int count =0;
        int res[] = new int[numCourses];
        int k=0;
        while(!queue.isEmpty()){
            int temp = queue.poll();
            ArrayList<Integer> adj = list.get(temp);
            count++;
            res[k++]= temp;
            for(int i=0;i<adj.size();i++){
                int val = adj.get(i);
                inDegree[val]--;
                if(inDegree[val]==0){
                    queue.add(val);
                }
            }
        }
        if(count==numCourses){
            return res;
        }
        return new int[0];
    }
}