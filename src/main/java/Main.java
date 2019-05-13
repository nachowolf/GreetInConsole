import greet.CommandExecuter;
import greet.CommandExtractor;
import greet.greeter.*;
import greet.methods.Helper;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Helper.print("###### CONSOLE GREETER APPLICATION ######");
        Helper.print(Helper.ANSI_GREEN + "Greet Console Application Started... " + Helper.ANSI_RESET);
        Scanner scanner = new Scanner(System.in);
        Helper helper = new Helper();
        GreetCounter counter = new GreetCounterDB();
        CommandExecuter executer = new CommandExecuter(counter);
        while (true) {

            CommandExtractor extractor = new CommandExtractor(scanner.nextLine());
            String result = executer.execute(extractor);
            if (result.equals("quit")) {
                helper.print(Helper.ANSI_RED + "Closing Greeter Application..." + Helper.ANSI_RESET);
                break;
            } else {
                helper.print(result);
            }

        }

    }
}
