package greet.greeter;


import greet.enums.Language;

public interface GreetCounter {

    String greet(String name);

    String greet(String name, Language language);

    String greeted();

    String greeted(String user);

    String counter();

    String clear();

    String clear(String user);


}
