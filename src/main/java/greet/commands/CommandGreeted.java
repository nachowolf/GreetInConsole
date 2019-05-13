package greet.commands;

import greet.CommandExtractor;
import greet.greeter.GreetCounter;

public class CommandGreeted implements Command {

    private GreetCounter counter;

    public CommandGreeted(GreetCounter counter) {
        this.counter = counter;
    }

    @Override
    public String execute(CommandExtractor extract) {

        String name = extract.getName();

        if (name != null) {
            return counter.greeted(name);
        } else {
            return counter.greeted();
        }
    }
}
