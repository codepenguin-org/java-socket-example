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

import static org.codepenguin.java.socket.server.example.ArithmeticOperator.*;
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
    void apply() {
        assertEquals(7, new BinaryOperation(3, ADDITION, 4).apply());
        assertEquals(-1, new BinaryOperation(3, SUBTRACTION, 4).apply());
        assertEquals(12, new BinaryOperation(3, MULTIPLICATION, 4).apply());
        assertEquals(6, new BinaryOperation(30, DIVISION, 5).apply());
    }

    @Test
    void print() {
        assertEquals("3 + 4", new BinaryOperation(3, ADDITION, 4).print(false));
        assertEquals("2 - 8", new BinaryOperation(2, SUBTRACTION, 8).print(false));
        assertEquals("34.55 + 23.88", new BinaryOperation(34.55F, ADDITION, 23.88F)
                .print(false));

        assertEquals("2 + 2 = 4", new BinaryOperation(2, ADDITION, 2).print(true));
        assertEquals("5 * 3 = 15", new BinaryOperation(5, MULTIPLICATION, 3).print(true));
        assertEquals("3.2 * 8.9 = 28.48", new BinaryOperation(3.2F, MULTIPLICATION, 8.9F)
                .print(true));
    }
}