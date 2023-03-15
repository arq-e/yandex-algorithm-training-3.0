package queue;

import java.io.*;

public class TaskD {

    public static void main(String[] args) throws IOException {
        TaskD task = new TaskD();
        task.solve();
    }

    private void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        Heap heap = new Heap();
        for (int i = 0; i < N; i++) {
            String[] command = br.readLine().split(" ");
            if (command[0].equals("0")) {
                heap.add(Integer.parseInt(command[1]));
            } else {
                int res = heap.remove();
                pw.println(res);
            }
        }
        br.close();
        pw.close();
    }
}

class Heap{
    int[] array;
    int lastElement;

    public Heap() {
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
        while (pos*2 + 1 <= lastElement && array[pos] < array[pos*2 + 1]
                || pos*2 + 2 <= lastElement && array[pos] < array[pos*2 + 2]) {
            if (pos*2 + 2 <= lastElement && array[pos*2 + 2] > array[pos*2 + 1]) {
                int buff = array[pos];
                array[pos] = array[pos*2 + 2];
                array[pos*2 + 2] = buff;
                pos  = pos*2 + 2;
            } else if (pos*2 + 2 <= lastElement && array[pos*2 + 1] > array[pos*2 + 2]
                    || array[pos * 2 + 1] > array[pos]) {
                int buff = array[pos];
                array[pos] = array[pos*2 + 1];
                array[pos*2 + 1] = buff;
                pos = pos*2 + 1;
            }
        }
    }

    private void reorderHeapFromLast() {
        int pos = lastElement;
        while (pos > 0 && array[pos] > array[(pos-1)/2]) {
            int buff = array[pos];
            array[pos] = array[(pos-1)/2];
            array[(pos-1)/2] = buff;
            pos = (pos-1)/2;
        }
    }
}
