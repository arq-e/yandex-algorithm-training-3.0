package dynamic1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskC {

    public static void main(String[] args) {
        TaskC task = new TaskC();
        task.solve();
    }

    private void solve() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        List<Integer> list = new ArrayList<>();
        list.add(N);
        int arr[] = new int[N+1];
        int arr1[] = new int[N+1];
        arr[N] = 1;
        arr1[0] = 1;
        for (int i = N; i > 1; --i) {
            if (arr[i] > 0) {
                if(arr[i-1] > 0) {
                    if (arr[i]+1 < arr[i-1]) {
                        arr[i-1] = arr[i]+1;
                        arr1[i-1] = i;
                    }
                } else {
                    arr[i-1] = arr[i]+1;
                    arr1[i-1] = i;
                }
                if (i % 2 == 0) {
                    if (arr[i/2] > 0) {
                        if (arr[i]+1 < arr[i/2]) {
                            arr[i/2] = arr[i]+1;
                            arr1[i/2] = i;
                        }
                    } else  {
                        arr[i/2] = arr[i] + 1;
                        arr1[i/2] = i;
                    }
                }
                if (i % 3 == 0) {
                    if (arr[i/3] > 0) {
                        if (arr[i]+1 < arr[i/3]) {
                            arr[i/3] = arr[i]+1;
                            arr1[i/3] = i;
                        }
                    } else  {
                        arr[i/3] = arr[i] + 1;
                        arr1[i/3] = i;
                    }
                }
            }
        }
        System.out.println(arr[1]-1);
        int pos = 0;
        while (pos < N) {
            System.out.print(arr1[pos] + " ");
            pos = arr1[pos];
        }
    }
}
