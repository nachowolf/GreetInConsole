package greet.commands;

import greet.CommandExtractor;
import greet.enums.Language;
import greet.greeter.GreetCounter;

public class CommandGreet implements Command {


    private GreetCounter counter;

    public CommandGreet(GreetCounter counter) {
        this.counter = counter;
    }

    @Override
    public String execute(CommandExtractor extract) {

        String name = extract.getName();
        Language lang = extract.getLanguage();

        if (name != null) {
            if (lang != null) {
                return counter.greet(name, lang);
            } else {
                return counter.greet(name);
            }
        }
        return "Please specify a name.";
    }
}
