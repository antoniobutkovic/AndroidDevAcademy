package butkovic;

public class Task5 {

//    5. Write a method that returns the largest element in a array.

    public static void main(String[] args) {

        int a[] = {10, 600, 24, 65, 600, 1, 89, 24, 543};
        int max = 0;

        for (int i = 0; i < a.length-1; i++) {
            if (max <= a[i]){
                max = a[i];
            }
        }

        System.out.print(max);
    }
}
