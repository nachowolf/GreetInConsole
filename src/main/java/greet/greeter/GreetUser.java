package greet.greeter;



import greet.enums.Language;

import java.util.List;

public interface GreetUser {

   String greet(String name);
   String greet(String name, Language language);
   List greeted();
   String greeted(String user);
   int counter();
   void clear();
   void clear(String user);
   List help();




}
