package final_contest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

public class TaskE {
    public static void main(String[] args) throws IOException {
        TaskE task = new TaskE();
        task.solve();
    }

    private void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] param = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        char[][] field = new char[param[0]][param[1]];
        for (int i = 0; i < param[0]; ++i) {
            field[i] = br.readLine().toCharArray();
        }
        int[] start = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int buff = start[0];
        start[0] = param[0] - start[1];
        start[1] = buff - 1;

        int[] end = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        buff = end[0];
        end[0] = param[0] - end[1];
        end[1] = buff - 1;
        br.close();

        System.out.println(bfs(param, start, end, field));
    }

    private int bfs(int[] param, int[] start, int[] end, char[][] field) {
        int[] directions = new int[]{1,-1,0,1,1,0,-1,-1,1};

        Deque<int[]> deque = new ArrayDeque<>();
        int[] first = new int[]{start[0], start[1], -1, 0};
        deque.add(first);
        int min = Integer.MAX_VALUE;
        field[start[0]][start[1]] = '#';
        while(deque.size() > 0) {
            int[] pos = deque.pollFirst();
            field[pos[0]][pos[1]] = '#';
            if (pos[0] == end[0] && pos[1] == end[1] && pos[3] < min) {
                min = pos[3];
            } else {
                for (int i = 0; i < 8; ++i) {
                    int[] newPos = Arrays.copyOf(pos,4);
                    newPos[2] = i;
                    if (i == pos[2]) {
                        newPos[3] = pos[3];
                    } else newPos[3] = pos[3] + 1;
                    while (true) {
                        int[] next = Arrays.copyOf(newPos,4);
                        next[0] = newPos[0] + directions[i];
                        next[1] = newPos[1] + directions[i+1];
                        if (next[0] >= 0 && next[0] < field.length && next[1] >= 0 && next[1] < field[0].length
                                && field[next[0]][next[1]] == '.') {
                            deque.addLast(next);
                            newPos = next;
                        } else break;
                    }
                }
            }
        }
        if (min == Integer.MAX_VALUE) {
            return -1;
        } else {
            return min;
        }
    }
}
