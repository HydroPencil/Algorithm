import java.io.*;
import java.util.*;

public class Main {
    static class Ripe {
        int m, n;
        int day;

        Ripe(int m, int n, int day) {
            this.m = m;
            this.n = n;
            this.day = day;
        }
    }

    static int[] mMove = {1, 0, -1, 0};
    static int[] nMove = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Queue<Ripe> progress = new LinkedList<>();

        int m, n;
        String[] farmSize = br.readLine().split(" ");
        m = Integer.parseInt(farmSize[0]);
        n = Integer.parseInt(farmSize[1]);

        int[][] farm = new int[n][m];
        for (int i = 0; i < n; i++) {
            String[] tomatoStatus = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                farm[i][j] = Integer.parseInt(tomatoStatus[j]);
                if (farm[i][j] == 1) {
                    progress.add(new Ripe(j, i, 0));
                }
            }
        }

        int ans = 0;
        while (!progress.isEmpty()) {
            int count = progress.size();
            for (int i = 0; i < count; i++) {
                Ripe s = progress.poll();

                if (ans < s.day) ans = s.day;

                for (int j = 0; j < 4; j++) {
                    int nextM = s.m + mMove[j];
                    int nextN = s.n + nMove[j];
                    if (nextM < m && nextM >= 0 && nextN < n && nextN >= 0) {
                        if (farm[nextN][nextM] == 0) {
                            progress.add(new Ripe(nextM, nextN, s.day + 1));
                            farm[nextN][nextM] = 1;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (farm[i][j] == 0) {
                    ans = -1;
                }
            }
        }

        System.out.printf("%d", ans);
    }
}