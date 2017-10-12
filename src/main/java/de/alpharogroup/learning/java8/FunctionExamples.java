package de.alpharogroup.learning.java8;

import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.UnaryOperator;

/**
 * The class {@link FunctionExamples} shows the examples of the blog
 * <a href="https://dzone.com/articles/functional-programming-java-8">Functional
 * Programming with Java 8 Functions</a>.
 */
public class FunctionExamples {

	public static void main(String[] args) throws Exception {
		howFunctionWorks();
		howFunctionInFunctionsWorks();
		multiplyFunctionWithAddingFunction();
		howFunctionCompositionWorks();
		howFuntionCurryingWorks();
		howUnaryOperatorWorks();
		howIntFunctionWorks();

	}

	private static void howIntFunctionWorks() {

		IntFunction<IntFx> sum = x -> y -> x + y;

		IntFx sum10 = sum.apply(10);

		Integer result = sum10.apply(4); // yields 14
		System.out.println("=============================");
		System.out.println("sum = x -> y -> x + y; => \nsum10 = sum.apply(10); => \nsum10.apply(4) => \n" + result);
	}

	private static UnaryOperator<Integer> howUnaryOperatorWorks() {
		Function<Integer, UnaryOperator<Integer>> sum = x -> y -> x + y;

		UnaryOperator<Integer> sum10 = sum.apply(10);

		Integer result = sum10.apply(5);
		System.out.println("=============================");
		System.out.println("sum = x -> y -> x + y; => \nsum10 = sum.apply(10); => \nsum10.apply(5) => \n" + result);
		return sum10;
	}

	private static void howFuntionCurryingWorks() {
		Function<Integer, Function<Integer, Integer>> sum = x -> y -> x + y;
		Function<Integer, Integer> plus10 = sum.apply(10);
		Integer result = plus10.apply(5); // yields 15
		System.out.println("=============================");
		System.out.println("sum = x -> y -> x + y; => \nplus10 = sum.apply(10); => \nplus10.apply(5) => \n" + result);
	}

	private static void howFunctionCompositionWorks() {
		BinaryOperator<Integer> sum = (a, b) -> a + b;
		Integer result = sum.apply(1, 2); // yields 3
		System.out.println("=============================");
		System.out.println("sum.apply(1,2) => \n" + result);

		BinaryOperator<Function<Integer, Integer>> compose = (f, g) -> x -> g.apply(f.apply(x));

		Function<Integer, Integer> add1 = x -> x + 1;
		Function<Integer, Integer> mul3 = x -> x * 3;

		Function<Integer, Integer> h = compose.apply(add1, mul3);

		result = h.apply(10); // yields 33
		System.out.println("=============================");
		System.out.println(" h = compose.apply(add1,mul3);h.apply(10)  => \n" + result);

		h = mul3.compose(add1);
		result = h.apply(10); // yields 33
		System.out.println("=============================");
		System.out.println(" h = mul3.compose(add1);h.apply(10)  => \n" + result);
	}

	private static void multiplyFunctionWithAddingFunction() {
		Function<Integer, Integer> add1 = x -> x + 1;
		Function<Integer, Integer> mul3 = x -> x * 3;

		Integer x = 10;
		Integer result = mul3.apply(add1.apply(x)); // yields 33
		System.out.println("=============================");
		System.out.println("mul3.apply(add1.apply(10)) => \n" + result);
	}

	private static void howFunctionInFunctionsWorks() {
		Function<Integer, Function<Integer, Integer>> makeAdder = x -> y -> x + y;

		Function<Integer, Integer> add1 = makeAdder.apply(1);
		Function<Integer, Integer> add2 = makeAdder.apply(2);
		Integer result;

		System.out.println("=============================");
		result = add1.apply(1);
		System.out.println("add1.apply(1) => \n" + result);
		result = add1.apply(2);
		System.out.println("add1.apply(2) => \n" + result);
		System.out.println("=============================");
		result = add2.apply(1);
		System.out.println("add2.apply(1) => \n" + result);
		result = add2.apply(2);
		System.out.println("add2.apply(2) => \n" + result);

		makeAdder = Utils::adder;

		add1 = makeAdder.apply(1);
		add2 = makeAdder.apply(2);

		System.out.println("=============================");
		result = add1.apply(1);
		System.out.println("add1.apply(1) => \n" + result);
		result = add1.apply(2);
		System.out.println("add1.apply(2) => \n" + result);
		System.out.println("=============================");
		result = add2.apply(1);
		System.out.println("add2.apply(1) => \n" + result);
		result = add2.apply(2);
		System.out.println("add2.apply(2) => \n" + result);
	}

	private static void howFunctionWorks() {
		Function<Integer, Integer> add = x -> x + 1;
		Function<String, String> concat = x -> x + 1;

		Integer two = add.apply(1); // yields 2
		System.out.println("add.apply(1) => \n" + two);

		String answer = concat.apply("0 + 1 = "); // yields "0 + 1 = 1"
		System.out.println("concat.apply(\"0 + 1 = \") => \n" + answer);

		Function<Integer, Integer> add1 = Utils::add1;

		Function<String, String> concat1 = Utils::concat1;

		two = add1.apply(1); // yields 2
		System.out.println("add1.apply(1) => \n" + two);

		answer = concat1.apply("0 + 1 = "); // yields "0 + 1 = 1"
		System.out.println("concat1.apply(\"0 + 1 = \") => \n" + answer);
	}
}
