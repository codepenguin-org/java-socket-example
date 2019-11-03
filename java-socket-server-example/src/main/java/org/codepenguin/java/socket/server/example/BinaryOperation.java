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

import lombok.AllArgsConstructor;
import lombok.Data;

import static java.lang.String.join;
import static java.lang.String.valueOf;
import static org.codepenguin.java.socket.server.example.NumberUtility.isInteger;

/**
 * Binary operation.
 *
 * @author Jorge Alfonso Garcia Espinosa
 * @version 1.0-SNAPSHOT
 * @since 1.8
 */
@Data
@AllArgsConstructor
final class BinaryOperation {

    private static final String PRINT_DELIMITER = " ";
    private static final String EQUALS = "=";

    private final float firstOperand;
    private final ArithmeticOperator symbol;
    private final float secondOperand;

    float apply() {
        switch (symbol) {
            case ADDITION:
                return firstOperand + secondOperand;
            case SUBTRACTION:
                return firstOperand - secondOperand;
            case MULTIPLICATION:
                return firstOperand * secondOperand;
            case DIVISION:
                return firstOperand / secondOperand;
        }
        throw new AssertionError("Unknown symbol: " + symbol);
    }

    String print(boolean withResult) {
        String text = join(PRINT_DELIMITER, print(firstOperand), valueOf(symbol.getSymbol()), print(secondOperand));
        return withResult ? join(PRINT_DELIMITER, text, EQUALS, print(apply())) : text;
    }

    private String print(float f) {
        return isInteger(f) ? valueOf((int) f) : valueOf(f);
    }
}
