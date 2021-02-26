/*
 * MIT License
 *
 * Copyright (c) 2019 Codepenguin.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package org.codepenguin.java.socket.server.example;

import org.junit.jupiter.api.Test;

import static java.lang.Double.POSITIVE_INFINITY;
import static org.codepenguin.java.socket.server.example.ArithmeticOperator.*;
import static org.codepenguin.java.socket.server.example.NumberUtils.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for {@link BinaryOperation}.
 *
 * @author Jorge Alfonso Garcia Espinosa
 * @version 1.0-SNAPSHOT
 * @since 1.8
 */
class BinaryOperationTest {

    @Test
    void applyWhenAdditionSuccess() {
        assertEquals(INT_7, new BinaryOperation(INT_3, ADDITION, INT_4).apply());
    }

    @Test
    void applyWhenSubtractionSuccess() {
        assertEquals(-INT_1, new BinaryOperation(INT_3, SUBTRACTION, INT_4).apply());
    }

    @Test
    void applyWhenMultiplicationSuccess() {
        assertEquals(INT_12, new BinaryOperation(INT_3, MULTIPLICATION, INT_4).apply());
    }

    @Test
    void applyWhenDivisionSuccess() {
        assertEquals(INT_6, new BinaryOperation(INT_30, DIVISION, INT_5).apply());
    }

    @Test
    void applyWhenDivisionWithSecondOperandZeroReturnsSuccessInfinity() {
        assertEquals(POSITIVE_INFINITY, new BinaryOperation(INT_30, DIVISION, INT_0).apply());
    }

    @Test
    void getFirstOperand() {
        assertEquals(INT_3, new BinaryOperation(INT_3, ADDITION, INT_4).getFirstOperand());
    }

    @Test
    void getSymbol() {
        assertEquals(ADDITION, new BinaryOperation(INT_3, ADDITION, INT_4).getSymbol());
    }

    @Test
    void getSecondOperand() {
        assertEquals(INT_4, new BinaryOperation(INT_3, ADDITION, INT_4).getSecondOperand());
    }

    @Test
    void equalsSuccess() {
        assertEquals(new BinaryOperation(INT_3, ADDITION, INT_4), new BinaryOperation(INT_3, ADDITION, INT_4));
    }

    @Test
    void hashCodeSuccess() {
        assertNotEquals(INT_0, new BinaryOperation(INT_3, ADDITION, INT_4).hashCode());
    }

    @Test
    void toStringSuccess() {
        assertEquals("BinaryOperation(firstOperand=3.0, symbol=ADDITION, secondOperand=4.0)",
                new BinaryOperation(INT_3, ADDITION, INT_4).toString());
    }
}