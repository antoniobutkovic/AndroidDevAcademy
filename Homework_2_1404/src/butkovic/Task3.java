package butkovic;

import javax.swing.*;

public class Task3 {

//    3. Write a program that prints a multiplication table for numbers up to 12.

    public static void main(String[] args) {

        int n;

        do{
            n = Integer.parseInt(JOptionPane.showInputDialog(null, "Unesi n:"));
        }while (n < 1 || n > 12);

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.printf("%d ", i*j);
            }
            System.out.println();
        }
    }

}
