package butkovic;

import javax.swing.*;

public class Task2 {

//  2. Write a program that asks the user for a number n and gives them the possibility to choose between computing the sum and computing the product of 1,…,n. pr

    public static void main(String[] args) {

        int n;
        int result;
        String operation;

        do {
            n = Integer.valueOf(JOptionPane.showInputDialog(null, "Unesi n:"));
        }while (n < 1);

        do {
            operation = JOptionPane.showInputDialog(null, "Izaberi radnju s/p");
        }while (!operation.equals("s") && !operation.equals("p"));

        if (operation.equals("s")){
            result = 0;
            for (int i = 1; i <= n; i++) {
                result += i;
            }
            System.out.println(result);
        }else if (operation.equals("p")){
            result = 1;
            for (int i = 1; i <= n; i++) {
                result *= i;
            }
            System.out.println(result);
        }


    }
}
