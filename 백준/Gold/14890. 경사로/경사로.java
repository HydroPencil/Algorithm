import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> st = new Stack<>();
        String[] option = br.readLine().split(" ");
        int n = Integer.parseInt(option[0]);
        int l = Integer.parseInt(option[1]);
        int[][] roads = new int[2 * n][n];
        int ans = 0;

        for (int i = 0; i < n; i++) {
            String[] road = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                int block = Integer.parseInt(road[j]);
                roads[i][j] = block;
                roads[n + j][i] = block;
            }
        }

        for (int[] r : roads) {
            int pre = r[0];
            st.push(pre);

            for (int i = 1; i <= n; i++) {
                if (i == n) {
                    ans++;
                    break;
                }
                if (r[i] == pre) {
                    st.push(pre);
                    continue;
                }
                if (r[i] == pre + 1) {
                    if (st.size() < l) {
                        break;
                    }
                    st.clear();
                    pre = r[i];
                    st.push(pre);
                    continue;
                }
                if (r[i] == pre - 1) {
                    st.clear();
                    pre = r[i];
                    int count;
                    for (count = 0; count < l && i + count < n; count++) {
                        if (r[i + count] != r[i]) {
                            break;
                        }
                    }

                    if (count != l) break;

                    i += (l - 1);
                    continue;
                }
                break;
            }

            st.clear();
        }

        System.out.printf("%d", ans);
    }
}