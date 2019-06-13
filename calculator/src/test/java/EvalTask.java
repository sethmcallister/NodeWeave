import com.nodeweave.calculator.dto.Operator;
import org.junit.Test;

import java.util.Stack;

public class EvalTask {
    @Test
    public void test() {
        // test basic addition
        System.out.println(eval(new double[]{1d, 1d}, new Operator[]{Operator.ADD}) + " should == 2");
        // test more complex addition
        System.out.println(eval(new double[]{1d, 2d, 3d, 4d}, new Operator[]{Operator.ADD, Operator.ADD, Operator.ADD}) + " should == 10");

        // test basic subtraction
        System.out.println(eval(new double[]{10d, 1d}, new Operator[]{Operator.SUB}) + " should == 9");
        // test more complex subtraction
        System.out.println(eval(new double[]{10d, 3d, 2d, 1d}, new Operator[]{Operator.SUB, Operator.SUB, Operator.SUB}) + " should == 4");

        // test basic multiplication
        System.out.println(eval(new double[]{10d, 10d}, new Operator[]{Operator.MULT}) + " should == 100");
        // test more complex multiplication
        System.out.println(eval(new double[]{10d, 10d, 0d}, new Operator[]{Operator.MULT, Operator.MULT}) + " should == 0");

        // test basic div
        System.out.println(eval(new double[]{10d, 2d}, new Operator[]{Operator.DIV}) + " should == 5");
        // test more complex div
        System.out.println(eval(new double[]{10d, 2d, 1d}, new Operator[]{Operator.DIV, Operator.DIV}) + " should == 5");

        // test basic square
        System.out.println(eval(new double[]{10d}, new Operator[]{Operator.SQUARE}) + " should == 3.16227766017");

        // test quite complex operation in whole
        System.out.println(eval(new double[]{10d, 2d, 5d, 9d, 12d}, new Operator[]{Operator.DIV, Operator.MULT, Operator.SUB, Operator.ADD}) + " should == 28");
    }

    public double eval(double[] numbers, Operator[] operators) {
        Stack<Double> numberStack = new Stack<>();
        // Push it on in reverse order because a stack is a LIFO data structure
        for (int i = numbers.length-1; i >= 0; i--) {
            numberStack.push(numbers[i]);
        }

        Stack<Operator> operatorStack = new Stack<>();
        for (int i = operators.length-1; i >= 0; i--) {
            operatorStack.push(operators[i]);
        }

        double result = 0;
        while (!numberStack.isEmpty()) {
//            System.out.println("numberStackSize == " + numberStack.size());
            if (numberStack.size() == 1) {
                if (!operatorStack.isEmpty()) {
                    Operator operator = operatorStack.pop();
                    if (operator == Operator.SQUARE) {
                        result = Math.sqrt(numberStack.pop());
                        break;
                    }
                }
                result = numberStack.pop();
                break;
            }
            Operator operator = operatorStack.pop();
            Double num1 = numberStack.pop();
            Double num2 = numberStack.pop();

//            System.out.println(String.format("%s %s %s", num1, operator.name(), num2));

            double temp = 0;
            if (operator == Operator.ADD) {
                temp = num1 + num2;
            } else if (operator == Operator.SUB) {
                temp = num1 - num2;
            } else if (operator == Operator.MULT) {
                temp = num1 * num2;
            } else if (operator == Operator.DIV) {
                temp = num1 / num2;
            } else if (operator == Operator.SQUARE) {
                temp = Math.sqrt(num1);
            }
//            System.out.println("temp == " + temp);
            numberStack.push(temp);
//            System.out.println("numberStack == " + numberStack.toString());
        }


//        int result = 0;
//        int temp = 0;
//        Operator operator = null;
//
//        // loop through everything in formula
//        for (int i = 0; i < formula.length; i++) {
//            char c = formula[i]; // current char in formula
//
//            // is a number
//            if (Character.isDigit(c)) {
//                int number = c - '0'; // get the real number value not the ordinal value
//                // loop through all following chars to see if there is a bigger number so 1, 0 == 10 not 1 + 0 = 1
//                while (++i < formula.length && Character.isDigit(formula[i])) { // ++i == increment then assign, i++ == assign then increment
//                    number = number + formula[i] - '0';
//                }
//                i--; // return to last digit
//                System.out.println(number);
//
//                if (operator != null) {
//                    if (operator == Operator.DIV) {
//                        temp = temp / number;
//                    } else if (operator == Operator.MULT) {
//                        temp = temp * number;
//                    }
//                }
//            } else {
//                operator = Operator.findByCode(c);
//            }
//        }
//
////
        return result;
    }

}
