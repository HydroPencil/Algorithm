import java.io.*;

public class Main {
    private static int[][] room;
    private static int direct;
    private static int[] moveRow = {-1, 0, 1, 0};
    private static int[] moveCol = {0, 1, 0, -1};
    private static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int row;
        int col;
        count = 0;
        String[] boardSize = br.readLine().split(" ");
        row = Integer.parseInt(boardSize[0]);
        col = Integer.parseInt(boardSize[1]);
        String[] init = br.readLine().split(" ");

        direct = Integer.parseInt(init[2]);

        room = new int[row][col];
        for (int i = 0; i < row; i++) {
            String[] elements = br.readLine().split(" ");
            for (int j = 0; j < col; j++) {
                room[i][j] = Integer.parseInt(elements[j]);
            }
        }

        clean(Integer.parseInt(init[0]), Integer.parseInt(init[1]));

        System.out.printf("%d", count);
    }

    private static void clean(int r, int c) {
        if (room[r][c] == 0) {
            room[r][c] = 2;
            count++;
        }

        int next = changeDirect(direct);
        int checkCount = 0;
        while (room[r + moveRow[next]][c + moveCol[next]] != 0 && checkCount < 4) {
            next = changeDirect(next);
            checkCount++;
        }

        if (checkCount == 4) {
            if (room[r + moveRow[(direct + 2) % 4]][c + moveCol[(direct + 2) % 4]] == 1) return;
            clean(r + moveRow[(direct + 2) % 4], c + moveCol[(direct + 2) % 4]);
        } else {
            direct = next;
            clean(r + moveRow[direct], c + moveCol[direct]);
        }
        return;
    }

    private static int changeDirect(int d) {
        int ans = d - 1;
        if (ans < 0) return 3;
        return ans;
    }
}