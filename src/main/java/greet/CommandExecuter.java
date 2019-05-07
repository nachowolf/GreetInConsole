package greet;

import greet.enums.GreetCommands;
import greet.greeter.GreetCounterDB;
import greet.greeter.GreetCounterMap;
import greet.greeter.GreetCounter;
import greet.methods.Helper;

public class CommandExecuter {

    GreetCounter app = new GreetCounterDB();

    public void error(){
        System.out.println("Not a valid option. Enter `help` to see all available commands.");
    }

    public void execute(String consoleCommand){
        if(consoleCommand.equals(GreetCommands.quit.toString())){
            System.out.println("Closing Application...");
            System.exit(0);
        }

        else if(consoleCommand.equals(GreetCommands.greeted.toString())){
            if(app.greeted().size() == 0){
                System.out.println("No users has been greeted");
            }
            else{

                app.greeted().forEach(userGreeted -> System.out.println(userGreeted));
            }
        }
        else if(consoleCommand.equals(GreetCommands.help.toString())){
            System.out.println("All greet app commands :");
            for (GreetCommands command : GreetCommands.values()) {
                System.out.println(command);
                }
        }
        else if(consoleCommand.equals(GreetCommands.counter.toString())){
            System.out.println(app.counter());
        }
        else if(consoleCommand.equals(GreetCommands.clear.toString())){
            System.out.println(app.clear());
        }
        else{
            error();
        }
    }

    public void execute(String consoleCommand, String user){
        if(consoleCommand.equals(GreetCommands.greet.toString())){
            System.out.println(app.greet(user));
        }
        else if(consoleCommand.equals(GreetCommands.greeted.toString())){
                System.out.println(app.greeted(user));

        }
        else if(consoleCommand.equals(GreetCommands.clear.toString())){
            System.out.println(app.clear(user));
        }
        else{
            error();
        }
    }


    public void execute(String consoleCommand, String user, String language){
        if(consoleCommand.equals(GreetCommands.greet.toString())){
            System.out.println(app.greet(user, Helper.checkLanguage(language)));
        }
    }
    }

