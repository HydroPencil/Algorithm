import java.io.*;

class Solution {
    static class Point {
        int r;
        int c;
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    private static final int BOARD_SIZE = 100;
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = 10;
        int [][] board = new int[BOARD_SIZE][BOARD_SIZE];
        for(int test_case = 1; test_case <= T; test_case++)
        {
            Point bottom_up = new Point(0,0);
            String case_num = br.readLine();
            for(int i=0; i<BOARD_SIZE; i++) {
                String[] in = br.readLine().split(" ");
                for(int j=0; j<BOARD_SIZE; j++) {
                    board[i][j] = Integer.parseInt(in[j]);
                    if(board[i][j] == 2) {
                        bottom_up.r = i;
                        bottom_up.c = j;
                    }
                }
            }

            while(bottom_up.r > 0) {
                while(bottom_up.c < BOARD_SIZE-1 && board[bottom_up.r][bottom_up.c+1] == 1) {
                    bottom_up.c++;
                    if(board[bottom_up.r-1][bottom_up.c] == 1) {
                        bottom_up.r--;
                    }
                }
                while (bottom_up.c > 0 && board[bottom_up.r][bottom_up.c-1] == 1) {
                    bottom_up.c--;
                    if(board[bottom_up.r-1][bottom_up.c] == 1) {
                        bottom_up.r--;
                    }
                }
                bottom_up.r--;
            }

            System.out.printf("#%d %d\n", test_case, bottom_up.c);
        }
    }
}
