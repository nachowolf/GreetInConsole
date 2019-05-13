package greet;

import greet.enums.GreetCommand;
import greet.enums.Language;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandExtractorTests {

    @Test
    public void extractGreetAndName(){
        CommandExtractor extractor = new CommandExtractor("Greet nathri");
        assertEquals(GreetCommand.greet, extractor.getCommand());
        assertEquals("nathri", extractor.getName());
    }

    @Test
    public void extractGreetAndNameAndLanguage(){
        CommandExtractor extractor = new CommandExtractor("Greet nathri Japanese");
        assertEquals(GreetCommand.greet, extractor.getCommand());
        assertEquals("nathri", extractor.getName());
        assertEquals(Language.japanese, extractor.getLanguage());
    }

    @Test
    public void extractHelpCommand(){
        CommandExtractor extractor = new CommandExtractor("Help");
        assertEquals(GreetCommand.help, extractor.getCommand());
    }

    @Test
    public void extractCommandNull(){
        CommandExtractor extractor = new CommandExtractor("grgsegesegr");
        assertEquals(null, extractor.getCommand());
    }
}
