package butkovic.CRUD;

import butkovic.model.Author;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class AuthorCRUD {

    public List<Author> createAuthor(List<Author> authors){

        Scanner scanner = new Scanner(System.in);

        String name = "";
        String surname = "";
        System.out.println("Unesi ime autora");
        name = scanner.nextLine();
        System.out.println("Unesi prezime autora");
        surname = scanner.nextLine();

        authors.add(new Author(name, surname));
        System.out.println("Autor " + name + " je dodan");
        return  authors;
    }

    public void readAuthor(List<Author> authors){
        System.out.println("Trenutno dodani autori: ");
        for (Author author : authors) {
            System.out.println(author.getName() + " " + author.getSurname());
        }
    }

    public List<Author> updateAuthor(List<Author> authors){

        Scanner scanner = new Scanner(System.in);

        boolean isUpdated = false;
        String name = "";
        String surname = "";
        System.out.println("Unesi ime autora");
        name = scanner.nextLine();
        System.out.println("Unesi prezime autora");
        surname = scanner.nextLine();

        for (int i = 0; i < authors.size(); i++) {
            String a = authors.get(i).getName()+authors.get(i).getSurname();
            if (a.equals(name+surname)){
                String newName = "";
                String newSurname = "";
                System.out.println("Unesi novo ime autora");
                newName = scanner.nextLine();
                System.out.println("Unesi novo prezime autora");
                newSurname = scanner.nextLine();

                authors.set(i, new Author(newName, newSurname));

                isUpdated = true;
                break;
            }
        }

        if (isUpdated){
            System.out.println("Autor je promijenjen");
        }else {
            System.out.println("Autor ne postoji. Pokusajte ponovo");
            updateAuthor(authors);
        }
        return  authors;
    }

    public List<Author> deleteAuthor(List<Author> authors){
        Scanner scanner = new Scanner(System.in);

        boolean isDeleted = false;
        String name = "";
        String surname = "";
        System.out.println("Unesi ime autora");
        name = scanner.nextLine();
        System.out.println("Unesi prezime autora");
        surname = scanner.nextLine();

        for (int i = 0; i < authors.size(); i++) {
            String a = authors.get(i).getName()+authors.get(i).getSurname();
            if (a.equals(name+surname)){
                authors.remove(i);
                isDeleted = true;
                break;
            }
        }

        if (isDeleted){
            System.out.println("Autor je obrisan");
        }else {
            System.out.println("Autor ne postoji. Pokusajte ponovo");
            deleteAuthor(authors);
        }
        return  authors;
    }

}
