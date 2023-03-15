package warmup;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class TaskC {

    public static void main(String[] args) throws IOException {
        TaskC task = new TaskC();
        task.solve();
    }

    private void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numOfStickers = Integer.parseInt(br.readLine());
        int[] stickers = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int numOfCollectors = Integer.parseInt(br.readLine());
        int[] collectorsMin = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Set<Integer> diegoStickers = new HashSet<>();
        for (int i = 0; i < numOfStickers; i++) {
            diegoStickers.add(stickers[i]);
        }
        List<Integer> ordered = new ArrayList<>(diegoStickers);
        Collections.sort(ordered);
        Map<Integer, Integer> cache = new HashMap<>();
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < numOfCollectors; i++) {
            int minValue = collectorsMin[i];
            if (cache.containsKey(minValue)) {
                pw.println(cache.get(minValue));
            } else {
                int res = binarySearch(ordered, minValue);
                pw.println(res);
                cache.put(minValue, res);
            }
        }
        pw.close();
    }

    private int binarySearch(List<Integer> list, int minValue) {
        if (minValue == 0) {
            return 0;
        } else if (list.size() == 1) {
            if (minValue > list.get(0)) return 1;
            return 0;
        } else if (minValue > list.get(list.size()-1)) {
            return list.size();
        }
        int start = 0;
        int end = list.size()-1;
        while (start < end) {
            int med = (start + end) / 2;
            if (list.get(med) > minValue) {
                end = med;
            } else if (list.get(med) == minValue) {
                end = med;
                break;
            } else start = med + 1;
        }
        return Math.max(start, end);
    }
}
