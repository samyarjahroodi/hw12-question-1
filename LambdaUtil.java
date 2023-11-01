import java.math.BigDecimal;
import java.util.*;
import java.util.function.*;

public class LambdaUtil {

    /**
     * Returns {@link Supplier} that always supply "Hello"
     *
     * @return a string supplier
     */
    //1
    public static Supplier<String> helloSupplier() {
        System.out.println("Question 1");
        return () -> "Hello";
    }

    /**
     * Returns a {@link Predicate} of string that checks if string is empty
     *
     * @return a string predicate
     */
    //2
    public static Predicate<String> isEmptyPredicate() {
        System.out.println("Question 2");
        return s -> s.length() <= 0;
    }

    /**
     * Return a {@link Function} that accepts {@link String} and returns that string repeated n time, where n is passed
     * as function argument
     *
     * @return function that repeats Strings
     */
    //3
    public static BiFunction<String, Integer, String> stringMultiplier() {
        System.out.println("Question 3");
        StringBuilder stringBuilder = new StringBuilder();
        return (a, b) -> {
            for (int i = 0; i < b; i++) {
                stringBuilder.append(a);
            }
            return String.valueOf(stringBuilder);
        };
    }


    /**
     * Returns a {@link Function} that converts a {@link BigDecimal} number into a {@link String} that start with
     * a dollar sign and then gets a value
     *
     * @return function that converts adds dollar sign
     */
    //4
    public static Function<BigDecimal, String> toDollarStringFunction() {
        System.out.println("Question 4");
        StringBuilder stringBuilder = new StringBuilder();
        char ch = '$';
        return (a) -> String.valueOf(stringBuilder.append(ch).append(a));
    }

    /**
     * Receives two parameter that represent a range and returns a {@link Predicate<String>} that verifies if string
     * length is in the specified range. E.g. min <= length < max
     *
     * @param min min length
     * @param max max length
     * @return a string predicate
     */
    //5
    public static Predicate<String> lengthInRangePredicate(int min, int max) {
        System.out.println("Question 5");
        return (s -> s.length() > min && s.length() < max);
//        Predicate<String> stringPredicate = new Predicate<String>() {
//            @Override
//            public boolean test(String s) {
//                return s.length() < max && s.length() > min;
//            }
//        };
//        return stringPredicate;
    }

    /**
     * Returns a {@link Supplier} of random integers
     *
     * @return int supplier
     */
    //6
    public static IntSupplier randomIntSupplier() {
        System.out.println("Question 6");
        IntSupplier sup = () -> (int) (Math.random() * 1000);
        IntSupplier sup1 = () -> (int) (Math.random() * 1000);
        System.out.println("first number is: " + sup.getAsInt() + "     second number is: " + sup1.getAsInt());
        System.out.print("Are the above numbers equal? ");
        System.out.println(sup.getAsInt() == sup1.getAsInt());
        return sup;
    }


    /**
     * Returns an {@link IntUnaryOperator} that receives an int as a bound parameter, and returns a random int
     *
     * @return int operation
     */
    //7
    public static IntUnaryOperator boundedRandomIntSupplier() {
        System.out.println("Question 7");
        Random random = new Random();
        int number = random.nextInt(1000);
        System.out.println("The number is : " + number);
        return bound -> number;
    }


    /**
     * Returns {@link IntUnaryOperator} that calculates an integer square
     *
     * @return square operation
     */
    //8
    public static IntUnaryOperator intSquareOperation() {
        System.out.println("Question 8");
        return a -> a * a;
    }

    /**
     * Returns a {@link LongBinaryOperator} sum operation.
     *
     * @return binary sum operation
     */
    //9
    public static LongBinaryOperator longSumOperation() {
        System.out.println("Question 9");
        return Long::sum;
    }

    /**
     * Returns a {@link ToIntFunction<String>} that converts string to integer.
     *
     * @return string to int converter
     */
    //10
    public static ToIntFunction<String> stringToIntConverter() {
        System.out.println("Question 10");
        return Integer::parseInt;
    }

    /**
     * Receives int parameter n, and returns a {@link Supplier} that supplies {@link IntUnaryOperator}
     * that is a function f(x) = n * x
     *
     * @param n a multiplier
     * @return a function supplier
     */
    //11
    public static Supplier<IntUnaryOperator> nMultiplyFunctionSupplier(int n) {
        System.out.println("Question 11");
        return () -> num -> num * n;
    }


    /**
     * Returns {@link Supplier} of {@link Supplier} of {@link Supplier} of {@link String} "WELL DONE".
     *
     * @return a supplier instance
     */
    //12
    public static Supplier<Supplier<Supplier<String>>> trickyWellDoneSupplier() {
        System.out.println("Question 12");
        return () -> () -> () -> "WELL DONE";
    }


    /**
     * Returns a {@link UnaryOperator} that accepts str to str function and returns the same function composed with trim
     *
     * @return function that composes functions with trim() function
     */
    //13
    public static UnaryOperator<Function<String, String>> composeWithTrimFunction() {
        System.out.println("Question 13");
        return (str) -> (str).andThen(String::trim);
    }

    /**
     * Returns a {@link BiFunction} that has three parameters. First is {@link IntUnaryOperator} which is some integer function.
     * Second is {@link IntPredicate} which is some integer condition. And the third is {@link IntUnaryOperator} which is
     * a new composed function that uses provided predicate (second parameter of binary function) to verify its input
     * parameter. If predicate returns {@code true} it applies a provided integer function
     * (first parameter of binary function) and returns a result value, otherwise it returns an element itself.
     *
     * @return a binary function that receiver predicate and function and compose them to create a new function
     */
    //14
    public static BiFunction<IntUnaryOperator, IntPredicate, IntUnaryOperator> functionToConditionalFunction() {
        System.out.println("Question 14");
        return (intUnaryOperator, intPredicate) -> operand -> {
            if (intPredicate.test(operand)) {
                return intUnaryOperator.applyAsInt(operand);
            } else {
                return operand;
            }
        };
    }

    /**
     * Returns a {@link BiFunction} which first parameter is a {@link Map} where key is a function name, and value is some
     * {@link IntUnaryOperator}, and second parameter is a {@link String} which is a function name. If the map contains a
     * function by a given name then it is returned by high order function otherwise an identity() is returned.
     *
     * @return a high-order function that fetches a function from a function map by a given name or returns identity()
     */
    //15
    public static BiFunction<Map<String, IntUnaryOperator>, String, IntUnaryOperator> functionLoader() {
        System.out.println("Question 15");
        return (stringIntUnaryOperatorMap, s) -> {
            if ("increment".equals(s) && stringIntUnaryOperatorMap.containsKey("increment")) {
                return stringIntUnaryOperatorMap.get("increment");
            } else if ("square".equals(s) && stringIntUnaryOperatorMap.containsKey("square")) {
                return stringIntUnaryOperatorMap.get("square");
            } else {
                return operand -> operand;
            }
        };
    }

}
