package greet;

import greet.enums.*;
import greet.greeter.GreetCounterMap;
import greet.methods.Helper;

public class CommandExtractor {

    private GreetCommand command;
    private String name;
    private Language language;

    public CommandExtractor(String string){
        String[] hold = string.toLowerCase().split(" ");
        this.command = GreetCommand.valueOf(hold[0]);

        if(hold.length >= 2){
            this.name = hold[1];
            if(hold.length == 3){
                this.language = Language.valueOf(hold[3]);
            }

        }
    }

    public GreetCommand getCommand(){
        return this.command;
    }

    public String getName(){
        return this.name;
    }

    public Language getLanguage(){
        return this.language;
    }



}
