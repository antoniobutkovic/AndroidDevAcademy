package butkovic;

public class Task4 {

//    4. Write a program that prints the next 20 leap years.

    public static void main(String[] args) {

        int currentYear = 2018;
        int yearCounter = 0;

        do {
            if (currentYear % 4 == 0){
                yearCounter++;
                System.out.printf("%d ", currentYear);
            }
            currentYear++;
        }while (yearCounter != 20);
    }

}
