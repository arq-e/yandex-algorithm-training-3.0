package queue;

import java.io.*;
import java.util.stream.Stream;

public class TaskE {

    public static void main(String[] args) throws IOException {
        TaskE task = new TaskE();
        task.solve();
    }

    private void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] nums = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        br.close();
        HeapImpl heap = new HeapImpl();
        for (int i = 0; i < N; ++i) {
            heap.add(nums[i]);
        }
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < N; ++i) {
            pw.print(heap.remove() + " ");
        }
        pw.close();
    }
}

class HeapImpl{
    int[] array;
    int lastElement;

    public HeapImpl() {
        array = new int[1000];
        lastElement = -1;
    }

    public void add(int val) {
        if (lastElement + 1 == array.length) {
            int[] newArray = new int[2*array.length];
            for (int i = 0; i < array.length; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
        array[++lastElement] = val;
        reorderHeapFromLast();
    }

    public int remove() {
        int max = array[0];
        array[0] = array[lastElement--];
        reorderHeapFromFirst();
        return max;
    }

    private void reorderHeapFromFirst() {
        int pos = 0;
        while (pos*2 + 1 <= lastElement && array[pos] > array[pos*2 + 1]
                || pos*2 + 2 <= lastElement && array[pos] > array[pos*2 + 2]) {
            if (pos*2 + 2 <= lastElement && array[pos*2 + 2] < array[pos*2 + 1]) {
                int buff = array[pos];
                array[pos] = array[pos*2 + 2];
                array[pos*2 + 2] = buff;
                pos  = pos*2 + 2;
            } else if (pos*2 + 2 <= lastElement && array[pos*2 + 1] < array[pos*2 + 2]
                    || array[pos * 2 + 1] < array[pos]) {
                int buff = array[pos];
                array[pos] = array[pos*2 + 1];
                array[pos*2 + 1] = buff;
                pos = pos*2 + 1;
            }
        }
    }

    private void reorderHeapFromLast() {
        int pos = lastElement;
        while (pos > 0 && array[pos] < array[(pos-1)/2]) {
            int buff = array[pos];
            array[pos] = array[(pos-1)/2];
            array[(pos-1)/2] = buff;
            pos = (pos-1)/2;
        }
    }
}
