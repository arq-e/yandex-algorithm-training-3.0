package dynamic2;

import java.util.Scanner;

public class TaskC {

    public static void main(String[] args) {
        TaskC task = new TaskC();
        task.solve();
    }

    private void solve() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[][] table = new int[N][M];
        table[0][0] = 1;
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                if ( i + 2 < N && j + 1 < M) {
                    table[i+2][j+1] += table[i][j];
                }
                if (i + 1 < N && j + 2 < M) {
                    table[i+1][j+2] += table[i][j];
                }
            }
        }
        System.out.println(table[N-1][M-1]);
    }
}
