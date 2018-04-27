package butkovic;

import java.util.Random;

public class Task9 {

//    9. Write a program that prints Matrix code lookalike in console.

    public static void main(String[] args) {

        Random random = new Random();
        int counter  = 0;

        for (;;){
            System.out.print(random.nextInt(2));
            counter++;
            if (counter == 50){
                System.out.println();
                counter = 0;
            }
        }
    }

}
