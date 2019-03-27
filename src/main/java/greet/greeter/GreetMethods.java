package greet.greeter;

import java.util.ArrayList;
import greet.user.Account;
import greet.user.User;
import java.util.List;

public class GreetMethods implements GreetUser {

   private List<Account> users = new ArrayList<>();

    @Override
    public String greet(String name) {
        String holdName = name.toLowerCase();
        return "Hello, " + capitilize(checkName(holdName));
    }

    @Override
    public String greet(String name, Language language) {
        String holdName = name.toLowerCase();
        for(Account user : users){
            if(user.getUsername() == holdName){
                user.greet();
                break;
            }
            User createUser = new User(holdName);
            users.add(createUser);

        }
        return greetLanguage(language) + capitilize(checkName(holdName));
    }

    @Override
    public String greeted() {
        return null;
    }

    @Override
    public String greeted(String user) {
        return null;
    }

    @Override
    public int counter() {
        return users.size();
    }

    @Override
    public void clear(String user) {

    }

    @Override
    public void exit() {

    }

    @Override
    public String help() {
        return null;
    }

    public String capitilize(String string){
        String cap = string.substring(0,1).toUpperCase() + string.substring(1);
        return cap;
    }

    public String greetLanguage(Language language){

        String response;

//        if(language == Language.English){
//            response = "Hello, ";
//        }
        if(language == Language.Japanese){
            response = "Konichiwa, ";
        }
        else if(language == Language.Thai){
            response = "Sawa dee krahp, ";
        }
        else {
            response = "Hello, ";
        }
        return response;
    }

    public String checkName(String holdName) {
        for(Account user : users) {
            if (user.getUsername() == holdName) {
                user.greet();
                break;
            }
            else if(user.getUsername() != holdName){
                continue;
            }
            User createUser = new User(holdName);
            users.add(createUser);
            System.out.println(users.size());
        }
            return holdName;
    }
}
