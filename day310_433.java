class Solution {
    public int minMutation(String startGene, String endGene, String[] bank) {
        Set<String> geneBank = new HashSet<>(Arrays.asList(bank));
        if (!geneBank.contains(endGene)) {
            return -1;
        }

        char[] genes = {'A', 'C', 'G', 'T'};
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add(startGene);
        visited.add(startGene);
        int mutations = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String current = queue.poll();
                if (current.equals(endGene)) {
                    return mutations;
                }
                char[] currentArray = current.toCharArray();
                for (int j = 0; j < currentArray.length; j++) {
                    char originalChar = currentArray[j];
                    for (char gene : genes) {
                        if (gene == originalChar) continue;
                        currentArray[j] = gene;
                        String mutatedGene = new String(currentArray);
                        if (geneBank.contains(mutatedGene) && !visited.contains(mutatedGene)) {
                            queue.add(mutatedGene);
                            visited.add(mutatedGene);
                        }
                    }
                    currentArray[j] = originalChar; // revert change
                }
            }
            mutations++;
        }
        return -1; 
    }
}