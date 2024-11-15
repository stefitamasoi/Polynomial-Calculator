package org.example.polynomial;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Operation {
    public static Map<Integer, Double> addPolynomials(Map<Integer, Double> polynomial1, Map<Integer, Double> polynomial2) {
        Map<Integer, Double> result = new HashMap<>(polynomial1);
        for (Map.Entry<Integer, Double> term : polynomial2.entrySet()) {
            int exponent = term.getKey();
            double coefficient = term.getValue();
            result.put(exponent, result.getOrDefault(exponent, 0.0) + coefficient);
        }
        return result;
    }

    public static Map<Integer, Double> subtractPolynomials(Map<Integer, Double> polynomial1, Map<Integer, Double> polynomial2) {
        Map<Integer, Double> result = new HashMap<>(polynomial1);
        for (Map.Entry<Integer, Double> term : polynomial2.entrySet()) {
            int exponent = term.getKey();
            double coefficient = term.getValue();
            result.put(exponent, result.getOrDefault(exponent, 0.0) - coefficient);
        }
        return result;
    }

    public static Map<Integer, Double> multiplyPolynomials(Map<Integer, Double> polynomial1, Map<Integer, Double> polynomial2) {
        Map<Integer, Double> result = new HashMap<>();

        for (Map.Entry<Integer, Double> term1 : polynomial1.entrySet()) {
            for (Map.Entry<Integer, Double> term2 : polynomial2.entrySet()) {
                int exponent = term1.getKey() + term2.getKey();
                double coefficient = term1.getValue() * term2.getValue();
                result.put(exponent, result.getOrDefault(exponent, 0.0) + coefficient);
            }
        }
        return result;
    }
    public static Map<Integer, Double> dividePolynomials(Map<Integer, Double> dividend, Map<Integer, Double> divisor) {
        if (divisor.isEmpty() || divisor.containsKey(0) && divisor.get(0) == 0.0) {
            throw new IllegalArgumentException("Division by zero!!!");
        }

        Map<Integer, Double> quotient = new HashMap<>();
        Map<Integer, Double> remainder = new HashMap<>(dividend);

        dividend = orderPolynomial(dividend);
        divisor = orderPolynomial(divisor);

        while (!remainder.isEmpty() && getMaxExponent(remainder) >= getMaxExponent(divisor)) {
            int maxExponentRemainder = getMaxExponent(remainder);
            int maxExponentDivisor = getMaxExponent(divisor);

            double firstDividendCoefficient = remainder.getOrDefault(maxExponentRemainder, 0.0);
            double firstDivisorCoefficient = divisor.getOrDefault(maxExponentDivisor, 0.0);
            int exponentDifference = maxExponentRemainder - maxExponentDivisor;

            double termQuotient = firstDividendCoefficient / firstDivisorCoefficient;
            quotient.put(exponentDifference, termQuotient);

            Map<Integer, Double> termResult = multiplyPolynomials(createTermMap(exponentDifference, termQuotient), divisor);
            remainder = subtractPolynomials(remainder, termResult);
        }
        return quotient;
    }

    private static Map<Integer, Double> createTermMap(int exponent, double coefficient) {
        Map<Integer, Double> termMap = new HashMap<>();
        termMap.put(exponent, coefficient);
        return termMap;
    }
    private static Map<Integer, Double> orderPolynomial(Map<Integer, Double> polynomial) {
        Map<Integer, Double> orderedPolynomial = new HashMap<>();
        polynomial.entrySet().stream()
                .sorted(Map.Entry.<Integer, Double>comparingByKey().reversed())
                .forEachOrdered(entry -> orderedPolynomial.put(entry.getKey(), entry.getValue()));
        return orderedPolynomial;
    }
    public static Map<Integer, Double> derivativePolynomials(Map<Integer, Double> polynomial) {
        Map<Integer, Double> derivative = new HashMap<>();
        for (Map.Entry<Integer, Double> term : polynomial.entrySet()) {
            int exponent = term.getKey();
            double coefficient = term.getValue();
            if (exponent > 0) {
                derivative.put(exponent - 1, coefficient * exponent);
            }
        }
        return derivative;
    }

    public static Map<Integer, Double> integratePolynomials(Map<Integer, Double> polynomial) {
        Map<Integer, Double> integral = new HashMap<>();

        for (Map.Entry<Integer, Double> term : polynomial.entrySet()) {
            int exponent = term.getKey();
            double coefficient = term.getValue();
            double integratedCoefficient = coefficient / (exponent + 1);
            integral.put(exponent + 1, integratedCoefficient);
        }
        return integral;
    }
    public static String performOperation(OperationType operationType, String polynomial1, String polynomial2) {
        try {
            Map<Integer, Double> polyMap1 = Polynomial.regexMatcher(polynomial1);
            Map<Integer, Double> polyMap2 = Polynomial.regexMatcher(polynomial2);

            Map<Integer, Double> result;
            switch (operationType) {
                case ADD:
                    result = addPolynomials(polyMap1, polyMap2);
                    break;
                case SUBTRACT:
                    result = subtractPolynomials(polyMap1, polyMap2);
                    break;
                case MULTIPLY:
                    result = multiplyPolynomials(polyMap1, polyMap2);
                    break;
                case DIVIDE:
                    result = dividePolynomials(polyMap1, polyMap2);
                    break;
                case DERIVATE:
                    result = derivativePolynomials(polyMap1);
                    break;
                case INTEGRATE:
                    result = integratePolynomials(polyMap1);
                    break;
                default:
                    result = new HashMap<>();
                    break;
            }
            return toString(result);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    private static String toString(Map<Integer, Double> polynomial) {
        StringBuilder polynomialString = new StringBuilder();

        boolean isFirstTerm = true;
        for (int exponent = getMaxExponent(polynomial); exponent >= 0; exponent--) {
            double coefficient = polynomial.getOrDefault(exponent, 0.0);

            if (coefficient != 0) {
                if (!isFirstTerm) {
                    polynomialString.append(" ");
                    polynomialString.append(coefficient >= 0 ? "+" : "-");
                    polynomialString.append(" ");
                } else {
                    if (coefficient < 0) {
                        polynomialString.append("-");
                    }
                    isFirstTerm = false;
                }
                polynomialString.append(Math.abs(coefficient));
                if (exponent > 0) {
                    polynomialString.append("x");
                    if (exponent > 1) {
                        polynomialString.append("^").append(exponent);
                    }
                }
            }
        }
        if (polynomialString.length() == 0) {
            polynomialString.append("0");
        }

        return polynomialString.toString();
    }
    private static int getMaxExponent(Map<Integer, Double> polynomial) {
        int maxExponent = Integer.MIN_VALUE;
        for (int exponent : polynomial.keySet()) {
            if (exponent > maxExponent) {
                maxExponent = exponent;
            }
        }
        return maxExponent;
    }
}
