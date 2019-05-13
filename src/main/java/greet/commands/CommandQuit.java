package greet.commands;

import greet.CommandExtractor;

public class CommandQuit implements Command {
    @Override
    public String execute(CommandExtractor extract) {
        return "quit";
    }
}
