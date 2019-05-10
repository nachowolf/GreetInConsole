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
     String result = "Not a valid option. Enter `help` to see all available commands.";

        GreetCommand command = extractor.getCommand();
        String name = extractor.getName();
        Language lang = extractor.getLanguage();

        if(command == GreetCommand.greet && name != null){
            if (lang !=  null){
                result = app.greet(name, lang);
            }
            else{
                result = app.greet(name);
            }
        }

        else if(command == GreetCommand.greeted){
            if(name != null){
                result = app.greeted(name);
            }
            else{
                result = app.greeted();
            }
        }


     return result;
    }
    }

