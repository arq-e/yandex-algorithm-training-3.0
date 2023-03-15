package stack;

import java.util.Scanner;
import java.util.Stack;

public class TaskB {

    public static void main(String[] args) {
        TaskB task = new TaskB();
        task.solve();
    }

    private void solve() {
        Scanner sc = new Scanner(System.in);
        String sequence = sc.nextLine();
        Stack<Character> stack = new Stack<>();
        boolean rightSequence = true;
        for (int i = 0; i < sequence.length(); i++) {
            if (sequence.charAt(i) == '(' || sequence.charAt(i) == '[' || sequence.charAt(i)=='{') {
                stack.push(sequence.charAt(i));
            } else {
                if (stack.size() > 0) {
                    char stackHead = stack.peek();
                    if (sequence.charAt(i) ==')' && stackHead == '(' || sequence.charAt(i) == ']' && stackHead == '['
                            || sequence.charAt(i) =='}' && stackHead == '{') {
                        stack.pop();
                    } else {
                        rightSequence = false;
                        break;
                    }
                } else {
                    rightSequence = false;
                    break;
                }
            }
        }
        if (rightSequence && stack.size() == 0) {
            System.out.println("yes");
        } else System.out.println("no");
    }
}
