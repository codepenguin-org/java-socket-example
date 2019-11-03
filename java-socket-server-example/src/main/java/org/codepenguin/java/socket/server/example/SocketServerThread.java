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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Thread for the socket's server client session.
 *
 * @author Jorge Alfonso Garcia Espinosa
 * @version 1.0-SNAPSHOT
 * @since 1.8
 */
class SocketServerThread extends Thread {

    private static final Logger LOGGER = Logger.getLogger(SocketServerThread.class.getName());

    private static final String NAME_SEPARATOR = ":";
    private static final String LOG_DELIMITER = "\t";
    private static final String INPUT = "INPUT";
    private static final String START_CLIENT_SESSION = "START_CLIENT_SESSION";
    private static final String OUTPUT = "OUTPUT";
    private static final String END_CLIENT_SESSION = "END_CLIENT_SESSION";

    private final Socket socket;

    /**
     * Constructor.
     *
     * @param socket Socket of the client's session.
     */
    SocketServerThread(Socket socket) {
        super(SocketServerThread.class.getName() + NAME_SEPARATOR + socket.toString());
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            LOGGER.info(String.join(LOG_DELIMITER, START_CLIENT_SESSION, getName()));

            final BinaryOperationProtocol protocol = new BinaryOperationProtocol();
            String input;
            while ((input = reader.readLine()) != null) {
                LOGGER.info(String.join(LOG_DELIMITER, INPUT, getName(), input));
                if (input.equals(protocol.getExitCommand()))
                    break;

                BinaryOperationProtocol.Response response = protocol.process(input);
                LOGGER.info(String.join(LOG_DELIMITER, OUTPUT, getName(), input, response.toString()));
                writer.println(response);
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, getName(), e);
        } finally {
            LOGGER.info(String.join(LOG_DELIMITER, END_CLIENT_SESSION, getName()));
        }
    }
}
