package warmup;

import java.util.Scanner;

public class TaskD {

    public static void main(String[] args) {
        TaskD task = new TaskD();
        task.solve();
    }

    private void solve() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        int rowPetya = sc.nextInt();
        int placePetya = sc.nextInt();
        int posPetya = (rowPetya - 1) * 2 + placePetya;
        if (K >= N) {
            System.out.println("-1");
        } else {
            int posPositive = posPetya + K;
            int posNegative = posPetya - K;
            if (posPositive > N && posNegative < 1) System.out.println("-1");
            else if (posNegative < 1) {
                showRowAndPlace(posPositive);
            } else if (posPositive > N) {
                showRowAndPlace(posNegative);
            } else {
                int rowsPositive = (posPositive+1) / 2;
                int rowsNegative = (posNegative+1) / 2;
                if (rowPetya - rowsNegative < rowsPositive - rowPetya) {
                    showRowAndPlace(posNegative);
                } else {
                    showRowAndPlace(posPositive);
                }
            }
        }
    }

    private void showRowAndPlace(int pos) {
        System.out.println(((pos+1) / 2) + " " + ((pos + 1) % 2 + 1));
    }
}
