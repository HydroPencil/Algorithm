import java.io.*;
import java.util.*;

class Main {
    private static int[][] areas;
    private static int N;
    private static int min = Integer.MAX_VALUE;
    private static int totalPopulation = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        areas = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                areas[i][j] = Integer.parseInt(st.nextToken());
                totalPopulation += areas[i][j];
            }
        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                for (int d1 = 1; d1 < N; d1++) {
                    for (int d2 = 1; d2 < N; d2++) {
                        if (r + d1 + d2 >= N) continue;
                        if (c < d1 || c >= N - d2) continue;

                        check(r, c, d1, d2);
                    }
                }
            }
        }

        System.out.printf("%d", min);
    }

    private static void check(int r, int c, int d1, int d2) {
        int[] population = new int[5];
        boolean[][] border = new boolean[N][N];

        for (int i = 0; i <= d1; i++) {
            border[r + i][c - i] = true;
            border[r + d2 + i][c + d2 - i] = true;
        }
        for (int i = 0; i <= d2; i++) {
            border[r + i][c + i] = true;
            border[r + d1 + i][c - d1 + i] = true;
        }

        for (int i = 0; i < r + d1; i++) {
            for (int j = 0; j <= c; j++) {
                if (border[i][j]) break;
                population[0] += areas[i][j];
            }
        }

        for (int i = 0; i <= r + d2; i++) {
            for (int j = N - 1; j > c; j--) {
                if (border[i][j]) break;
                population[1] += areas[i][j];
            }
        }

        for (int i = r + d1; i < N; i++) {
            for (int j = 0; j < c - d1 + d2; j++) {
                if (border[i][j]) break;
                population[2] += areas[i][j];
            }
        }

        for (int i = r + d2 + 1; i < N; i++) {
            for (int j = N - 1; j >= c - d1 + d2; j--) {
                if (border[i][j]) break;
                population[3] += areas[i][j];
            }
        }

        //5 area
        population[4] = totalPopulation;

        for (int i = 0; i < 4; i++) {
            population[4] -= population[i];
        }

        Arrays.sort(population);
        min = Math.min(min, population[4] - population[0]);
    }
}