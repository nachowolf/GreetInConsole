package greet.commands;

import greet.CommandExtractor;
import greet.greeter.GreetCounter;

public class CommandCounter implements Command {

    private GreetCounter counter;

    public CommandCounter(GreetCounter counter) {
        this.counter = counter;
    }

    @Override
    public String execute(CommandExtractor extract) {
        return counter.counter();
    }
}
