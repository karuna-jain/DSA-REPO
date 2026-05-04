import java.util.*;

public class NQueen {

    // Check if placing a queen at (row, col) is safe
    public boolean isSafe(int row, int col, int n, int board[][]) {

        // 🔹 check same column (only above rows)
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 1)
                return false;
        }

        // 🔹 upper-left diagonal ↖
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1)
                return false;
        }

        // 🔹 upper-right diagonal ↗
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 1)
                return false;
        }

        return true;
    }

    public void helper(int n, int board[][], int row, ArrayList<ArrayList<String>> res) {

        // 🔹 base case: one valid configuration
        if (row == n) {
            ArrayList<String> temp = new ArrayList<>();

            // convert board -> list of strings
            for (int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == 1)
                        sb.append("Q");
                    else
                        sb.append(".");
                }
                temp.add(sb.toString());
            }

            res.add(temp);
            return;
        }

        // 🔹 try all columns for current row
        for (int col = 0; col < n; col++) {

            if (isSafe(row, col, n, board)) {
                board[row][col] = 1; // place queen
                helper(n, board, row + 1, res); // next row
                board[row][col] = 0; // backtrack
            }
        }
    }

    public ArrayList<ArrayList<String>> solveNQueen(int n) {

        ArrayList<ArrayList<String>> res = new ArrayList<>();
        int board[][] = new int[n][n];

        helper(n, board, 0, res);
        return res;
    }

    public static void main(String[] args) {
        NQueen obj = new NQueen();
        ArrayList<ArrayList<String>> ans = obj.solveNQueen(4);

        for (ArrayList<String> board : ans) {
            for (String row : board) {
                System.out.println(row);
            }
            System.out.println();
        }
    }
}