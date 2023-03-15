package dynamic2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class TaskE {

    public static void main(String[] args) throws IOException {
        TaskE task = new TaskE();
        task.solve();
    }

    private void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] firstSequence = Stream.of(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        int M = Integer.parseInt(br.readLine().trim());
        int[] secondSequence = Stream.of(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        br.close();

        int[][] dp = new int[N][M];
        for (int i = 0; i < N; ++i) {
            if (firstSequence[i] == secondSequence[0] || i > 0 && dp[i-1][0] == 1) {
                dp[i][0] = 1;
            }
        }
        for (int j = 0; j < M; ++j) {
            if (secondSequence[j] == firstSequence[0] || j > 0 && dp[0][j-1] == 1) {
                dp[0][j] = 1;
            }
        }

        for (int i = 1; i < N; ++i) {
            for(int j = 1; j < M; ++j) {
                if (firstSequence[i] == secondSequence[j]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else dp[i][j] =  Math.max(dp[i][j-1], dp[i-1][j]);
            }
        }
        int max = dp[N-1][M-1];
        if (max == 0) return;
        int[] res = new int[max];
        int i = N-1;
        int j = M-1;
        while (max > 0) {
            if (j > 0 && dp[i][j-1] == dp[i][j]) {
                --j;
            } else if (i > 0 && dp[i-1][j] == dp[i][j]) {
                --i;
            } else {
                res[--max] = firstSequence[i];
                --i;
                --j;
            }
        }
        for (int k = 0; k < res.length; ++k) {
            System.out.print(res[k] + " ");
        }
    }
}
