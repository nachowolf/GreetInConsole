package greet.greeter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.*;
import java.util.logging.Logger;

import greet.console.GreetCommands;
import greet.database.dbRequests;
import greet.user.Account;
import greet.user.User;

public class GreetMethods implements GreetUser {


        dbRequests request = new dbRequests();




    @Override
    public String greet(String name) {
        String holdName = name.toLowerCase();
        String output = "";
        if(checkName(holdName) == true){
            output = "Hello, " + capitilize(holdName);
        }

        return output;
    }

    @Override
    public String greet(String name, Language language) {
        String holdName = name.toLowerCase();
        String output = "";
        if(checkName(holdName) == true){
            output = greetLanguage(language) + capitilize(holdName);
        }
        return output;
    }

    @Override
    public String greeted(String username) {
        String output = "No such user";
        if(request.allUsers().containsKey((username.toLowerCase())) ){
            return "Name: " + username + " greets: " + request.allUsers().get(username.toLowerCase());
        }
        return output;
    }

    @Override
    public HashMap greeted() {
        return request.allUsers();
    }


    @Override
    public int counter() {
        return request.allUsersCount();
    }

    @Override
    public void clear() {
        request.deleteAllUsers();
    }

    @Override
    public void clear(String name) {
        String holdName = name.toLowerCase();
       request.deleteUser(holdName);
    }


    @Override
    public List help() {
        List<String> allCommands = new ArrayList();
       for(GreetCommands command : GreetCommands.values()) {
           allCommands.add(capitilize(command.toString()));
       }
//        allCommands.forEach(command -> System.out.println(command));
        return allCommands;
    }

    public String capitilize(String string){
        String cap = string.substring(0,1).toUpperCase() + string.substring(1);
        return cap;
    }

    public String greetLanguage(Language language){

        String response = "Hello, ";

//        if(language == Language.English){
//            response = "Hello, ";
//        }
        if(language == Language.Japanese){
            response = "Konichiwa, ";
        }
        else if(language == Language.Thai){
            response = "Sawa dee krahp, ";
        }

        return response;
    }

    public boolean checkName(String holdName) {
            request.addUser(holdName);
return true;
    }


}
