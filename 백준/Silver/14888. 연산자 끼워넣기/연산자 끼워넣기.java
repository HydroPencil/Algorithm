import java.io.*;

public class Main {
    private static int max = Integer.MIN_VALUE;
    private static int min = Integer.MAX_VALUE;
    private static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
        String[] numsStr = br.readLine().split(" ");
        nums = new int[count];
        String[] operStr = br.readLine().split(" ");
        int[] opers = new int[4];// + - * / 순서

        for (int i = 0; i < count; i++) {
            nums[i] = Integer.parseInt(numsStr[i]);
        }
        for (int i = 0; i < 4; i++) {
            opers[i] = Integer.parseInt(operStr[i]);
        }

        calc(opers, nums[0], 1);
        System.out.printf("%d\n%d", max, min);
    }

    private static void calc(int[] opers, int value, int point) {
        if (point == nums.length) {
            check(value);
            return;
        }

        int ans;
        if (opers[0] > 0) {
            opers[0]--;
            ans = value + nums[point];
            if (point < nums.length) {
                calc(opers, ans, point + 1);
            }
            opers[0]++;
        }
        if (opers[1] > 0) {
            opers[1]--;
            ans = value - nums[point];
            if (point < nums.length) {
                calc(opers, ans, point + 1);
            }
            opers[1]++;
        }
        if (opers[2] > 0) {
            opers[2]--;
            ans = value * nums[point];
            if (point < nums.length) {
                calc(opers, ans, point + 1);
            }
            opers[2]++;
        }
        if (opers[3] > 0) {
            opers[3]--;
            ans = value / nums[point];
            if (point < nums.length) {
                calc(opers, ans, point + 1);
            }
            opers[3]++;
        }
    }

    private static void check(int ans) {
        if (max < ans) max = ans;
        if (min > ans) min = ans;
    }
}