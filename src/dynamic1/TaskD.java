package dynamic1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class TaskD {

    public static void main(String[] args) throws IOException {
        TaskD task = new TaskD();
        task.solve();
    }

    private void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int serviceTimes[][] = new int[N][3];
        for (int i = 0; i < N; ++i) {
            serviceTimes[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        br.close();
        if (N == 1) {
            System.out.println(serviceTimes[0][0]);
            return;
        }

        int dp[] = new int[N+2];
        dp[N-1] = serviceTimes[N-1][0];
        dp[N-2] = Math.min(serviceTimes[N-1][1], serviceTimes[N-2][1]+dp[N-1]);
        for (int i = N-2; i >= 0; --i) {
            int serve1 = serviceTimes[i][0] + dp[i+1];
            int serve2 = serviceTimes[i][1] + dp[i+2];
            int serve3 = serviceTimes[i][2] + dp[i+3];
            dp[i] = Math.min(serve3, Math.min(serve1, serve2));
        }
        System.out.println(dp[0]);
    }
}
