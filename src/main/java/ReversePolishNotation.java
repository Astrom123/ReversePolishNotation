import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.EmptyStackException;
import java.util.Stack;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;


public class ReversePolishNotation {

    public static Map<String, BinaryOperator<Integer>> operstors = ReversePolishNotation.getOperatorsMap();

    public static void main(String args[]) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                String input = reader.readLine();
                if ("exit".equals(input))
                    return;
                String[] tokens = input.split(" ");
                Integer result = evaluate(tokens);
                if (result != null)
                    System.out.println(result);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static Map<String, BinaryOperator<Integer>> getOperatorsMap() {
        Map<String, BinaryOperator<Integer>> functions = new HashMap<>();
        functions.put("+", Integer::sum);
        functions.put("÷", (Integer x1, Integer x2) -> x2 / x1);
        functions.put("×", (Integer x1, Integer x2) -> x2 * x1);
        functions.put("−", (Integer x1, Integer x2) -> x2 - x1);
        return functions;
    }

    public static Integer evaluate(String[] tokens) {
        Stack<Integer> stack = new Stack<Integer>();
        try {
            for (String token : tokens) {
                if (operstors.containsKey(token))
                    stack.push(operstors.get(token).apply(stack.pop(), stack.pop()));
                else
                    stack.push(Integer.parseInt(token));
            }
        } catch (NumberFormatException | EmptyStackException ex) {
            System.out.println("Bad input");
            return null;
        } catch (ArithmeticException ex) {
            System.out.println("Error: dividing by zero");
            return null;
        }

        if (stack.size() != 1) {
            System.out.println("Incorrect expression");
            return null;
        }

        return stack.pop();
    }
}
