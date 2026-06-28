class Solution {
    static boolean searchIt(int mat[], int target) {
        int low = 0, high = mat.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (mat[mid] == target) {
                return true;
            } else if (mat[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return false;
    }

    public boolean searchMatrix(int[][] mat, int x) {
        int n = mat.length, m = mat[0].length;

        for (int i = 0; i < mat.length; i++) {
            if (searchIt(mat[i], x)) {
                return true;
            }
        }
        return false;
    }
}