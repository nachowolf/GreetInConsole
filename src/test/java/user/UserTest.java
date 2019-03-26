package greet.user;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @Test
    public void shouldCreateUser(){
        User user = new User("Nathri");
        assertEquals("Nathri", user.getUsername());
    }

    @Test
    public void shouldReturnUserGreetCount(){
        User user = new User("Nathri");
        assertEquals(0, user.getGreetCount());
    }

    @Test
    public void shouldIncrementAndReturnUserGreetCount(){
        User user = new User("Nathri");
        user.greet();
        assertEquals(1, user.getGreetCount());
    }
}
