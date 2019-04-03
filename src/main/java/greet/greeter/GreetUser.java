package greet.greeter;


import java.util.List;

public interface GreetUser {

   String greet(String name);
   String greet(String name, Language language);
   List greeted();
   List greeted(String user);
   int counter();
   void clear();
   void clear(String user);
   List help();




}
