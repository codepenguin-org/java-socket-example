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

import java.util.logging.Logger;

import static java.lang.String.join;
import static java.lang.String.valueOf;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.codepenguin.java.socket.server.example.NumberUtils.isInteger;

/**
 * Protocol for binary operations.
 *
 * @author Jorge Alfonso Garcia Espinosa
 * @version 1.0-SNAPSHOT
 * @since 1.8
 */
final class BinaryOperationProtocol {

    private static final Logger LOGGER = Logger.getLogger(BinaryOperationProtocol.class.getName());

    private static final String OPERATION_SEPARATOR = " ";
    private static final String EXIT_COMMAND = "QUIT";
    private static final String PROTOCOL_SEPARATOR = "\t";
    private static final String APP_NAME = "Binary Operation Server";
    private static final String APP_VERSION = "v.1.0-SNAPSHOT";
    private static final int INPUT_EXPECTED_LENGTH = 3;

    /**
     * Processes the input.
     *
     * @param input Input.
     * @return Response of the process.
     */
    Response process(final String input) {
        if (input == null) {
            return new Response(ResponseErrorType.INPUT_IS_NULL);
        }

        if (isBlank(input)) {
            return new Response(ResponseErrorType.INPUT_IS_BLANK);
        }

        final String[] split = input.split(OPERATION_SEPARATOR);
        if (split.length != INPUT_EXPECTED_LENGTH) {
            return new Response(ResponseErrorType.INPUT_MUST_HAVE_THREE_PARTS_ONLY);
        }

        float firstOperand;
        try {
            firstOperand = Float.parseFloat(split[0]);
        } catch (NumberFormatException e) {
            LOGGER.warning(e.getMessage());
            return new Response(ResponseErrorType.INPUT_FIRST_OPERAND_IS_NOT_A_NUMBER);
        }

        if (split[1].length() != 1) {
            return new Response(ResponseErrorType.INPUT_OPERATOR_IS_NOT_VALID);
        }

        char symbol = split[1].charAt(0);

        ArithmeticOperator operator;
        try {
            operator = ArithmeticOperator.valueOfSymbol(symbol);
        } catch (IllegalArgumentException e) {
            LOGGER.warning(e.getMessage());
            return new Response(ResponseErrorType.INPUT_OPERATOR_IS_NOT_VALID);
        }

        float secondOperator;
        try {
            secondOperator = Float.parseFloat(split[2]);
        } catch (NumberFormatException e) {
            LOGGER.warning(e.getMessage());
            return new Response(ResponseErrorType.INPUT_SECOND_OPERAND_IS_NOT_A_NUMBER);
        }

        BinaryOperation operation = new BinaryOperation(firstOperand, operator, secondOperator);
        final String result = formatOperation(operation) + PROTOCOL_SEPARATOR + formatResult(operation);

        return new Response(result);
    }

    /**
     * Gets the exit command.
     *
     * @return The exit command.
     */
    String getExitCommand() {
        return EXIT_COMMAND;
    }

    /**
     * Gets the welcome message to send for the new connected clients.
     *
     * @return The welcome message.
     */
    String getWelcomeMessage() {
        return join(PROTOCOL_SEPARATOR, APP_NAME, APP_VERSION);
    }

    private String formatOperation(final BinaryOperation operation) {
        return join(OPERATION_SEPARATOR, format(operation.getFirstOperand()),
                valueOf(operation.getSymbol().getSymbol()), format(operation.getSecondOperand()));
    }

    private String formatResult(final BinaryOperation operation) {
        return format(operation.apply());
    }

    private String format(double f) {
        return isInteger(f) ? valueOf((int) f) : valueOf(f);
    }

    /**
     * Response of the protocol.
     *
     * @author Jorge Alfonso Garcia Espinosa
     * @version 1.0-SNAPSHOT
     * @since 1.8
     */
    static class Response {

        private final ResponseType type;
        private final String okMessage;
        private final ResponseErrorType errorType;

        /**
         * Constructor. Sets the type as {@link ResponseType#OK}.
         *
         * @param okMessage Message for OK result.
         */
        Response(String okMessage) {
            this.okMessage = okMessage;

            type = ResponseType.OK;
            errorType = null;
        }

        /**
         * Constructor. Sets the type as {@link ResponseType#ERR}.
         *
         * @param errorType Type of error.
         */
        Response(ResponseErrorType errorType) {
            this.errorType = errorType;

            type = ResponseType.ERR;
            okMessage = null;
        }

        /**
         * Gets the type.
         *
         * @return The type.
         */
        ResponseType getType() {
            return type;
        }

        /**
         * Gets the OK message.
         *
         * @return The OK message if the type is {@link ResponseType#OK}; otherwise, {@literal null}.
         */
        String getOkMessage() {
            return okMessage;
        }

        /**
         * Gets the error type.
         *
         * @return The error type if the response type is {@link ResponseType#ERR}; otherwise, {@literal null}.
         */
        ResponseErrorType getErrorType() {
            return errorType;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder(valueOf(getType())).append(PROTOCOL_SEPARATOR);
            if (getType().equals(ResponseType.OK)) {
                builder.append(getOkMessage());
            } else {
                builder.append(getErrorType());
            }
            return builder.toString();
        }
    }

    /**
     * Response type.
     *
     * @author Jorge Alfonso Garcia Espinosa
     * @version 1.0-SNAPSHOT
     * @since 1.8
     */
    enum ResponseType {
        /**
         * OK type.
         */
        OK,
        /**
         * Error type.
         */
        ERR
    }

    /**
     * Response error type.
     *
     * @author Jorge Alfonso Garcia Espinosa
     * @version 1.0-SNAPSHOT
     * @since 1.8
     */
    enum ResponseErrorType {
        /**
         * Input is {@literal null}.
         */
        INPUT_IS_NULL,
        /**
         * Input is blank.
         */
        INPUT_IS_BLANK,
        /**
         * Input must have three parts only (First operand, operator, second operator).
         */
        INPUT_MUST_HAVE_THREE_PARTS_ONLY,
        /**
         * Input's first operand isn't a number.
         */
        INPUT_FIRST_OPERAND_IS_NOT_A_NUMBER,
        /**
         * Input's operator isn't valid.
         */
        INPUT_OPERATOR_IS_NOT_VALID,
        /**
         * Input's second operand isn't a number.
         */
        INPUT_SECOND_OPERAND_IS_NOT_A_NUMBER
    }
}
