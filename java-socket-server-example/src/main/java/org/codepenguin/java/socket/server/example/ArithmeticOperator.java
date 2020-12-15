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

import java.text.MessageFormat;

/**
 * Arithmetic operator.
 *
 * @author Jorge Alfonso Garcia Espinosa
 * @version 1.0-SNAPSHOT
 * @since 1.8
 */
enum ArithmeticOperator {

    /**
     * Addition (+).
     */
    ADDITION('+'),
    /**
     * Subtraction (-).
     */
    SUBTRACTION('-'),
    /**
     * Multiplication (*).
     */
    MULTIPLICATION('*'),
    /**
     * Division (/).
     */
    DIVISION('/');

    private final char symbol;

    ArithmeticOperator(char symbol) {
        this.symbol = symbol;
    }

    /**
     * Gets the symbol.
     *
     * @return Symbol.
     */
    char getSymbol() {
        return symbol;
    }

    /**
     * Returns the arithmetic operator of the specified symbol. The symbol must match exactly an symbol used to declare
     * an enum constant in this type.
     *
     * @param symbol The symbol.
     * @return The arithmetic operator with the specified symbol.
     * @throws IllegalArgumentException If the symbol isn't used by any supported operator.
     */
    static ArithmeticOperator valueOfSymbol(char symbol) {
        for (ArithmeticOperator operator : values()) {
            if (operator.getSymbol() == symbol) {
                return operator;
            }
        }

        throw new IllegalArgumentException(MessageFormat.format("No symbol in {0}: {1}",
                ArithmeticOperator.class.getCanonicalName(), symbol));
    }
}
