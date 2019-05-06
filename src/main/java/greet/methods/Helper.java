package greet.methods;

import greet.enums.Language;

public class Helper {

    public String capitilize(String string){
        String capital = string.substring(0,1).toUpperCase() + string.substring(1);
        return capital;
    }

    public String greetLanguage(Language language){

        String response = "Hello, ";

        if(language == Language.Japanese){
            response = "Konichiwa, ";
        }
        else if(language == Language.Thai){
            response = "Sawa dee krahp, ";
        }

        return response;
    }
}
