package greet.methods;

import greet.enums.Language;

public class Helper {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static String capitilize(String string) {
        String capital = string.substring(0, 1).toUpperCase() + string.substring(1);
        return capital;
    }

    public static String greetLanguage(Language language) {

        String response = "Hello, ";

        if (language == Language.japanese) {
            response = "Konichiwa, ";
        } else if (language == Language.thai) {
            response = "Sawa dee krahp, ";
        }

        return response;
    }

    public static Language checkLanguage(String lang) {
        Language chosenLanguage = Language.english;
        for (Language language : Language.values()) {
            if (capitilize(lang).equals(language.toString())) {
                chosenLanguage = language;
                break;
            }
        }
        return chosenLanguage;
    }

    public static void print(String input) {

        System.out.println(ANSI_CYAN + input + " " + ANSI_RESET);

    }

}
