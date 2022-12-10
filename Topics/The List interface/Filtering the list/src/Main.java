import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> list = new ArrayList<>();
        String[] input = scanner.nextLine().split(" ");
        for (int i = 1; i < input.length; i += 2 ) {
            list.add(Integer.parseInt(input[i]));
        }

        for (int i = list.size() - 1; i >= 0; i--) {
            System.out.print(list.get(i) + " ");
        }

        // put your code here
    }
}