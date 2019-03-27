package greet.greeter;


public interface GreetUser {

   String greet(String name);
   String greet(String name, Language language);
   String greeted();
   String greeted(String user);
   int counter();
   void clear(String user);
   void exit();
   String help();




}
