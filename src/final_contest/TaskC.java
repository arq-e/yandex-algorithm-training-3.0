package final_contest;

import java.io.*;
import java.util.Arrays;
import java.util.stream.Stream;

public class TaskC {
    public static void main(String[] args) throws IOException {
        TaskC task = new TaskC();
        task.solve();
    }

    private void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] orders = new int[N][];
        for (int i = 0; i < N; ++i) {
            orders[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        br.close();
        int[][][] dp = new int[2][N+1][N+1];
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; ++i) {
            dp[0][0][i] = orders[0][0];
            dp[1][i][0] = orders[0][0];
        }

        String[][] history = new String[N+1][N+1];
        for (int i = 1; i <= N; ++i) {
            Arrays.fill(history[i], "");
        }
        history[0][0] = "";
        for (int i = 1; i <= N; ++i) {
            history[0][i] = history[0][i-1] + "1";
            history[i][0] = history[i-1][0] + "2";
        }
        System.out.println(sb.toString().trim());
    }
}
