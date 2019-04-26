package greet.console;


import greet.greeter.GreetMethods;
import greet.greeter.Language;

import java.awt.*;
import java.util.Scanner;
import greet.greeter.Language;

public class GreetConsole implements Console {
    GreetMethods app = new GreetMethods();



@Override
public void input(String string){

    String[] hold = string.toLowerCase().split(" ");
    String consoleCommand;
    String user;
    String language;

    if(hold.length == 1 ){
        consoleCommand = hold[0];
            if(consoleCommand.equals(GreetCommands.quit.toString())){
                System.out.println("Closing Application...");
                System.exit(0);
            }

            else if(consoleCommand.equals(GreetCommands.greeted.toString())){
                if(app.greeted().size() == 0){
                    System.out.println("No users has been greeted");
                }
                else{
                    for (Object i : app.greeted().keySet()) {
                        System.out.println("user: " + capitilize(i.toString()) + " greeted: " + app.greeted().get(i));
                    }
//                    app.greeted().forEach(userGreeted -> System.out.println(userGreeted));
                }
            }
            else if(consoleCommand.equals(GreetCommands.help.toString())){
                System.out.println("All greet app commands :");
                app.help().forEach(command->System.out.println(command));
            }
            else if(consoleCommand.equals(GreetCommands.counter.toString())){
                System.out.println("Users: " + app.counter());
            }
            else if(consoleCommand.equals(GreetCommands.clear.toString())){
                app.clear();
                System.out.println("User list has been cleared");
            }
            else{
                System.out.println("Not a valid option. Enter `help` to see all available commands.");
            }
    }

    else if(hold.length == 2){
        consoleCommand = hold[0];
        user = hold[1];
        if(consoleCommand.equals(GreetCommands.greet.toString())){
            System.out.println(app.greet(user));
        }
        else if(consoleCommand.equals(GreetCommands.greeted.toString())){
            if(app.greeted(user).equals("No such user")){
                System.out.println("No such user");
            }
            else{
                System.out.println(app.greeted());
            }
        }
        else if(consoleCommand.equals(GreetCommands.clear.toString())){
            app.clear(user);
            System.out.println("User has been deleted");
        }
        else{
            System.out.println("Not a valid option. Enter `help` to see all available commands.");
        }
    }

    else if(hold.length == 3){
        consoleCommand = hold[0];
        user = hold[1];
        language = hold[2];

        if(consoleCommand.equals(GreetCommands.greet.toString())){
            System.out.println(app.greet(user, checkLanguage(language)));
        }
    }

    else{
        System.out.println("Not a valid option. Enter `help` to see all available commands.");

    }

}

public Language checkLanguage(String lang){
    Language chosenLanguage = Language.English;
    for(Language language : Language.values()){
        if(capitilize(lang).equals(language.toString())){
            chosenLanguage = language;
            break;
        }
    }
    return chosenLanguage;
}

    public String capitilize(String string){
        String cap = string.substring(0,1).toUpperCase() + string.substring(1);
        return cap;
    }

    public static void main(String[] args){
        System.out.println("Greet Console Application Started...");
        Scanner scanner = new Scanner(System.in);
        GreetConsole consoleApp = new GreetConsole();
        while(true){

            ;
//        JavaConsole console = new JavaConsole();
//        console.setBackground(Color.DARK_GRAY);
//        console.setForeground(Color.green);
//        console.setFont(new Font ("Ariel", Font.Bold, 12));
//        console.setTitle("Greet In Console Application");


            consoleApp.input(scanner.nextLine().toString());

        }


    }

}