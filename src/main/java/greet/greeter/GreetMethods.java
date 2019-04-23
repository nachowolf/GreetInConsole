package greet.greeter;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.*;

import greet.console.GreetCommands;
import greet.user.Account;
import greet.user.User;

public class GreetMethods implements GreetUser {

   private List<Account> userList = new ArrayList<>();


    @Override
    public String greet(String name) {
        String holdName = name.toLowerCase();

        return "Hello, " + capitilize(checkName(holdName));
    }

    @Override
    public String greet(String name, Language language) {
        String holdName = name.toLowerCase();
        return greetLanguage(language) + capitilize(checkName(holdName));
    }

    @Override
    public List greeted() {
//         JsonArray userGreets = new JsonArray();
        List<String> userCounts = new ArrayList();
             for(Account user : userList){
                 String name = capitilize(user.getUsername());
                 String greeted = Integer.toString(user.getGreetCount());
//                 JsonObject currentUser = new JsonObject();
//                 currentUser.addProperty("user", name);
//                 currentUser.addProperty("greeted", greeted);
//                 userGreets.add(currentUser);
                 String currentUser = "user: " + name + ", greeted: " + greeted;
                 userCounts.add(currentUser);




        }
//        OutputStream os = new ByteArrayOutputStream();
//        PrintStream ps = new PrintStream(os);
//        userCounts.forEach(user -> System.out.println(user));
        return userCounts;
    }

    @Override
    public List greeted(String username) {
        List<String> userCounts = new ArrayList();
        String holdName = username.toLowerCase();
        for(Account user : userList){
            if(user.getUsername().contains(holdName)){
                String name = capitilize(user.getUsername());
                String greeted = Integer.toString(user.getGreetCount());
//                 JsonObject currentUser = new JsonObject();
//                 currentUser.addProperty("user", name);
//                 currentUser.addProperty("greeted", greeted);
//                 userGreets.add(currentUser);
                String currentUser = "user: " + name + ", greeted: " + greeted;
                userCounts.add(currentUser);
            }



        }
//        OutputStream os = new ByteArrayOutputStream();
//        PrintStream ps = new PrintStream(os);
//        userCounts.forEach(user -> System.out.println(user));

        return userCounts;
    }

    @Override
    public int counter() {
        return userList.size();
    }

    @Override
    public void clear() {
        userList.clear();
    }

    @Override
    public void clear(String name) {
        String holdName = name.toLowerCase();
        for (Account user : userList) {
            if(user.getUsername().contains(holdName)){
                userList.remove(user);
                break;
            }
        }
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

    public String checkName(String holdName) {
String newUser;
        if(userList.size() == 0){
            directAdd(holdName);
        }
        else {
            int counted = 0;
            for (Account user : userList) {
                if (user.getUsername().contains(holdName)) {
                    user.greet();
                    break;
                }
                else{
                    counted ++;
                }
            }
            if (counted == userList.size()){
                directAdd(holdName);
            }
        }
            return newUser = holdName;

    }

    public String directAdd(String name){
        String holdName = name.toLowerCase();
        User createUser = new User(holdName);
        createUser.greet();
        userList.add(createUser);
return createUser.getUsername();
    }
}
