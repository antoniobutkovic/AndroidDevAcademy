package butkovic;

public class Task7 {

//    7. Write a method that returns the elements on odd positions in a array

    public static void main(String[] args) {
        int a[] = {10, 600, 24, 65, 600, 1, 89, 24, 543};

        for (int i = 0; i < a.length; i++) {
            if (i % 2 != 0){
                System.out.println(a[i]);
            }
        }
    }

}
