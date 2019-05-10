import greet.CommandExecuter;
import greet.CommandExtractor;
import greet.methods.Helper;
import greet.greeter.*;


import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Helper.print(Helper.ANSI_GREEN + "Greet Console Application Started... " + Helper.ANSI_RESET);
        Scanner scanner = new Scanner(System.in);
        Helper helper = new Helper();
        GreetCounter counter = new GreetCounterMap();
        CommandExecuter executer = new CommandExecuter(counter);
        while(true){

            CommandExtractor extractor = new CommandExtractor(scanner.nextLine());
            helper.print(executer.execute(extractor));

        }


    }
}
