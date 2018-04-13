package butkovic;

public class Task9 {

//    9. Write a program that prints Matrix code lookalike in console.

    public static void main(String[] args) {
        
        int matrix [][] = {{13, 99, 56},{12, 68, 32}, {87, 20, 33}};

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

}
