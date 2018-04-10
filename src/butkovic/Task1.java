package butkovic;

import javax.swing.*;

public class Task1 {

//    1. Write a program that asks the user for a number n and prints the sum of the numbers 1 to n

    public static void main(String[] args) {

        int n;
        int sum = 0;

        do {
            n = Integer.parseInt(JOptionPane.showInputDialog(null, "Unesi n:"));
        }while (n < 1);

        for (int i = 1; i <= n; i++) {
            sum += i;
        }

        System.out.print(sum);
    }
}
