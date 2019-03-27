package greet.console;

import greet.greeter.Language;

public interface Console {
    String greet(String name);
    String greet(String name, Language language);
    String greeted(String user);
    int counter();
    void clear(String user);
    void exit();
    String help();
}
