package com.aleksandrov.fibonacci.util;

/**
 * @author DAleksandrov
 * @created 21.09.2022
 **/
public final class FibonacciUtil {

    private FibonacciUtil(){}

    public static String calculateFibonacciSequence(int firstNumber, int secondNumber, int rangeNumber){
        StringBuilder builder = new StringBuilder(String.format("First number: %d, Second number: %d, Sequence: ", firstNumber, secondNumber));
        if (rangeNumber == 0){
            return builder.append(0).toString();
        }
        int sum;
        for(int i = 0; i < rangeNumber; i++){
            sum = firstNumber + secondNumber;
            firstNumber = secondNumber;
            secondNumber = sum;
            builder.append(sum).append(" ");
        }
        return builder.toString();
    }
}
