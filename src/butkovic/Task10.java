package butkovic;

import javax.swing.*;

public class Task10 {

//    10. Write a program that automatically converts English text to Morse code and vice versa.

    public static void main(String[] args) {

        int select;

        String[] morse = { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", "|" };
        char[] english= {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',' '};

        do {
            select = Integer.parseInt(JOptionPane.showInputDialog(null, "1. Morse to English (1) \n 2. English to Morse (2)"));
        }while (select != 1 && select != 2);

        String text = JOptionPane.showInputDialog(null, "Write any text:");



        if (select == 1){
            translateMorseToEnglish(text);
        }else if (select == 2){
            translateEnglishToMorse(text);
        }else {
            return;
        }

    }

    private static void translateMorseToEnglish(String text) {
        String array[] = text.split(" ");
        String result = "";

        for (int i = 0; i < array.length; i++) {
            switch (array[i]){
                case ".-":
                    result += "a";
                    break;
                case "-...":
                    result += "b";
                    break;
                case "-.-.":
                    result += "c";
                    break;
                case "-..":
                    result += "d";
                    break;
                case ".":
                    result += "e";
                    break;
                case "..-.":
                    result += "f";
                    break;
                case "--.":
                    result += "g";
                    break;
                case "....":
                    result += "h";
                    break;
                case "..":
                    result += "i";
                    break;
                case ".---":
                    result += "j";
                    break;
                case "-.-":
                    result += "k";
                    break;
                case ".-..":
                    result += "l";
                    break;
                case "--":
                    result += "m";
                    break;
                case "-.":
                    result += "n";
                    break;
                case "---":
                    result += "o";
                    break;
                case ".--.":
                    result += "p";
                    break;
                case "--.-":
                    result += "q";
                    break;
                case ".-.":
                    result += "r";
                    break;
                case "...":
                    result += "s";
                    break;
                case "-":
                    result += "t";
                    break;
                case "..-":
                    result += "u";
                    break;
                case "...-":
                    result += "v";
                    break;
                case ".--":
                    result += "w";
                    break;
                case "-..-":
                    result += "x";
                    break;
                case "-.--":
                    result += "y";
                    break;
                case "--..":
                    result += "z";
                    break;
                case "|":
                    result += " ";
                    break;
            }
        }
        System.out.print(result);
    }

    private static void translateEnglishToMorse(String text) {
        char array[] = text.toCharArray();
        String result = "";

        for (int i = 0; i < array.length; i++) {
            switch (array[i]){
                case 'a':
                    result += ".- ";
                    break;
                case 'b':
                    result += "-... ";
                    break;
                case 'c':
                    result += "-.-. ";
                    break;
                case 'd':
                    result += "-.-. ";
                    break;
                case 'e':
                    result += ". ";
                    break;
                case 'f':
                    result += "..-. ";
                    break;
                case 'g':
                    result += "--. ";
                    break;
                case 'h':
                    result += ".... ";
                    break;
                case 'i':
                    result += ".. ";
                    break;
                case 'j':
                    result += ".--- ";
                    break;
                case 'k':
                    result += "-.- ";
                    break;
                case 'l':
                    result += ".-.. ";
                    break;
                case 'm':
                    result += "-- ";
                    break;
                case 'n':
                    result += "-. ";
                    break;
                case 'o':
                    result += "--- ";
                    break;
                case 'p':
                    result += ".--. ";
                    break;
                case 'q':
                    result += "--.- ";
                    break;
                case 'r':
                    result += ".-. ";
                    break;
                case 's':
                    result += "... ";
                    break;
                case 't':
                    result += "- ";
                    break;
                case 'u':
                    result += "..- ";
                    break;
                case 'v':
                    result += "...- ";
                    break;
                case 'w':
                    result += ".-- ";
                    break;
                case 'x':
                    result += "-..- ";
                    break;
                case 'y':
                    result += "-.-- ";
                    break;
                case 'z':
                    result += "--.. ";
                    break;
                case ' ':
                    result += "| ";
                    break;
            }
        }

        System.out.print(result);
    }

}
