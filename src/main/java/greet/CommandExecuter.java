package greet;

import greet.commands.*;
import greet.enums.GreetCommand;
import greet.greeter.GreetCounter;

import java.util.HashMap;
import java.util.Map;

public class CommandExecuter {

    Map<GreetCommand, Command> commandsAvailable = new HashMap<>();
    private GreetCounter counter;

    public CommandExecuter(GreetCounter counter) {
        this.counter = counter;
        commandsAvailable.put(GreetCommand.greet, new CommandGreet(this.counter));
        commandsAvailable.put(GreetCommand.greeted, new CommandGreeted(this.counter));
        commandsAvailable.put(GreetCommand.counter, new CommandCounter(this.counter));
        commandsAvailable.put(GreetCommand.help, new CommandHelp());
        commandsAvailable.put(GreetCommand.clear, new CommandClear(this.counter));
        commandsAvailable.put(GreetCommand.quit, new CommandQuit());

    }

    public String execute(CommandExtractor extract) {

        GreetCommand command = extract.getCommand();

        Command commandToExecute = this.commandsAvailable.get(command);

        if (commandToExecute != null) {
            return commandToExecute.execute(extract);
        } else {
            return "invalid command.";
        }

    }
}

