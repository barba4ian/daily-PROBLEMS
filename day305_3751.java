class Solution {
    public int totalWaviness(int num1, int num2) {
        int sum = 0;
        for (int n = num1; n <= num2; n++) {
            sum += getWave(n);
        }
        return sum;
    }
    
    private int getWave(int n) {
        String s = String.valueOf(n);
        int len = s.length();
        
        if (len < 3) return 0;
        
        int w = 0;
        for (int i = 1; i < len - 1; i++) {
            int prev = s.charAt(i - 1) - '0';
            int curr = s.charAt(i) - '0';
            int next = s.charAt(i + 1) - '0';
            
            // Peak: curr > both neighbors
            if (curr > prev && curr > next) {
                w++;
            }
            // Valley: curr < both neighbors
            else if (curr < prev && curr < next) {
                w++;
            }
        }
        
        return w;
    }
}