package greet.methods;

import greet.enums.Language;

public class Helper {

    public static String capitilize(String string){
        String capital = string.substring(0,1).toUpperCase() + string.substring(1);
        return capital;
    }

    public static String greetLanguage(Language language){

        String response = "Hello, ";

        if(language == Language.Japanese){
            response = "Konichiwa, ";
        }
        else if(language == Language.Thai){
            response = "Sawa dee krahp, ";
        }

        return response;
    }

    public static Language checkLanguage(String lang) {
        Language chosenLanguage = Language.English;
        for (Language language : Language.values()) {
            if (capitilize(lang).equals(language.toString())) {
                chosenLanguage = language;
                break;
            }
        }
        return chosenLanguage;
    }
}
