import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TestPolishNotation {

    @Test
    public void testExpr1() {
        String expr = "15 7 1 1 + − ÷ 3 × 2 1 1 + + −";
        Integer result = ReversePolishNotation.evaluate(expr.split(" "));
        assertEquals(result.intValue(), 5);
    }

    @Test
    public void testIntegersDividing() {
        String expr = "1 5 ÷";
        Integer result = ReversePolishNotation.evaluate(expr.split(" "));
        assertEquals(result.intValue(), 0);
    }

    @Test
    public void testManyOperands() {
        String expr = "1 1 1 1 + +";
        Integer result = ReversePolishNotation.evaluate(expr.split(" "));
        assertNull(result);
    }

    @Test
    public void testManyOperators() {
        String expr = "1 1 1 + + + + + + ";
        Integer result = ReversePolishNotation.evaluate(expr.split(" "));
        assertNull(result);
    }

    @Test
    public void testZeroDividing() {
        String expr = "3 0 ÷";
        Integer result = ReversePolishNotation.evaluate(expr.split(" "));
        assertNull(result);
    }

}
