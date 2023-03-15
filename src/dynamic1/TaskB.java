package dynamic1;

import java.util.Scanner;

public class TaskB {

    public static void main(String[] args) {
        TaskB task = new TaskB();
        task.solve();
    }

    private void solve() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int k = sc.nextInt();
        int arr[] = new int[N];
        arr[N-1] = 1;
        for (int i = N-2; i >= 0; --i) {
            for (int j = 1; j <=k; ++j) {
                if (i + j >= N) break;
                arr[i] += arr[i+j];
            }
        }
        System.out.println(arr[0]);
    }
}
