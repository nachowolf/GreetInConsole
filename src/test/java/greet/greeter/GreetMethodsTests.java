package greet.greeter;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreetMethodsTests {

    @Test
    public void shouldGreetUserInEnglish(){
        GreetMethods greetUser = new GreetMethods();
        assertEquals("Hello, Nathri",greetUser.greet("Nathri", Language.English));
        assertEquals(1, greetUser.counter());
    }

    @Test
    public void shouldGreetUserInJapanese(){
        GreetMethods greetUser = new GreetMethods();
        assertEquals("Konichiwa, Nathri",greetUser.greet("Nathri", Language.Japanese));
        assertEquals(1, greetUser.counter());
    }

    @Test
    public void shouldGreetUserInThai(){
        GreetMethods greetUser = new GreetMethods();
        assertEquals("Sawa dee krahp, Nathri",greetUser.greet("Nathri", Language.Thai));
        assertEquals(1, greetUser.counter());
    }

    @Test
    public void shouldGreetUserInEnglishByDefault(){
        GreetMethods greetUser = new GreetMethods();
        assertEquals("Hello, Nathri",greetUser.greet("Nathri"));
        assertEquals(1, greetUser.counter());
    }

    @Test
    public void shouldShowHowManyTimesEachUserHasBeenGreeted(){
        GreetMethods greetUser = new GreetMethods();
        greetUser.greet("Nathri");
        greetUser.greet("James");
        greetUser.greet("Nathri");
        greetUser.greet("Thomas");
        assertEquals(Arrays.asList("user: Nathri, greeted: 2", "user: James, greeted: 1", "user: Thomas, greeted: 1"), greetUser.greeted());
    }

    @Test
    public void shouldShowHowManyTimesUsersHasBeenGreeted(){
        GreetMethods greetUser = new GreetMethods();
        greetUser.greet("Nathri");
        greetUser.greet("Nathri");
        assertEquals(Arrays.asList("user: Nathri, greeted: 2"), greetUser.greeted("Nathri"));
    }

    @Test
    public void shouldShowHowManyTimesASpecificUserHasBeenGreeted(){
        GreetMethods greetUser = new GreetMethods();
        greetUser.greet("Nathri");
        greetUser.greet("Nathri");
        greetUser.greet("John");
        greetUser.greet("Thomas");
        greetUser.greet("Sandra");
        greetUser.greet("Juniper");
        assertEquals(5, greetUser.counter());
    }

    @Test
    public void shouldClearListOfUsers(){
        GreetMethods greetUser = new GreetMethods();
        greetUser.greet("Nathri");
        greetUser.greet("John");
        greetUser.greet("Thomas");
        greetUser.greet("Sandra");
        greetUser.greet("Juniper");
        assertEquals(5, greetUser.counter());
        greetUser.clear();
        assertEquals(0, greetUser.counter());
    }

    @Test
    public void shouldClearOneGreetedUsers(){
        GreetMethods greetUser = new GreetMethods();
        greetUser.greet("Nathri");
        greetUser.greet("John");
        greetUser.greet("Thomas");
        greetUser.greet("Sandra");
        greetUser.greet("Juniper");
        assertEquals(5, greetUser.counter());
        greetUser.clear("Nathri");
        assertEquals(4, greetUser.counter());
    }

    @Test
    public void shouldReturnAllPossibleCommands(){
        GreetMethods greetUser = new GreetMethods();
        assertEquals(Arrays.asList("Greet", "Quit", "Help", "Greeted", "Clear", "Counter"), greetUser.help());
    }
}
