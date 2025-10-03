import java.util.*;

public class SumOfIntegers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> numbers = new ArrayList<>();

        System.out.println("Enter integers (type 'done' to finish):");

        while (true) {
            String input = sc.next();
            if (input.equalsIgnoreCase("done")) {
                break;
            }
            // Parsing string to int
            int num = Integer.parseInt(input);  
            // Autoboxing: int -> Integer
            numbers.add(num);
        }

        int sum = 0;
        // Enhanced for loop with unboxing
        for (Integer n : numbers) {
            sum += n; // Unboxing: Integer -> int
        }

        System.out.println("Sum of integers: " + sum);
    }
}
