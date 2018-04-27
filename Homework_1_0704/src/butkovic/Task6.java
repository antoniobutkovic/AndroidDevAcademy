package butkovic;

public class Task6 {

//    6. Write a method that reverses a array.

    public static void main(String[] args) {
        int a[] = {10, 600, 24, 65, 600, 1, 89, 24, 543};

        reverseArray(a);
    }

    private static void reverseArray(int[] array) {
        for (int i = array.length-1; i >= 0; i--) {
            System.out.println(array[i]);
        }
    }

}
