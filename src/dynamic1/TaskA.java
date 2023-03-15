package dynamic1;

import java.util.Scanner;

public class TaskA {

    public static void main(String[] args) {
        TaskA task = new TaskA();
        task.solve();
    }

    private void solve() {
        int dp[] = new int[35];
        dp[0] = 2;
        dp[1] = 4;
        dp[2] = 7;
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        for (int i = 3; i < N; ++i) {
            dp[i] = dp[i-1] + dp[i-2]+dp[i-3];
        }
        System.out.println(dp[N-1]);
    }
}
