package greet.greeter;



import greet.enums.Language;

import java.util.List;

public interface GreetCounter {

   String greet(String name);
   String greet(String name, Language language);
   List greeted();
   String greeted(String user);
   String counter();
   String clear();
   String clear(String user);





}