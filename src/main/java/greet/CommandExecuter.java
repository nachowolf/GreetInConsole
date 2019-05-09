package greet;

import greet.enums.GreetCommands;
import greet.greeter.GreetCounterDB;
import greet.greeter.GreetCounterMap;
import greet.greeter.GreetCounter;
import greet.methods.Helper;

public class CommandExecuter {

    GreetCounter app = new GreetCounterDB();


    public void execute(String consoleCommand){
        if(consoleCommand.equals(GreetCommands.quit.toString())){
            Helper.print("Closing Application...");
            System.exit(0);
        }

        else if(consoleCommand.equals(GreetCommands.greeted.toString())){
            if(app.greeted().size() == 0){
                Helper.print("No users has been greeted");
            }
            else{

                Helper.print(app.greeted());
            }
        }
        else if(consoleCommand.equals(GreetCommands.help.toString())){
            Helper.print("All greet app commands :");
            for (GreetCommands command : GreetCommands.values()) {
                Helper.print(command.toString());
                }
        }
        else if(consoleCommand.equals(GreetCommands.counter.toString())){
            Helper.print(app.counter());
        }
        else if(consoleCommand.equals(GreetCommands.clear.toString())){
            Helper.print(app.clear());
        }
        else{
            Helper.print("Not a valid option. Enter `help` to see all available commands.");
        }
    }

    public void execute(String consoleCommand, String user){
        if(consoleCommand.equals(GreetCommands.greet.toString())){
            Helper.print(app.greet(user));
        }
        else if(consoleCommand.equals(GreetCommands.greeted.toString())){
            Helper.print(app.greeted(user));

        }
        else if(consoleCommand.equals(GreetCommands.clear.toString())){
            Helper.print(app.clear(user));
        }
        else{
            Helper.print("Not a valid option. Enter `help` to see all available commands.");
        }
    }


    public void execute(String consoleCommand, String user, String language){
        if(consoleCommand.equals(GreetCommands.greet.toString())){
            Helper.print(app.greet(user, Helper.checkLanguage(language)));
        }
    }
    }

