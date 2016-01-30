package com.byronlai.nickel;

import com.byronlai.nickel.ui.CommandLine;
import com.byronlai.nickel.ui.GUI;

/*
 * The entry point of the program. Running the program with --cli flag launches
 * the command line interface. Running it without any arguments starts the
 * graphical user interface.
 */
public class App {
    public static void main(String[] args) {
        boolean isConsoleMode = args.length == 1 && args[0].equals("--cli");

        if (isConsoleMode) {
            CommandLine commandLine = new CommandLine();
            commandLine.run();
        } else {
            GUI gui = new GUI();
            gui.run();
        }
    }
}
