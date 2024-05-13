import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Solution {
    private static int TEST_COUNT = 10;

    private static int isValid(String in, int length) {
        Stack<Character> st = new Stack<>();
        for(int i=0; i<length; i++) {
            if(in.charAt(i) == '(' || in.charAt(i) == '[' || in.charAt(i) == '{' || in.charAt(i) == '<') {
                st.push(in.charAt(i));
                continue;
            }
            if(in.charAt(i) == ')') {
                if(st.peek() == '(') {
                    st.pop();
                    continue;
                } else {
                    return 0;
                }
            }
            if(in.charAt(i) == ']') {
                if(st.peek() == '[') {
                    st.pop();
                    continue;
                } else {
                    return 0;
                }
            }
            if(in.charAt(i) == '}') {
                if(st.peek() == '{') {
                    st.pop();
                    continue;
                } else {
                    return 0;
                }
            }
            if(in.charAt(i) == '>') {
                if(st.peek() == '<') {
                    st.pop();
                    continue;
                } else {
                    return 0;
                }
            }
        }
        if(st.size() == 0) return 1;
        return 0;
    }
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            int T = TEST_COUNT;
            for(int test_case = 1; test_case <= T; test_case++)
            {
                int length = Integer.parseInt(br.readLine());
                String in = br.readLine();
                bw.write("#"+test_case+" "+isValid(in, length)+"\n");
            }
            bw.flush();
            bw.close();
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}