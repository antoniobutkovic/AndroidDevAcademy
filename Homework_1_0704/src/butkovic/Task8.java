package butkovic;

import javax.swing.*;

public class Task8 {

//    8. Write a method that takes a int number and returns a array of its digits.

    public static void main(String[] args) {

        int n = Integer.valueOf(JOptionPane.showInputDialog(null, "Enter integer number"));


        for (int i = 0; i < getIntegerArray(n).length; i++) {
            System.out.println(getIntegerArray(n)[i]);
        }
    }

    public static int[] getIntegerArray(int n){
        int digit;
        int number = n;
        int i = 0;

        while (number > 0) {
            number /= 10;
            i++;
        }

        number = n;
        int array[] = new int[i];

        for (int j = 0; j < i; j++) {
            digit = number%10;
            number = number/10;
            array[j] = digit;
        }
        return array;
    }
}
