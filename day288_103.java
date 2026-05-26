class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);
        boolean ltr = true;
        while (!que.isEmpty()) {
            int size = que.size();
            LinkedList<Integer> level = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = que.poll();
                if (ltr) level.addLast(node.val);
                else level.addFirst(node.val);
                if (node.left != null) que.offer(node.left);
                if (node.right != null) que.offer(node.right);
            }
            result.add(level);
            ltr = !ltr;
        }
        return result;
    }
}