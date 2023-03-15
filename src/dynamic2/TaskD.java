package dynamic2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class TaskD {

    public static void main(String[] args) throws IOException {
        TaskD task = new TaskD();
        task.solve();
    }

    private void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        if (N == 0) {
            System.out.println(0);
            System.out.println(0 + " " + 0);
            return;
        }
        int[] prices = new int[N];
        for (int i = 0; i < N; ++i) {
            prices[i] = Integer.parseInt(br.readLine());
        }
        br.close();
        if (N == 1) {
            System.out.println(prices[0]);
            int used = 0;
            int left = prices[0] > 100 ? 1 : 0;
            System.out.println(left + " " + used);
            return;
        }
        int[][] dp = new int[N][N];
        String[][] dpSteps = new String[N][N];
        for (int i = 0; i < N; ++i) {
            Arrays.fill(dpSteps[i], "");
            Arrays.fill(dp[i], -1);
        }
        if (prices[0] > 100) {
            dp[0][1] = prices[0];
        } else dp[0][0] = prices[0];
        for (int i = 1; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                int newCoupon = Integer.MAX_VALUE;
                int useCoupon = Integer.MAX_VALUE;
                int saveCoupon = Integer.MAX_VALUE;
                if (j > 0 && dp[i-1][j-1] >= 0 && prices[i] > 100) {
                    newCoupon = dp[i-1][j-1] + prices[i];
                }
                if (j < N-1 && dp[i-1][j+1] >= 0) {
                    useCoupon = dp[i-1][j+1];
                }
                if (dp[i-1][j] >= 0) {
                    saveCoupon = dp[i-1][j] + prices[i];
                }
                if (useCoupon != Integer.MAX_VALUE && useCoupon <= newCoupon
                        && useCoupon <= saveCoupon) {
                    dp[i][j] = useCoupon;
                    dpSteps[i][j] = dpSteps[i-1][j+1] + " " + (i+1);
                } else if (newCoupon != Integer.MAX_VALUE && newCoupon <= useCoupon
                        && newCoupon <= saveCoupon) {
                    dp[i][j] = newCoupon;
                    dpSteps[i][j] = dpSteps[i-1][j-1];
                } else if (saveCoupon != Integer.MAX_VALUE && saveCoupon <= useCoupon
                        && saveCoupon <= newCoupon) {
                    dp[i][j] = saveCoupon;
                    dpSteps[i][j] = dpSteps[i-1][j];
                }
            }
        }
        int last = N-1;
        int min = dp[last][0];
        int pos = 0;
        for (int i = 0; i < N; ++i) {
            if(dp[last][i] <= min && dp[last][i] >= 0) {
                min = dp[last][i];
                pos = i;
            }
        }
        System.out.println(min);
        if (dpSteps[last][pos].length() > 0) {
            int[] res = Stream.of(dpSteps[last][pos].trim().split(" ")).mapToInt(Integer::parseInt).toArray();
            System.out.println(pos + " " + res.length);
            for (int i : res) {
                System.out.println(i);
            }
        } else {
            System.out.println(pos + " " + 0);
        }

    }
}
