import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {
    private static int max;
    private static int cnt,limit;
    private static int[][] imf;
    
    private static void findMax(int itemIndex, int kal, int satisfy) {
        if(kal > limit) return;
        if(itemIndex == cnt) {
            if(max < satisfy) max = satisfy;
            return;
        }
        findMax(itemIndex+1, kal+imf[itemIndex][1], satisfy+imf[itemIndex][0]);
        findMax(itemIndex+1, kal, satisfy);
    }
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            int T = Integer.parseInt(br.readLine());
            for(int test_case = 1; test_case <= T; test_case++)
            {
                String[] in = br.readLine().split(" ");
                cnt = Integer.parseInt(in[0]);
                limit = Integer.parseInt(in[1]);
                imf = new int[cnt][2];
                max = 0;
                
                for(int i=0; i<cnt; i++) {
                    in = br.readLine().split(" ");
                    imf[i][0] = Integer.parseInt(in[0]);
                    imf[i][1] = Integer.parseInt(in[1]);
                }
                
				findMax(0,0,0);
                bw.write("#"+test_case+" "+max+"\n");
            }
            bw.flush();
            bw.close();
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}