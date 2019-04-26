package greet.greeter;


import java.util.HashMap;
import java.util.List;

public interface GreetUser {

   String greet(String name);
   String greet(String name, Language language);
   HashMap greeted();
   String greeted(String user);
   int counter();
   void clear();
   void clear(String user);
   List help();




}
