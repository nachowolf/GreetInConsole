package greet;

import greet.enums.Language;

public class CommandExtractor {
    CommandExecuter process = new CommandExecuter();


public void extract(String string) {

    String consoleCommand;
    String user;
    String language;


    String[] hold = string.toLowerCase().split(" ");

    if(hold.length == 1){
        consoleCommand = hold[0];
        process.execute(consoleCommand);
    }
    else if(hold.length == 2){
        consoleCommand = hold[0];
        user = hold[1];
        process.execute(consoleCommand, user);
    }
    else if(hold.length == 3){
        consoleCommand = hold[0];
        user = hold[1];
        language = hold[2];
        process.execute(consoleCommand, user, language);
    }


    }
}
