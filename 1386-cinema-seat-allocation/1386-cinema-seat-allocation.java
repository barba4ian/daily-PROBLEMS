import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> rowMasks = new HashMap<>();

        // Track reservations for seats 2 through 9 in a bitmask per row
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];
            if (col >= 2 && col <= 9) {
                rowMasks.put(row, rowMasks.getOrDefault(row, 0) | (1 << col));
            }
        }

        // Bitmasks representing four-seat blocks
        int leftMask   = (1 << 2) | (1 << 3) | (1 << 4) | (1 << 5);   // Seats 2, 3, 4, 5
        int middleMask = (1 << 4) | (1 << 5) | (1 << 6) | (1 << 7);   // Seats 4, 5, 6, 7
        int rightMask  = (1 << 6) | (1 << 7) | (1 << 8) | (1 << 9);   // Seats 6, 7, 8, 9

        // Unreserved rows can each accommodate 2 families
        int maxFamilies = (n - rowMasks.size()) * 2;

        // Process rows that have relevant reserved seats
        for (int mask : rowMasks.values()) {
            boolean leftFree   = (mask & leftMask) == 0;
            boolean rightFree  = (mask & rightMask) == 0;
            boolean middleFree = (mask & middleMask) == 0;

            if (leftFree && rightFree) {
                maxFamilies += 2;
            } else if (leftFree || rightFree || middleFree) {
                maxFamilies += 1;
            }
        }

        return maxFamilies;
    }
}