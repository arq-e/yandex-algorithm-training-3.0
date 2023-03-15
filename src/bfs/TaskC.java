package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.stream.Stream;

public class TaskC {
    public static void main(String[] args) throws IOException {
        TaskC task = new TaskC();
        task.solve();
    }

    private void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] params = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = params[0];
        int M = params[1];
        int S = params[2]-1;
        int T = params[3]-1;
        int Q = params[4];
        int[][] positions = new int[Q][2];
        for (int i = 0; i < Q; ++i) {
            positions[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        System.out.println(bfs(N, M, S, T, Q, positions));
    }

    private int bfs(int N, int M, int S, int T, int Q, int[][] positions) {
        int[][] lengthMap = new int[N][M];
        for (int i = 0; i < N; ++i) {
            Arrays.fill(lengthMap[i],-1);
        }
        lengthMap[S][T] = 0;
        Deque<int[]> deque = new ArrayDeque<>();
        deque.add(new int[]{S, T});

        int[] moves = new int[]{1,2,-1,2,1,-2,-1,-2,1};
        while (deque.size() > 0) {
            int[] curr = deque.pollFirst();
            for (int i = 0; i < moves.length-1; ++i) {
                int[] pos = new int[2];
                pos[0] = curr[0] + moves[i];
                pos[1] = curr[1] + moves[i+1];
                if (pos[0] >= 0 && pos[1] >= 0 && pos[0] < N && pos[1] < M
                        && lengthMap[pos[0]][pos[1]] == -1) {
                    deque.addLast(pos);
                    lengthMap[pos[0]][pos[1]] = lengthMap[curr[0]][curr[1]] + 1;
                }
            }
        }
        int sum = 0;
        for (int i = 0; i < Q; ++i) {
            int num = lengthMap[positions[i][0]-1][positions[i][1]-1];
            if (num == -1) {
                return -1;
            } else sum += num;
        }
        return sum;
    }
}
