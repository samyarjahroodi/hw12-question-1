import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.function.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("|------DEVELOPED BY SAMYAR JAHROODI------|");

        //1
        Supplier<String> helloSupplier = LambdaUtil.helloSupplier();
        System.out.println(helloSupplier.get());//Hello

        //2
        Predicate<String> isEmptyPredicate = LambdaUtil.isEmptyPredicate();
        System.out.println(isEmptyPredicate.test("java"));//false
        System.out.println(isEmptyPredicate.test(""));//true

        //3
        BiFunction<String, Integer, String> stringMultiplier = LambdaUtil.stringMultiplier();
        System.out.println(stringMultiplier.apply("Hi", 3));//HiHiHi

        //4
        Function<BigDecimal, String> toDollarStringFunction = LambdaUtil.toDollarStringFunction();
        String tenDollarStr = toDollarStringFunction.apply(BigDecimal.TEN.setScale(2));
        System.out.println(tenDollarStr);//$10.00

        //5
        Predicate<String> lengthInRangePredicate = LambdaUtil.lengthInRangePredicate(4, 8);
        System.out.println(lengthInRangePredicate.test("Hello world"));//false

        //6
        IntSupplier randomIntSupplier = LambdaUtil.randomIntSupplier();
        int firstValue = randomIntSupplier.getAsInt();
        int secondValue = randomIntSupplier.getAsInt();
        System.out.println(firstValue == secondValue);//false

        //7
        IntUnaryOperator boundedRandomIntSupplier = LambdaUtil.boundedRandomIntSupplier();
        int randomIntLessThan1000 = boundedRandomIntSupplier.applyAsInt(1000);
        System.out.println(randomIntLessThan1000 < 1000);//true

        //8
        IntUnaryOperator squareOperation = LambdaUtil.intSquareOperation();
        System.out.println(squareOperation.applyAsInt(4));//16

        //9
        LongBinaryOperator sumOperation = LambdaUtil.longSumOperation();
        System.out.println(sumOperation.applyAsLong(5, -10));//-5

        //10
        ToIntFunction<String> stringToIntConverter = LambdaUtil.stringToIntConverter();
        int num = stringToIntConverter.applyAsInt("234");
        System.out.println(num);//234

        //11
        Supplier<IntUnaryOperator> fiveMultiplyFunctionSupplier = LambdaUtil.nMultiplyFunctionSupplier(5);
        IntUnaryOperator multiplyByFiveOperation = fiveMultiplyFunctionSupplier.get();
        int result = multiplyByFiveOperation.applyAsInt(11);
        System.out.println(result);//11 * 5 => 55

        //12
        Supplier<Supplier<Supplier<String>>> wellDoneSupplier = LambdaUtil.trickyWellDoneSupplier();
        System.out.println(wellDoneSupplier.get().get().get());//WELL DONE!

        //13
        UnaryOperator<Function<String, String>> composeWithTrimFunction = LambdaUtil.composeWithTrimFunction();
        Function<String, String> toLowerWithTrim = composeWithTrimFunction.apply(String::toLowerCase);
        System.out.println(toLowerWithTrim.apply("  Hey "));//hey

        //extra points
        //14
        BiFunction<IntUnaryOperator, IntPredicate, IntUnaryOperator> intFunctionToConditionalIntFunction
                = LambdaUtil.functionToConditionalFunction();
        IntUnaryOperator abs = intFunctionToConditionalIntFunction.apply(a -> -a, a -> a < 0);
        System.out.println(abs.applyAsInt(-5));//5

        //15
        BiFunction<Map<String, IntUnaryOperator>, String, IntUnaryOperator> functionLoader = LambdaUtil.functionLoader();
        Map<String, IntUnaryOperator> functionMap = new HashMap<>();
        functionMap.put("increment", x -> x + 1);
        functionMap.put("square", x -> x * x);

        IntUnaryOperator incrementFunction = functionLoader.apply(functionMap, "increment");
        IntUnaryOperator squareFunction = functionLoader.apply(functionMap, "square");
        IntUnaryOperator identityFunction = functionLoader.apply(functionMap, "none");

        System.out.println(incrementFunction.applyAsInt(4));//5
        System.out.println(squareFunction.applyAsInt(3));//9
        System.out.println(identityFunction.applyAsInt(10));//10

    }

}
