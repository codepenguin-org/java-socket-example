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

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.codepenguin.java.socket.server.example.BinaryOperationProtocol.ResponseErrorType.*;
import static org.codepenguin.java.socket.server.example.BinaryOperationProtocol.ResponseType.ERR;
import static org.codepenguin.java.socket.server.example.BinaryOperationProtocol.ResponseType.OK;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for {@link BinaryOperationProtocol}.
 *
 * @author Jorge Alfonso Garcia Espinosa
 * @version 1.0-SNAPSHOT
 * @since 1.8
 */
class BinaryOperationProtocolTest {

    private static final String EXPECTED_RESPONSE_FORMAT = "OK\t%s\t%s";

    private static BinaryOperationProtocol protocol;

    @BeforeAll
    static void setUp() {
        protocol = new BinaryOperationProtocol();
    }

    @Test
    void processNull() {
        BinaryOperationProtocol.Response response = protocol.process(null);
        assertNotNull(response);
        assertEquals(ERR, response.getType());
        assertEquals(INPUT_IS_NULL, response.getErrorType());
        assertNull(response.getOkMessage());
        assertEquals("ERR\tINPUT_IS_NULL", response.toString());
    }

    @Test
    void processBlank() {
        BinaryOperationProtocol.Response response = protocol.process("     ");
        assertNotNull(response);
        assertEquals(ERR, response.getType());
        assertEquals(INPUT_IS_BLANK, response.getErrorType());
        assertNull(response.getOkMessage());
        assertEquals("ERR\tINPUT_IS_BLANK", response.toString());
    }

    @Test
    void processParts() {
        BinaryOperationProtocol.Response response = protocol.process(" 2 + 3 + 4");
        assertNotNull(response);
        assertEquals(ERR, response.getType());
        assertEquals(INPUT_MUST_HAVE_THREE_PARTS_ONLY, response.getErrorType());
        assertNull(response.getOkMessage());
        assertEquals("ERR\tINPUT_MUST_HAVE_THREE_PARTS_ONLY", response.toString());
    }

    @Test
    void processFirstOperand() {
        BinaryOperationProtocol.Response response = protocol.process("a + 2");
        assertNotNull(response);
        assertEquals(ERR, response.getType());
        assertEquals(INPUT_FIRST_OPERAND_IS_NOT_A_NUMBER, response.getErrorType());
        assertNull(response.getOkMessage());
        assertEquals("ERR\tINPUT_FIRST_OPERAND_IS_NOT_A_NUMBER", response.toString());
    }

    @Test
    void processOperator() {
        BinaryOperationProtocol.Response response = protocol.process("1 ? 2");
        assertNotNull(response);
        assertEquals(ERR, response.getType());
        assertEquals(INPUT_OPERATOR_IS_NOT_VALID, response.getErrorType());
        assertNull(response.getOkMessage());
        assertEquals("ERR\tINPUT_OPERATOR_IS_NOT_VALID", response.toString());
    }

    @Test
    void processSecondOperator() {
        BinaryOperationProtocol.Response response = protocol.process("1 + b");
        assertNotNull(response);
        assertEquals(ERR, response.getType());
        assertEquals(INPUT_SECOND_OPERAND_IS_NOT_A_NUMBER, response.getErrorType());
        assertNull(response.getOkMessage());
        assertEquals("ERR\tINPUT_SECOND_OPERAND_IS_NOT_A_NUMBER", response.toString());
    }

    @Test
    void processAddition() {
        String input = "1 + 2";
        BinaryOperationProtocol.Response response = protocol.process(input);
        assertNotNull(response);
        assertEquals(OK, response.getType());

        String result = "3";
        assertEquals(input + "\t" + result, response.getOkMessage());
        assertNull(response.getErrorType());
        assertEquals(String.format(EXPECTED_RESPONSE_FORMAT, input, result), response.toString());
    }

    @Test
    void processSubtraction() {
        String input = "1 - 2";
        BinaryOperationProtocol.Response response = protocol.process(input);
        assertNotNull(response);
        assertEquals(OK, response.getType());

        String result = "-1";
        assertEquals(input + "\t" + result, response.getOkMessage());
        assertNull(response.getErrorType());
        assertEquals(String.format(EXPECTED_RESPONSE_FORMAT, input, result), response.toString());
    }

    @Test
    void processMultiplication() {
        String input = "1 * 2";
        BinaryOperationProtocol.Response response = protocol.process(input);
        assertNotNull(response);
        assertEquals(OK, response.getType());

        String result = "2";
        assertEquals(input + "\t" + result, response.getOkMessage());
        assertNull(response.getErrorType());
        assertEquals(String.format(EXPECTED_RESPONSE_FORMAT, input, result), response.toString());
    }

    @Test
    void processDivision() {
        String input = "1 / 2";
        BinaryOperationProtocol.Response response = protocol.process(input);
        assertNotNull(response);
        assertEquals(OK, response.getType());

        String result = "0.5";
        assertEquals(input + "\t" + result, response.getOkMessage());
        assertNull(response.getErrorType());
        assertEquals(String.format(EXPECTED_RESPONSE_FORMAT, input, result), response.toString());
    }

    @Test
    void exitCommand() {
        assertEquals("QUIT", protocol.getExitCommand());
    }
}