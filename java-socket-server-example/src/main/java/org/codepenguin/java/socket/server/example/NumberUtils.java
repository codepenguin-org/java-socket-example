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

import java.math.BigDecimal;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;

/**
 * Utility for numbers.
 *
 * @author Jorge Alfonso Garcia Espinosa
 * @version 1.0-SNAPSHOT
 * @since 1.8
 */
final class NumberUtils {

    /**
     * Integer 0.
     */
    static final int INT_0 = 0;

    /**
     * Integer 1.
     */
    static final int INT_1 = 1;

    /**
     * Integer 3.
     */
    static final int INT_3 = 3;

    /**
     * Integer 4.
     */
    static final int INT_4 = 4;

    /**
     * Integer 5.
     */
    static final int INT_5 = 5;

    /**
     * Integer 6.
     */
    static final int INT_6 = 6;

    /**
     * Integer 7.
     */
    static final int INT_7 = 7;

    /**
     * Integer 12.
     */
    static final int INT_12 = 12;

    /**
     * Integer 30.
     */
    static final int INT_30 = 30;

    private NumberUtils() {
    }

    /**
     * Indicates if a number is an integer.
     *
     * @param number The number.
     * @return {@literal true} if the number is an integer; otherwise, {@literal false}.
     */
    static boolean isInteger(final Number number) {
        return BigDecimal.valueOf(number.floatValue()).remainder(ONE).doubleValue() == ZERO.doubleValue();
    }
}
