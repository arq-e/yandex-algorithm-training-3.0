package warmup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Stream;

public class TaskF {
    
    public static void main(String[] args) throws IOException {
        TaskF task = new TaskF();
        task.solve();
    }

    private void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());
        Map<Integer, Integer> sectorsMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int[] sector = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Iterator<Integer> iterator = sectorsMap.keySet().iterator();
            while (iterator.hasNext()) {
                int start = iterator.next();
                if (start <= sector[0] && sectorsMap.get(start) >= sector[0] || start <= sector[1]  && start >= sector[0]) {
                    iterator.remove();
                }
            }
            sectorsMap.put(sector[0], sector[1]);
        }
        System.out.println(sectorsMap.size());
    }
}
