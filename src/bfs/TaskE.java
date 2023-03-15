package bfs;

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
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[][] lines = new int[M][];
        for (int i = 0; i < M; ++i) {
            int[] parsedString = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt)
                    .toArray();
            lines[i] = new int[parsedString[0]];
            for (int j = 1; j < parsedString.length; ++j) {
                lines[i][j-1] = parsedString[j];
            }
        }
        int[] pos = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(bfs(N, M, lines, pos));
    }

    private int bfs(int N, int M, int[][] lines, int[] pos) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < lines.length; ++i) {
            for (int j = 0; j < lines[i].length; ++j) {
                if (!map.containsKey(lines[i][j])) {
                    map.put(lines[i][j], new ArrayList<>());
                }
                map.get(lines[i][j]).add(i);
            }
        }
        int[] pathLengths = new int[M];
        Arrays.fill(pathLengths, -1);
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i : map.get(pos[0])) {
            deque.add(i);
            pathLengths[i] = 0;
        }
        while (deque.size() > 0) {
            int curr = deque.pollFirst();
            for (int i = 0; i < lines[curr].length; ++i) {
                if (lines[curr][i] == pos[1]) return pathLengths[curr];
                else if (map.get(lines[curr][i]).size() > 1) {
                    for (int j : map.get(lines[curr][i])) {
                        if (pathLengths[j] == -1 && j != curr) {
                            deque.addLast(j);
                            pathLengths[j] = pathLengths[curr] + 1;
                        }
                    }
                }
            }
        }
        return -1;
    }
}
