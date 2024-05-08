import java.util.*;
import java.io.*;

class Solution
{
    private static int[] chessBoard;
    private static int ans;

    private static boolean isValid(int nthNumber) {
        for(int i=0; i<chessBoard.length; i++) {
            if(chessBoard[i] == 0) continue;
            if(nthNumber != i && Math.abs(chessBoard[nthNumber] - chessBoard[i]) ==  Math.abs(nthNumber - i)) return false;
        }
        return true;
    }

    private static void putQueen(int nth) {
        for(int i=0; i<chessBoard.length; i++) {
            if(chessBoard[i] == 0) {
                chessBoard[i] = nth;
                boolean valid = isValid(i);
                if(nth != 1 && !valid) {
                    chessBoard[i] = 0;
                    continue;
                }
                if(nth == chessBoard.length) {
                    if(valid) ans++;
                } else {
                    putQueen(nth+1);
                }
                chessBoard[i] = 0;
            }
        }
    }

    private static int nQueen(int n) {
        if(n == 1) return 1;
        chessBoard = new int[n];
        ans = 0;
        putQueen(1);
        return ans;
    }

    public static void main(String args[]) throws Exception
    {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            int T = Integer.parseInt(br.readLine());
            for(int tc = 1; tc <=T; tc++) {
                String n = br.readLine();
                bw.append("#"+tc+" "+nQueen(Integer.parseInt(n))+"\n");
            }
            bw.flush();
            bw.close();
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}