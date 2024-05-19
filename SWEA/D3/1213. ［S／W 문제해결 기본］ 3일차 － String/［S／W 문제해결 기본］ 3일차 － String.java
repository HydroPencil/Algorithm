import java.util.*;
import java.io.*;

class Solution
{
    public static int search(String key, String in) {
    	int ans = 0;
        int index = in.indexOf(key);
        while(index > 0) {
        	ans++;
            index = in.indexOf(key, index+1);
        }
        return ans;
    }
	public static void main(String args[]) throws Exception
	{
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			for(int test_case = 1; test_case <= 10; test_case++)
			{
                String t = br.readLine();
                String key = br.readLine();
                String in = br.readLine();
				bw.append("#"+test_case+" "+search(key, in)+"\n");
			}
            bw.flush();
            bw.close();
            br.close();
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}
}