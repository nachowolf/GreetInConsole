package greet;

import greet.enums.GreetCommand;
//import greet.enums.GreetCommand.*;
import greet.enums.Language;
import greet.greeter.GreetCounter;
import greet.methods.Helper;

public class CommandExecuter {

    GreetCounter app;

   public CommandExecuter(GreetCounter counter){
    this.app = counter;
   }


    public String execute(CommandExtractor extractor) {
     String result = "";

        GreetCommand command = extractor.getCommand();
        String name = extractor.getName();
        Language lang = extractor.getLanguage();
        result = "Not a valid option. Enter `help` to see all available commands.";
        if(command == null){
            result = "Not a valid option. Enter `help` to see all available commands.";
        }
        else {
            switch (command) {
                case greet:
                    if (name != null) {
                        if (lang != null) {
                            result = app.greet(name, lang);
                        } else {
                            result = app.greet(name);
                        }
                    }
                    break;

                case greeted:
                    if (name != null) {
                        result = app.greeted(name);
                    } else {
                        result = app.greeted();
                    }
                    break;

                case counter:
                    result = app.counter();
                    break;

                case clear:
                    if (name != null) {
                        result = app.clear(name);
                    } else {
                        result = app.clear();
                    }
                    break;

                case help:
                    String allCommands = "Greeter Application Commands:\n";
                    int count = 0;
                    for (GreetCommand option : GreetCommand.values()) {
                        allCommands += option;
                        if (++count != GreetCommand.values().length) {
                            allCommands += "\n";
                        }
                    }
                    result = allCommands;
                    break;

                case quit:
                    result = "quit";
                    break;

                default:
                    result = "Not a valid option. Enter `help` to see all available commands.";
            }
        }

     return result;
    }
    }

