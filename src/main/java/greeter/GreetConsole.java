package greeter;


import greet.user.User;
import greet.greeter.GreetCommands;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GreetConsole implements GreetUser {

List users = new ArrayList();


    @Override
    public String greet(String input) {

        String[] data = input.split(" ");
//        System.out.println;
        greet.user.User createUser = new User(data[0].toString());
        users.add(createUser);
        user = data[0];
        System.out.println(user);
        return "Hello, " + user;
    }

    @Override
    public String greeted(String user) {
        return null;
    }

    @Override
    public int counter() {
        return 0;
    }

    @Override
    public void clear(String user) {

    }

    @Override
    public void exit() {

    }



    @Override
    public String help() {
        Arrays.asList(GreetCommands.values()).forEach(command ->
                System.out.println(command));
        String commands = GreetCommands.values().toString();
return commands;
    }




}
