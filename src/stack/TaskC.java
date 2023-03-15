package stack;

import java.util.Scanner;
import java.util.Stack;

public class TaskC {

    public static void main(String[] args) {
        TaskC task = new TaskC();
        task.solve();
    }

    private void solve() {
        Scanner sc = new Scanner(System.in);
        String[] sequence = sc.nextLine().split(" ");
        Stack<Integer> stack = new Stack<>();
        boolean noErrors = true;
        for (int i = 0; i < sequence.length; i++) {
            switch (sequence[i]) {
                case "+":
                    noErrors = add(stack);
                    break;
                case "-":
                    noErrors = sub(stack);
                    break;
                case "*":
                    noErrors = multiply(stack);
                    break;
                default:
                    int num = Integer.parseInt(sequence[i]);
                    stack.push(num);
            }
            if (!noErrors) break;
        }
        System.out.println(stack.pop());
    }

    private boolean multiply(Stack<Integer> stack) {
        if (stack.size() >= 2) {
            int num1 = stack.pop();
            int num2 = stack.pop();
            num1 *= num2;
            stack.push(num1);
            return true;
        } else return false;
    }

    private boolean sub(Stack<Integer> stack) {
        if (stack.size() >= 2) {
            int num1 = stack.pop();
            int num2 = stack.pop();
            num2 -= num1;
            stack.push(num2);
            return true;
        } else return false;
    }

    public boolean add(Stack<Integer> stack) {
        if (stack.size() >= 2) {
            int num1 = stack.pop();
            int num2 = stack.pop();
            num1 += num2;
            stack.push(num1);
            return true;
        } else return false;
    }
}
