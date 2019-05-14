package greet.greeter;


import greet.enums.Language;
import greet.methods.Helper;

import java.util.Map;
import java.util.TreeMap;


public class GreetCounterMap implements GreetCounter {

    Map<String, Integer> userList = new TreeMap<>();

    @Override
    public String greet(String name) {
        if (!userList.containsKey(name)) {
            userList.put(name, 0);
        }

        Integer greets = userList.get(name);
        userList.put(name, greets + 1);
        return "Hello, " + Helper.capitilize(name);

    }

    @Override
    public String greet(String name, Language language) {
        if (!userList.containsKey(name)) {
            userList.put(name, 0);
        }
        Integer greets = userList.get(name);
        userList.put(name, greets + 1);
        return Helper.greetLanguage(language) + Helper.capitilize(name);
    }

    @Override
    public String greeted() {

        String result = "No users has been greeted";
        int count = 0;
        if (userList.size() > 0) {
            result = "";
            for (Map.Entry<String, Integer> entry : userList.entrySet()) {
                String user = entry.getKey();
                Integer greeted = entry.getValue();
                result += "user: " + Helper.capitilize(user) + ", greeted: " + greeted;
                if (++count != userList.size()) {
                    result += "\n";
                }

            }
        }
        return result;
    }

    @Override
    public String greeted(String user) {
        String result = "No such user has been greeted";
        if (userList.containsKey(user)) {
            result = "user: " + Helper.capitilize(user) + ", greeted: " + userList.get(user);
        }
        return result;
    }

    @Override
    public String counter() {
        return "Users: " + userList.size();
    }

    @Override
    public String clear() {
        if(userList.size() == 0){
            return "No users have been greeted";
        }
        else{
            userList.clear();
            return "All users have been deleted";
        }

    }

    @Override
    public String clear(String user) {
        if(!userList.containsKey(user)){
            return "No such user has been greeted";
        }
        else{
            userList.remove(user);
            return "User has been deleted";
        }
    }
}