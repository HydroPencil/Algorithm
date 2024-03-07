import java.io.*;
import java.util.*;

public class Main {
    static boolean[][] board = new boolean[101][101];
    static int[] calcR = {0, -1, 0, 1};
    static int[] calcC = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int curveCount = Integer.parseInt(br.readLine());

        for (int i = 0; i < curveCount; i++) {
            String[] inf = br.readLine().split(" ");
            int c = Integer.parseInt(inf[0]);
            int r = Integer.parseInt(inf[1]);
            int d = Integer.parseInt(inf[2]);
            int g = Integer.parseInt(inf[3]);
            createDragonCurve(r, c, d, g);
        }

        System.out.printf("%d", checkSquare());
    }

    private static void createDragonCurve(int r, int c, int direct, int generation) {
        List<Integer> route = new LinkedList<>();
        Stack<Integer> st = new Stack<>();
        route.add(direct);

        int row = r + calcR[direct];
        int col = c + calcC[direct];
        board[r][c] = true;
        board[row][col] = true;
        int g = 0;

        while (g < generation) {
            for (int i : route) {
                st.push(i);
            }
            while (!st.isEmpty()) {
                int nextDirect = (st.pop() + 1) % 4;
                row += calcR[nextDirect];
                col += calcC[nextDirect];
                board[row][col] = true;
                route.add(nextDirect);
            }
            g++;
        }
    }

    private static int checkSquare() {
        int ans = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (board[i][j] && board[i][j + 1] && board[i + 1][j] && board[i + 1][j + 1]) ans++;
            }
        }
        return ans;
    }
}