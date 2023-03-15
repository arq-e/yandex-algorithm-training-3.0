package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

public class TaskB {

    public static void main(String[] args) throws IOException {
        TaskB task = new TaskB();
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

        List<Integer> res = bfs(map, pos);
        if (res == null) {
            System.out.println(-1);
        } else {
            System.out.println(res.size()-1);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < res.size(); ++i) {
                sb.append((res.get(i)+1) + " ");
            }
            System.out.println(sb.toString().trim());
        }
    }

    private List<Integer> bfs(Map<Integer, Set<Integer>> map, int[] pos) {

        Deque<Integer> deque = new ArrayDeque<>();
        List<Integer> sequence = new ArrayList<>();
        sequence.add(pos[0]);
        deque.add(pos[0]);
        Map<Integer, List<Integer>> routeMap = new HashMap<>();
        routeMap.put(pos[0], new ArrayList<>());
        routeMap.get(pos[0]).add(pos[0]);
        while (deque.size() > 0) {
            int curr = deque.pollFirst();
            if (map.get(curr) != null) {
                for (int i : map.get(curr)) {
                    if (!routeMap.containsKey(i)) {
                        routeMap.put(i, new ArrayList<>(routeMap.get(curr)));
                        routeMap.get(i).add(i);
                        if (i == pos[1]) {
                            return routeMap.get(i);
                        } else deque.addLast(i);
                    }
                }
            }
        }
        return null;
    }
}
