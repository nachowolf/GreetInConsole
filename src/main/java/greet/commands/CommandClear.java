package greet.commands;

import greet.CommandExtractor;
import greet.greeter.GreetCounter;

public class CommandClear implements Command {

    private GreetCounter counter;

    public CommandClear(GreetCounter counter) {
        this.counter = counter;
    }

    @Override
    public String execute(CommandExtractor extract) {
        String name = extract.getName();
        if (name != null) {
            return counter.clear(name);
        } else {
            return counter.clear();
        }
    }
}
