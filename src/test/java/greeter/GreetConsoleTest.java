package greeter;

import org.junit.jupiter.api.Test;
import greet.greeter.GreetCommands;
import greet.greeter.Language;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreetConsoleTest {

    @Test
    public void shouldGreetUserInEnglish(){
        GreetConsole greetUser = new GreetConsole();
        assertEquals("Hello, Nathri",greetUser.greet("Nathri English"));
    }

    @Test
    public void shouldGreetUserInJapanese(){
        GreetConsole greetUser = new GreetConsole();
        assertEquals("Konichiwa, Nathri",greetUser.greet("Nathri Japanese"));
    }

    @Test
    public void shouldGreetUserInThai(){
        GreetConsole greetUser = new GreetConsole();
        assertEquals("Sawa dee krahp, Nathri",greetUser.greet("Nathri Thai"));
    }

    @Test
    public void shouldGreetUserInEnglishByDefault(){
        GreetConsole greetUser = new GreetConsole();
        assertEquals("Sawa dee krahp, Nathri",greetUser.greet("Nathri"));
    }

    @Test
    public void shouldShowHowManyTimesEachUserHasBeenGreeted(){
        GreetConsole greetUser = new GreetConsole();
        greetUser.greet("Nathri");
        greetUser.greet("James");
        greetUser.greet("Nathri");
        assertEquals(Arrays.asList("user: James, greeted: 1", "user: Nathri, greeted: 2"), greetUser.greeted());
    }

    @Test
    public void shouldShowHowManyTimesUsersHasBeenGreeted(){
        GreetConsole greetUser = new GreetConsole();
        greetUser.greet("Nathri");
        greetUser.greet("Nathri");
        assertEquals(Arrays.asList("user: Nathri, greeted: 2"), greetUser.greeted("Nathri"));
    }

    @Test
    public void shouldShowHowManyTimesASpecificUserHasBeenGreeted(){
        GreetConsole greetUser = new GreetConsole();
        greetUser.greet("Nathri");
        greetUser.greet("John");
        greetUser.greet("Thomas");
        greetUser.greet("Sandra");
        greetUser.greet("Juniper");
        assertEquals(Arrays.asList(5), greetUser.counter());
    }

    @Test
    public void shouldReturnHowManyUsersHasBeenGreeted(){
        GreetConsole greetUser = new GreetConsole();
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
    public void shouldClearAllGreetedUsers(){
        GreetConsole greetUser = new GreetConsole();
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
        GreetConsole greetUser = new GreetConsole();
        assertEquals(Arrays.asList("counter","Quit", "greet", "greeted", "clear", "help"), greetUser.help());
    }
}
