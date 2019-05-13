package greet;

import greet.greeter.GreetCounterMap;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandExecuterDBTests {
    GreetCounterMap greetCounter = new GreetCounterMap();

    @Test
    public void executeGreetUser(){
        CommandExecuter executer = new CommandExecuter(greetCounter);
        CommandExtractor extractor = new CommandExtractor("greet nathri");

        assertEquals("Hello, Nathri", executer.execute(extractor));
    }

    @Test
    public void executeGreetUserThai(){
        CommandExecuter executer = new CommandExecuter(greetCounter);
        CommandExtractor extractor = new CommandExtractor("greet nathri thai");

        assertEquals("Sawa dee krahp, Nathri", executer.execute(extractor));
    }

    @Test
    public void executeGreetUserJapanese(){
        CommandExecuter executer = new CommandExecuter(greetCounter);
        CommandExtractor extractor = new CommandExtractor("greet nathri japanese");

        assertEquals("Konichiwa, Nathri", executer.execute(extractor));
    }

    @Test
    public void executeGreetedSingleUser(){
        CommandExecuter executer = new CommandExecuter(greetCounter);

        CommandExtractor extract1 = new CommandExtractor("greet nathri");
        executer.execute(extract1);

        CommandExtractor extract2 = new CommandExtractor("greet nathri");
        executer.execute(extract2);

        CommandExtractor extract3 = new CommandExtractor("greet john");
        executer.execute(extract3);

        CommandExtractor extractor = new CommandExtractor("greeted nathri");
        assertEquals("user: Nathri, greeted: 2", executer.execute(extractor));
    }

    @Test
    public void executeGreeted(){
        CommandExecuter executer = new CommandExecuter(greetCounter);

        CommandExtractor extract1 = new CommandExtractor("greet nathri");
        executer.execute(extract1);

        CommandExtractor extract2 = new CommandExtractor("greet nathri");
        executer.execute(extract2);

        CommandExtractor extract3 = new CommandExtractor("greet john");
        executer.execute(extract3);

        CommandExtractor extract4 = new CommandExtractor("greet ann");
        executer.execute(extract4);

        CommandExtractor extractor = new CommandExtractor("greeted");
        assertEquals("user: Ann, greeted: 1 user: John, greeted: 1 user: Nathri, greeted: 2", executer.execute(extractor).replaceAll("\n", " ").replaceAll("\r", " "));
    }

    @Test
    public void executeClearUser() {
        CommandExecuter executer = new CommandExecuter(greetCounter);

        CommandExtractor extract1 = new CommandExtractor("greet nathri");
        executer.execute(extract1);

        CommandExtractor extract2 = new CommandExtractor("greet nathri");
        executer.execute(extract2);

        CommandExtractor extract3 = new CommandExtractor("greet john");
        executer.execute(extract3);

        CommandExtractor extract4 = new CommandExtractor("greet ann");
        executer.execute(extract4);

        CommandExtractor extractClear = new CommandExtractor("clear john");
        assertEquals("User has been deleted", executer.execute(extractClear));

        CommandExtractor extractorGreeted = new CommandExtractor("greeted");
        assertEquals("user: Ann, greeted: 1 user: Nathri, greeted: 2", executer.execute(extractorGreeted).replaceAll("\n", " ").replaceAll("\r", " "));
    }

    @Test
    public void executeClearAllUser() {
        CommandExecuter executer = new CommandExecuter(greetCounter);

        CommandExtractor extract1 = new CommandExtractor("greet nathri");
        executer.execute(extract1);

        CommandExtractor extract2 = new CommandExtractor("greet nathri");
        executer.execute(extract2);

        CommandExtractor extract3 = new CommandExtractor("greet john");
        executer.execute(extract3);

        CommandExtractor extract4 = new CommandExtractor("greet ann");
        executer.execute(extract4);

        CommandExtractor extractClear = new CommandExtractor("clear");
        assertEquals("All users have been deleted", executer.execute(extractClear));

        CommandExtractor extractorGreeted = new CommandExtractor("greeted");
        assertEquals("No users has been greeted", executer.execute(extractorGreeted).replaceAll("\n", " ").replaceAll("\r", " "));
    }

    @Test
    public void executeCounter() {
        CommandExecuter executer = new CommandExecuter(greetCounter);

        CommandExtractor extract1 = new CommandExtractor("greet micheal");
        executer.execute(extract1);

        CommandExtractor extract2 = new CommandExtractor("greet nathri");
        executer.execute(extract2);

        CommandExtractor extract3 = new CommandExtractor("greet john");
        executer.execute(extract3);

        CommandExtractor extract4 = new CommandExtractor("greet ann");
        executer.execute(extract4);

        CommandExtractor extractCounter = new CommandExtractor("counter");
        assertEquals("Users: 4", executer.execute(extractCounter));

    }

    @Test
    public void executeHelp(){
        CommandExecuter executer = new CommandExecuter(greetCounter);
        CommandExtractor extractor = new CommandExtractor("help");
        assertEquals("Greeter Application Commands: greet quit help greeted clear counter", executer.execute(extractor).replaceAll("\n", " ").replaceAll("\r", " "));
    }

    @Test
    public void executeQuit(){
        CommandExecuter executer = new CommandExecuter(greetCounter);
        CommandExtractor extractor = new CommandExtractor("quit");
        assertEquals("quit", executer.execute(extractor));
    }
}
