package greet.commands;

import greet.CommandExtractor;
import greet.enums.GreetCommand;

public class CommandHelp implements Command {

    @Override
    public String execute(CommandExtractor extract) {
        String allCommands = "Greeter Application Commands:\n";
        int count = 0;
        for (GreetCommand option : GreetCommand.values()) {
            allCommands += option;
            if (++count != GreetCommand.values().length) {
                allCommands += "\n";
            }
        }
        return allCommands;
    }
}
