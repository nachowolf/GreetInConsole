package greet.user;

public class User implements Account {

    private String username;
    private int greetCount = 0;

   public User(String name){
        this.username = name;
    }

    public String getUsername() {
        return username;
    }

    public int getGreetCount() {
        return greetCount;
    }

    public void greet(){
        this.greetCount++;
    }
}
