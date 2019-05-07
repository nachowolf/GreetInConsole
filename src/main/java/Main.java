import greet.CommandExtractor;
import greet.methods.Helper;


import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Helper.print(Helper.ANSI_GREEN + "Greet Console Application Started... " + Helper.ANSI_RESET);
        Scanner scanner = new Scanner(System.in);
        CommandExtractor consoleApp = new CommandExtractor();
        while(true){

            consoleApp.extract(scanner.nextLine());

        }


    }
}
