package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class TaskD {

    public static void main(String[] args) {
        TaskD task = new TaskD();
        task.solve();
    }

    private void solve() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        List<Integer> defaultOrder = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            defaultOrder.add(sc.nextInt());
        }
        Stack<Integer> deadLock = new Stack<>();
        int targetCarrige = 1;
        boolean carriageReorderingPossible = true;
        while (targetCarrige <= N) {
            if (defaultOrder.size() > 0 && defaultOrder.get(0) == targetCarrige) {
                defaultOrder.remove(0);
                ++targetCarrige;
            } else if (deadLock.size() > 0 && deadLock.peek() == targetCarrige){
                deadLock.pop();
                ++targetCarrige;
            } else if (defaultOrder.size() > 0){
                deadLock.push(defaultOrder.get(0));
                defaultOrder.remove(0);
            } else {
                carriageReorderingPossible = false;
                break;
            }
        }
        if (carriageReorderingPossible) System.out.println("YES");
        else System.out.println("NO");
    }
}
