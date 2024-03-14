import java.io.*;
import java.util.*;

public class Main {
    private static final int MAX_CCTV_COUNT = 8;
    private static int[][] board;
    private static int N;
    private static int M;
    private static int min;
    private static int[] cctvR;
    private static int[] cctvC;
    private static int cctvCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tk = new StringTokenizer(br.readLine());

        cctvR = new int[MAX_CCTV_COUNT];
        cctvC = new int[MAX_CCTV_COUNT];
        cctvCount = 0;
        N = Integer.parseInt(tk.nextToken());
        M = Integer.parseInt(tk.nextToken());
        board = new int[N][M];
        min = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            tk = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(tk.nextToken());
                if (board[i][j] != 0 && board[i][j] != 6) {
                    cctvR[cctvCount] = i;
                    cctvC[cctvCount++] = j;
                }
            }
        }

        check(0);

        System.out.printf("%d", min);
    }

    private static void check(int n) {
        if (n < cctvCount) {
            int repeat = 1;
            if (board[cctvR[n]][cctvC[n]] == 1 || board[cctvR[n]][cctvC[n]] == 3 || board[cctvR[n]][cctvC[n]] == 4) {
                repeat = 4;
            } else if (board[cctvR[n]][cctvC[n]] == 2) {
                repeat = 2;
            }
            for (int i = 0; i < repeat; i++) {
                int[][] preRoomStatus = copyRoom();
                search(board[cctvR[n]][cctvC[n]], cctvR[n], cctvC[n], i);
                check(n + 1);
                board = preRoomStatus;
            }
        } else {
            unvalidArea();
        }
    }

    private static void search(int type, int r, int c, int direct) {
        switch (type) {
            case 1:
                switch (direct) {
                    case 0:
                        checkRight(r, c);
                        break;
                    case 1:
                        checkDown(r, c);
                        break;
                    case 2:
                        checkLeft(r, c);
                        break;
                    case 3:
                        checkUp(r, c);
                }
                break;

            case 2:
                switch (direct) {
                    case 0:
                        checkRight(r, c);
                        checkLeft(r, c);
                        break;
                    case 1:
                        checkDown(r, c);
                        checkUp(r, c);
                }
                break;

            case 3:
                switch (direct) {
                    case 0:
                        checkUp(r, c);
                        checkRight(r, c);
                        break;
                    case 1:
                        checkRight(r, c);
                        checkDown(r, c);
                        break;
                    case 2:
                        checkDown(r, c);
                        checkLeft(r, c);
                        break;
                    case 3:
                        checkLeft(r, c);
                        checkUp(r, c);
                        break;
                }
                break;

            case 4:
                switch (direct) {
                    case 0:
                        checkLeft(r, c);
                        checkUp(r, c);
                        checkRight(r, c);
                        break;
                    case 1:
                        checkUp(r, c);
                        checkRight(r, c);
                        checkDown(r, c);
                        break;
                    case 2:
                        checkRight(r, c);
                        checkDown(r, c);
                        checkLeft(r, c);
                        break;
                    case 3:
                        checkDown(r, c);
                        checkLeft(r, c);
                        checkUp(r, c);
                        break;
                }
                break;

            case 5:
                checkUp(r, c);
                checkRight(r, c);
                checkDown(r, c);
                checkLeft(r, c);
        }
    }

    private static void checkRight(int r, int c) {
        for (int i = c; i < M; i++) {
            if (board[r][i] == 6) break;
            if (board[r][i] == 0) {
                board[r][i] = -1;
            }
        }
    }

    private static void checkDown(int r, int c) {
        for (int i = r; i < N; i++) {
            if (board[i][c] == 6) break;
            if (board[i][c] == 0) {
                board[i][c] = -1;
            }
        }
    }

    private static void checkLeft(int r, int c) {
        for (int i = c; i >= 0; i--) {
            if (board[r][i] == 6) break;
            if (board[r][i] == 0) {
                board[r][i] = -1;
            }
        }
    }

    private static void checkUp(int r, int c) {
        for (int i = r; i >= 0; i--) {
            if (board[i][c] == 6) break;
            if (board[i][c] == 0) {
                board[i][c] = -1;
            }
        }
    }

    private static void unvalidArea() {
        int ans = 0;
        for (int[] r : board) {
            for (int area : r) {
                if (area == 0) ans++;
            }
        }
        if (min > ans) {
            min = ans;
        }
    }

    private static int[][] copyRoom() {
        int[][] copyroom = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copyroom[i][j] = board[i][j];
            }
        }
        return copyroom;
    }
}