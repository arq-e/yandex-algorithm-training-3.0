package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

public class TaskA {

    public static void main(String[] args) throws IOException {
        TaskA task = new TaskA();
        task.solve();
    }

    private void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] matrix = new int[N][N];
        for (int i = 0; i < N; ++i) {
            matrix[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        int[] pos = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        br.close();
        if(pos[0] == pos[1]) {
            System.out.println(0);
            return;
        }
        --pos[0];
        --pos[1];
        if (pos[1] < pos[0]) {
            int buff = pos[1];
            pos[1] = pos[0];
            pos[0] = buff;
        }
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                if (matrix[i][j] == 1) {
                    if (!map.containsKey(i)) {
                        map.put(i, new HashSet<>());
                    }
                    if (!map.containsKey(j)) {
                        map.put(j, new HashSet<>());
                    }
                    map.get(i).add(j);
                    map.get(j).add(i);
                }
            }
        }

        Deque<Integer> deque = new ArrayDeque<>();
        int[] steps = new int[N];
        deque.add(pos[0]);
        Set<Integer> visited = new HashSet<>();
        while(deque.size() > 0) {
            int start = deque.pollFirst();
            if (map.get(start) != null) {
                for (int i : map.get(start)) {
                    if (i == pos[1]) {
                        System.out.println(steps[start] + 1);
                        return;
                    }
                    if (!visited.contains(i)) {
                        deque.addLast(i);
                        visited.add(i);
                        steps[i] = steps[start]+1;
                    }
                }
            }
        }
        System.out.println(-1);
    }
}
