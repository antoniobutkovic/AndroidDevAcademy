package butkovic.CRUD;

import butkovic.model.Category;

import java.util.List;
import java.util.Scanner;

public class CategoryCRUD {

    public List<Category> createCategory(List<Category> categories){

        Scanner scanner = new Scanner(System.in);

        String name = "";
        String desc = "";
        System.out.println("Unesi ime kategorije");
        name = scanner.nextLine();
        System.out.println("Unesi kratak opis kategorije");
        desc = scanner.nextLine();

        categories.add(new Category(name, desc));
        System.out.println("Kategorija " + name + " je dodana");
        return  categories;
    }

    public void readCategory(List<Category> categories){
        System.out.println("Trenutno dodane kategorije: ");
        for (Category category : categories) {
            System.out.println(category.getName());
        }
    }

    public List<Category> updateCategory(List<Category> categories){

        Scanner scanner = new Scanner(System.in);

        boolean isUpdated = false;
        String name = "";
        String desc = "";
        System.out.println("Unesi ime kategorije");
        name = scanner.nextLine();
        System.out.println("Unesi kratak opis kategorije");
        desc = scanner.nextLine();

        for (int i = 0; i < categories.size(); i++) {
            String a = categories.get(i).getName()+categories.get(i).getShortDesc();
            if (a.equals(name+desc)){
                String newName = "";
                String newDesc = "";
                System.out.println("Unesi novo ime kategorije");
                newName = scanner.nextLine();
                System.out.println("Unesi novi opis kategorije");
                newDesc = scanner.nextLine();

                categories.set(i, new Category(newName, newDesc));

                isUpdated = true;
                break;
            }
        }

        if (isUpdated){
            System.out.println("Kategorija je promijenjena");
        }else {
            System.out.println("Kategorija ne postoji. Pokusajte ponovo");
            updateCategory(categories);
        }
        return  categories;
    }

    public List<Category> deleteCategory(List<Category> categories){
        Scanner scanner = new Scanner(System.in);

        boolean isDeleted = false;
        String name = "";
        String desc = "";
        System.out.println("Unesi ime kategorije");
        name = scanner.nextLine();
        System.out.println("Unesi kratak opis kategorije");
        desc = scanner.nextLine();

        for (int i = 0; i < categories.size(); i++) {
            String a = categories.get(i).getName()+categories.get(i).getShortDesc();
            if (a.equals(name+desc)){
                categories.remove(i);
                isDeleted = true;
                break;
            }
        }

        if (isDeleted){
            System.out.println("Kategorija je obrisana");
        }else {
            System.out.println("Kategorija ne postoji. Pokusajte ponovo");
            deleteCategory(categories);
        }
        return  categories;
    }

}
