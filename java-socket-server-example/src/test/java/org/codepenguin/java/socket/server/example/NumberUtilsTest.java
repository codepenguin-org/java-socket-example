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

import static org.codepenguin.java.socket.server.example.NumberUtils.INT_0;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for {@link NumberUtils}.
 *
 * @author Jorge Alfonso Garcia Espinosa
 * @version 1.0-SNAPSHOT
 * @since 1.8
 */
class NumberUtilsTest {

    private static final int INTEGER = 254;
    private static final double INTEGER_AS_DOUBLE = 59D;
    private static final float INTEGER_AS_FLOAT = 54F;
    private static final double DOUBLE = 874.14;

    @Test
    void isInteger() {
        assertTrue(NumberUtils.isInteger(INT_0));
        assertTrue(NumberUtils.isInteger(INTEGER));
        assertTrue(NumberUtils.isInteger(INTEGER_AS_DOUBLE));
        assertTrue(NumberUtils.isInteger(INTEGER_AS_FLOAT));

        assertFalse(NumberUtils.isInteger(DOUBLE));
    }
}