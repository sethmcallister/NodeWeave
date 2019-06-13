import com.nodeweave.calculator.dto.Calculator;
import com.nodeweave.calculator.dto.Operator;
import org.junit.Test;

public class CalcTest {
    @Test
    public void test() {
        Calculator calculator = new Calculator();
        // going to add 5 and 4
        calculator.addNumber(5);
        calculator.addNumber(4);

        // going to add +
        calculator.addOperator(Operator.ADD);

        System.out.println(calculator.eval() + " should == 9");
    }
}
