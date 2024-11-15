package org.example.polynomial;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polynomial {
    private Map<Integer, Double> terms;
    public Polynomial() {
        terms = new HashMap<>();
    }
    public static Map<Integer, Double> regexMatcher(String expression) {
        Map<Integer, Double> polyMap = new HashMap<>();
        String polynomialPattern = "([+-]?\\d*x\\^(-?\\d+)?|[+-]?\\d+)";
        Pattern pattern = Pattern.compile(polynomialPattern);
        Matcher matcher = pattern.matcher(expression);

        while (matcher.find()) {
            String term = matcher.group();
            double coefficient = 0.0;
            int exponent = 0;
            if (term.matches("[+-]?\\d+")) {
                coefficient = Double.parseDouble(term);
            } else if (term.matches("[+-]?\\d*x")) {
                coefficient = term.contains("x") ? Double.parseDouble(term.replace("x", "")) : 1.0;
            } else if (term.matches("[+-]?\\d*x\\^(-?\\d+)")) {
                String[] parts = term.split("x\\^");
                coefficient = Double.parseDouble(parts[0]);
                exponent = Integer.parseInt(parts[1]);
            }
            polyMap.put(exponent, polyMap.getOrDefault(exponent, 0.0) + coefficient);
        }
        return polyMap;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type a polynom: ");
        String expression = scanner.nextLine();
        Map<Integer, Double> polyMap = regexMatcher(expression);
        System.out.println("Parsed polynom: " + polyMap);
        scanner.close();
    }
}
