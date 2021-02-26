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

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main class.
 *
 * @author Jorge Alfonso Garcia Espinosa
 * @version 1.0-SNAPSHOT
 * @since 1.8
 */
public final class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    private static final AtomicBoolean KEEP_GOING = new AtomicBoolean(true);

    private static final String PORT_OPTION = "p";
    private static final String PORT_LONG_OPTION = "port";
    private static final String SERVER_PORT_DESCRIPTION = "Server port";
    private static final int EXIT_STATUS = 1;

    private Main() {
    }

    /**
     * Main method. Starts the socket's server in the specified port.
     *
     * @param args The arguments: [port]
     */
    public static void main(String[] args) {
        final CommandLine commandLine;
        try {
            commandLine = new DefaultParser().parse(buildOptions(), args);
        } catch (ParseException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            close();
            return;
        }

        final String portValue = commandLine.getOptionValue(PORT_OPTION);

        final int port;
        try {
            port = Integer.parseInt(portValue);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, portValue, e);
            close();
            return;
        }

        try (ServerSocket server = new ServerSocket(port)) {
            LOGGER.log(Level.INFO, "START_SERVER\t{0}", port);
            while (KEEP_GOING.get()) {
                new SocketServerThread(server.accept()).start();
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, portValue, e);
            close();
        } finally {
            LOGGER.log(Level.INFO, "STOP_SERVER\t{0}", port);
        }
    }

    private static Options buildOptions() {
        return new Options().addRequiredOption(PORT_OPTION, PORT_LONG_OPTION, true, SERVER_PORT_DESCRIPTION);
    }

    private static void close() {
        System.exit(EXIT_STATUS);
    }
}
