import greet.CommandExtractor;


import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        System.out.println("Greet Console Application Started...");
        Scanner scanner = new Scanner(System.in);
        CommandExtractor consoleApp = new CommandExtractor();
        while(true){


            consoleApp.extract(scanner.nextLine());

        }


    }
}
