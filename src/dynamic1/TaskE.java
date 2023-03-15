package dynamic1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class TaskE {

    public static void main(String[] args) throws IOException {
        TaskE task = new TaskE();
        task.solve();
    }

    private void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int nails[] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        br.close();
        Arrays.sort(nails);
        if (N == 1) {
            System.out.println(0);
            return;
        }
        int[] dp = new int[N];
        dp[1] = nails[1] - nails[0];
        dp[0] = nails[1] - nails[0];
        for (int i = 2; i < N; ++i) {
            dp[i] = Math.min(dp[i-2] + nails[i] - nails[i-1], dp[i-1] + nails[i] - nails[i-1]);
        }
        System.out.println(dp[N-1]);
    }
}
