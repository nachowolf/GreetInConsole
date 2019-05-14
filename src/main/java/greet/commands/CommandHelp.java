package greet.commands;

import greet.CommandExtractor;
import greet.enums.GreetCommand;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class CommandHelp implements Command {

    Map<GreetCommand, String> helplist = new TreeMap<GreetCommand, String>();

    @Override
    public String execute(CommandExtractor extract) {
        helplist.put(GreetCommand.quit, "Exits application.");
        helplist.put(GreetCommand.help, "Prints list of available command");
        helplist.put(GreetCommand.counter, "returns how many users have been greeted");
        helplist.put(GreetCommand.clear, "'clear' removes all users that has been greeted and 'clear <user>' removes a specific user.");
        helplist.put(GreetCommand.greeted, "'greeted' returns all users that has been greeted and how many times they have been greeted.'greeted <user>' returns a specific user.");
        helplist.put(GreetCommand.greet, "Can be used as 'greet <user>' or 'greet <user> <language>'. greets in English by default.");

        String allCommands = "Greeter Application Commands:\n";
        int count = 0;

        for (Map.Entry option : helplist.entrySet()) {
            allCommands += option.getKey() + " : " + option.getValue();
            if (++count != helplist.size()) {
                allCommands += "\n";
            }
        }
        return allCommands;
    }
}
