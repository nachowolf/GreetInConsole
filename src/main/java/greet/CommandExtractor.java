package greet;

import greet.enums.GreetCommand;
import greet.enums.Language;
import org.apache.commons.lang3.EnumUtils;


public class CommandExtractor {

    private GreetCommand command;
    private String name;
    private Language language;

    public CommandExtractor(String string) {
        String[] hold = string.toLowerCase().split(" ");
        if (EnumUtils.isValidEnum(GreetCommand.class, hold[0])) {
            this.command = GreetCommand.valueOf(hold[0]);
        }

        if (hold.length >= 2) {
            this.name = hold[1];
            this.language = Language.english;
        }

        if (hold.length == 3 && EnumUtils.isValidEnum(Language.class, hold[2])) {
            this.language = Language.valueOf(hold[2]);
        }

    }

    public GreetCommand getCommand() {
        return this.command;
    }

    public String getName() {
        return this.name;
    }

    public Language getLanguage() {
        return this.language;
    }


}
