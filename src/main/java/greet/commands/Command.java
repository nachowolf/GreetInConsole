package greet.commands;

import greet.CommandExtractor;

public interface Command {
    String execute(CommandExtractor extract);
}
