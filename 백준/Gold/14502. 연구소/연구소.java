import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] boardSize = br.readLine().split(" ");
        int row = Integer.parseInt(boardSize[0]);
        int col = Integer.parseInt(boardSize[1]);
        int[][] board = new int[row][col];
        List<Integer[]> voidPoint = new ArrayList<>();
        List<Integer[]> gasPoint = new LinkedList<>();
        int answer = Integer.MIN_VALUE;

        //연구실 생성
        for (int i = 0; i < row; i++) {
            String[] colums = br.readLine().split(" ");
            for (int j = 0; j < col; j++) {
                board[i][j] = Integer.parseInt(colums[j]);
                if (board[i][j] == 2) gasPoint.add(new Integer[]{i, j});
                if (board[i][j] == 0) voidPoint.add(new Integer[]{i, j});
            }
        }

        //벽생성
        for (int i = 0; i < voidPoint.size() - 2; i++) {
            board[voidPoint.get(i)[0]][voidPoint.get(i)[1]] = 1;
            for (int j = i + 1; j < voidPoint.size() - 1; j++) {
                board[voidPoint.get(j)[0]][voidPoint.get(j)[1]] = 1;
                for (int k = j + 1; k < voidPoint.size(); k++) {
                    board[voidPoint.get(k)[0]][voidPoint.get(k)[1]] = 1;
                    int currentSafetyAreaCount = gasgasgas(board, gasPoint);
                    if (answer < currentSafetyAreaCount) answer = currentSafetyAreaCount;
                    board[voidPoint.get(k)[0]][voidPoint.get(k)[1]] = 0;
                }
                board[voidPoint.get(j)[0]][voidPoint.get(j)[1]] = 0;
            }
            board[voidPoint.get(i)[0]][voidPoint.get(i)[1]] = 0;
        }

        System.out.printf("%d", answer);
    }

    private static int gasgasgas(int[][] board, List gasPoint) {
        int[][] testBoard = new int[board.length][board[0].length];
        Queue<Integer[]> gasP = new LinkedList<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                testBoard[i][j] = board[i][j];
            }
        }
        for (Object g : gasPoint) {
            gasP.add((Integer[]) g);
        }
        //확산
        while (!gasP.isEmpty()) {
            int gSize = gasP.size();
            for (int i = 0; i < gSize; i++) {
                Integer[] now = gasP.remove();
                if (now[0] > 0 && testBoard[now[0] - 1][now[1]] == 0) {
                    testBoard[now[0] - 1][now[1]] = 2;
                    gasP.add(new Integer[]{now[0] - 1, now[1]});
                }
                if (now[0] < board.length - 1 && testBoard[now[0] + 1][now[1]] == 0) {
                    testBoard[now[0] + 1][now[1]] = 2;
                    gasP.add(new Integer[]{now[0] + 1, now[1]});
                }
                if (now[1] > 0 && testBoard[now[0]][now[1] - 1] == 0) {
                    testBoard[now[0]][now[1] - 1] = 2;
                    gasP.add(new Integer[]{now[0], now[1] - 1});
                }
                if (now[1] < board[0].length - 1 && testBoard[now[0]][now[1] + 1] == 0) {
                    testBoard[now[0]][now[1] + 1] = 2;
                    gasP.add(new Integer[]{now[0], now[1] + 1});
                }
            }
        }

        int safetyArea = 0;
        for (int[] row : testBoard) {
            for (int i : row) {
                if (i == 0) safetyArea++;
            }
        }

        return safetyArea;
    }
}