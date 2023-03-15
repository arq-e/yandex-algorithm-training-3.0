package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class TaskD {
    public static void main(String[] args) throws IOException {
        TaskD task = new TaskD();
        task.solve();
    }

    private void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[][][] cavern = new char[N][N][N];
        int[] pos = new int[3];
        for (int i = 0; i < N; ++i) {
            br.readLine();
            for (int j = 0; j < N; ++j) {
                String s = br.readLine();
                cavern[i][j] = s.toCharArray();
                if (s.contains("S")) {
                    pos[0] = i;
                    pos[1] = j;
                    pos[2] = s.indexOf('S');
                }
            }
        }
        System.out.println(bfs(cavern, pos, N));
    }

    private int bfs(char[][][] cavern, int[] pos, int N) {
        int[][][] lengthMap = new int[N][N][N];

        int res = 0;
        Deque<int[]> deque = new ArrayDeque<>();
        deque.add(pos);
        int[] steps = new int[]{0,0,1,0,0,-1,0,0};
        while (deque.size() > 0) {
            int[] curr = deque.pollFirst();
            for (int i = 0; i < steps.length-2; ++i) {
                int[] next = new int[3];
                next[0] = curr[0] + steps[i];
                next[1] = curr[1] + steps[i+1];
                next[2] = curr[2] + steps[i+2];
                if (next[0] >= 0 && next[1] >= 0 && next[2] >= 0
                        && next[0] < N && next[1] < N && next[2] < N) {
                    if (cavern[next[0]][next[1]][next[2]] == '.'
                            && lengthMap[next[0]][next[1]][next[2]] == 0) {
                        lengthMap[next[0]][next[1]][next[2]] = lengthMap[curr[0]][curr[1]][curr[2]] + 1;
                        if (next[0] == 0) {
                            return lengthMap[next[0]][next[1]][next[2]];
                        } else deque.addLast(next);
                    }
                }
            }

        }
        return -1;
    }
}
