package greeter;


public interface GreetUser {

   String greet(String name, Language language);
   String greeted(String user);
   int counter();
   void clear(String user);
   void exit();
   String help();




}
