package org.example;
import java.util.Map;

import org.example.polynomial.Operation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class OperationsTest {
    @Test
    public void testAddPolynomials() {
        Map<Integer, Double> polynomial1 = Map.of(3, 2.0, 2, 1.0, 1, -3.0, 0, 4.0);
        Map<Integer, Double> polynomial2 = Map.of(2, -1.0, 1, 2.0, 0, 3.0);
        Map<Integer, Double> result = Operation.addPolynomials(polynomial1, polynomial2);
        Map<Integer, Double> expected = Map.of(3, 2.0, 2, 0.0, 1, -1.0, 0, 7.0);
        assertEquals(expected, result);
    }

    @Test
    public void testSubtractPolynomials() {
        Map<Integer, Double> polynomial1 = Map.of(3, 2.0, 2, 1.0, 1, -3.0, 0, 4.0);
        Map<Integer, Double> polynomial2 = Map.of(2, -1.0, 1, 2.0, 0, 3.0);
        Map<Integer, Double> result = Operation.subtractPolynomials(polynomial1, polynomial2);
        Map<Integer, Double> expected = Map.of(3, 2.0, 2, 2.0, 1, -5.0, 0, 1.0);
        assertEquals(expected, result);
    }

    @Test
    public void testMultiplyPolynomials() {
        Map<Integer, Double> polynomial1 = Map.of(2, 2.0, 1, -1.0, 0, 3.0);
        Map<Integer, Double> polynomial2 = Map.of(1, 3.0, 0, -2.0);
        Map<Integer, Double> result = Operation.multiplyPolynomials(polynomial1, polynomial2);
        Map<Integer, Double> expected = Map.of(3, 6.0, 2, -2.0, 1, -11.0, 0, 9.0);
        assertEquals(expected, result);
    }

//    @Test
//    public void testDerivative() {
//        Map<Integer, Double> polynomial = Map.of(3, 2.0, 2, 1.0, 1, -3.0, 0, 4.0);
//        Map<Integer, Double> derivative = Operation.derivativePolynomials(polynomial);
//        Map<Integer, Double> expected = Map.of(2, 6.0, 1, -6.0, 0, 4.0);
//        assertEquals(expected, derivative);
//    }
//
//    @Test
//    public void testIntegrate() {
//        Map<Integer, Double> polynomial = Map.of(3, 2.0, 2, 1.0, 1, -3.0, 0, 4.0);
//        Map<Integer, Double> integral = Operation.integratePolynomials(polynomial);
//        Map<Integer, Double> expected = Map.of(4, 0.5, 3, 0.66, 2, -1.5, 1, 4.0, 0, 0.0);
//        assertEquals(expected, integral);
//    }
}
