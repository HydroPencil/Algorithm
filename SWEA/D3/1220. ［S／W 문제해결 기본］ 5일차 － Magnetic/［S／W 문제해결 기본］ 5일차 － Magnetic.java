import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {
    private static int TEST_COUNT = 10;
    private static int TABLE_SIZE = 100;
    private static char[][] table;

    private static int search() {
        int ans = 0;

        for(int c=0; c<TABLE_SIZE; c++) {
            int top = 0, bottom = TABLE_SIZE-1;
            while(table[top][c] != '1') {
                top++;
            }
            while(table[bottom][c] != '2') {
                bottom--;
            }
            if(top < bottom) {
                char pre = table[top][c];
                for(int i=top; i<=bottom; i++) {
                    if(table[i][c] =='1') {
                        if(pre == '2') pre = '1';
                    }
                    if(table[i][c] == '2') {
                        if(pre == '1') {
                            ans++;
                            pre = '2';
                        }
                    }
                }
            }
        }

        return ans;
    }
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            int T = TEST_COUNT;
            for(int test_case = 1; test_case <= T; test_case++)
            {
                int TABLE_SIZE = Integer.parseInt(br.readLine());
                table = new char[TABLE_SIZE][TABLE_SIZE];
                for(int i=0; i<TABLE_SIZE; i++) {
                    String[] in = br.readLine().split(" ");
                    for(int j=0; j<TABLE_SIZE; j++) {
                        table[i][j] = in[j].charAt(0);
                    }
                }

                bw.write("#"+test_case+" "+search()+"\n");
            }
            bw.flush();
            bw.close();
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}