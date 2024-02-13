import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] check = new boolean[n];
        int ans = 0;

        for (int i = 0; i < n; i++) {
            if (!check[i]) {
                q.add(i);
                check[i] = true;

                while (!q.isEmpty()) {
                    int index = q.poll();
                    for (int j = 0; j < n; j++) {
                        if (computers[index][j] == 1 && !check[j]) {
                            q.add(j);
                            check[j] = true;
                        }
                    }
                }

                ans++;
            }
        }

        return ans;
    }
}