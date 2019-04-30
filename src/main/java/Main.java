import greet.console.GreetConsole;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        System.out.println("Greet Console Application Started...");
        Scanner scanner = new Scanner(System.in);
        GreetConsole consoleApp = new GreetConsole();
        while(true){


//        JavaConsole console = new JavaConsole();
//        console.setBackground(Color.DARK_GRAY);
//        console.setForeground(Color.green);
//        console.setFont(new Font ("Ariel", Font.Bold, 12));
//        console.setTitle("Greet In Console Application");


            consoleApp.input(scanner.nextLine());

        }


    }
}
