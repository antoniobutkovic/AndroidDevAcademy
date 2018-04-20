package butkovic.CRUD;

import butkovic.model.Author;
import butkovic.model.Category;
import butkovic.model.News;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NewsCRUD {

    public List<News> createNews(List<News> news, List<Category> categories, List<Author> authors){

        Scanner scanner = new Scanner(System.in);

        String name;
        String date;
        String text;
        String authorName;
        String authorSurname;
        String categoryName;
        String moreCategories;
        List<Category> addedCategories = new ArrayList<>();
        boolean isAuthor = false;
        boolean isCategory = false;


        System.out.println("Unesi naziv vijesti");
        name = scanner.nextLine();
        System.out.println("Unesi datum vijesti u obliku (dd.mm.yyyy)");
        date = scanner.nextLine();
        System.out.println("Unesi text vijesti");
        text = scanner.nextLine();

        do {
            System.out.println("Unesi ime autora");
            authorName = scanner.nextLine();
            System.out.println("Unesi prezime autora");
            authorSurname = scanner.nextLine();
            for (Author author: authors) {
                String a = author.getName()+author.getSurname();
                if (a.equals(authorName+authorSurname)){
                    isAuthor = true;
                    break;
                }
            }
            if (!isAuthor){
                System.out.println("Autor ne postoji");
            }
        }while (!isAuthor);


        do {
            System.out.println("Odaberi kategorije za vijest (Upisi ime kategorije) \nTrenutno dodane kategorije:");
            for (int i = 0; i < categories.size(); i++) {
                System.out.println(i+1 + ". " + categories.get(i).getName());
            }

            do {
                categoryName = scanner.nextLine();
                for (int i = 0; i < categories.size(); i++) {
                    String c = categories.get(i).getName();
                    if (c.equals(categoryName)){
                        isCategory = true;
                        addedCategories.add(new Category(categories.get(i).getName(), categories.get(i).getShortDesc()));
                        categories.remove(i);
                        break;
                    }
                }
                if (!isCategory){
                    System.out.println("Pogresno ime kategorije. Pokusajte ponovo");
                }
            }while (!isCategory);

            moreCategories = " ";
            if (!categories.isEmpty()){
                System.out.println("Dodaj jos kategorija (da/ne)");
                moreCategories = scanner.nextLine();
            }

        }while (moreCategories.equals("da"));


        news.add(new News(name, date, text, new Author(authorName, authorSurname), addedCategories));
        System.out.println("Vijest " + name + " je dodana");
        for (Category c :addedCategories) {
            categories.add(new Category(c.getName(), c.getShortDesc()));
        }
        return  news;
    }

    public void readNewsByCathegory(List<News> news, List<Category> categories){
        for (Category c : categories) {
            System.out.println("--- "+c.getName()+" ---");
            for (News n : news) {
                for (Category ctg : n.getCategories()) {
                    if (c.getName().equals(ctg.getName())){
                        System.out.println("IME VIJESTI:\n" + n.getName() + "\nIME AUTORA:\n" + n.getAuthor().getName() + "\nPREZIME AUTORA:\n" + n.getAuthor().getSurname() + "\nVIJEST:\n" + n.getText() + "\nDATUM:\n" + n.getDate() + "\n");
                    }
                }
            }
            System.out.println();
        }

    }

    public void readNewsByAuthor(List<News> news, List<Author> authors){
        String showCategory = "";
        for (Author a : authors){
            System.out.println("--- " + a.getName()+ " " + a.getSurname() + " ---");
            for (News n : news){
                String fullName = n.getAuthor().getName()+n.getAuthor().getSurname();
                if (fullName.equals(a.getName()+a.getSurname())){
                    for (Category c : n.getCategories()) {
                        showCategory += c.getName() + " ";
                    }
                    System.out.println("IME VIJESTI:\n" + n.getName() +  n.getAuthor().getSurname() + "\nVIJEST:\n" + n.getText() + "\nDATUM:\n" + n.getDate() + "\nKATEGORIJE:\n" + showCategory + "\n");
                }
            }
        }
        System.out.println();
    }

    public void readNewsByDate(List<News> news){
        String showCategory = "";
        for (News n : news) {
            System.out.println("--- " + n.getDate() + " ---");
            for (News n1 : news){
                if (n.getDate().equals(n1.getDate())){
                    for (Category c : n.getCategories()) {
                        showCategory += c.getName() + " ";
                    }
                    System.out.println("IME VIJESTI:\n" + n.getName() + "\nIME AUTORA:\n" + n.getAuthor().getName() + "\nPREZIME AUTORA:\n" + n.getAuthor().getSurname() + "\nVIJEST:\n" + n.getText() + "\nKATEGORIJE:\n" + showCategory + "\n");
                }
            }
        }
    }

    public void updateNews(List<News> news, List<Category> categories, List<Author> authors){

        Scanner scanner = new Scanner(System.in);

        boolean isUpdated = false;
        String name;
        System.out.println("Unesi ime vijesti");
        name = scanner.nextLine();

        for (News n: news) {
            if (n.getName().equals(name)) {
                isUpdated = true;
                news.remove(n);
                createNews(news, categories, authors);
                break;
            }
        }

        if (!isUpdated){
            System.out.println("Ime vijesti ne postoji. Pokusajte ponovo");
            updateNews(news, categories, authors);
        }
    }

    public List<News> deleteNews(List<News> news){
        Scanner scanner = new Scanner(System.in);

        boolean isDeleted = false;
        String name = "";
        System.out.println("Unesi ime vijesti");
        name = scanner.nextLine();

        for (int i = 0; i < news.size(); i++) {
            String n = news.get(i).getName();
            if (n.equals(name)){
                news.remove(i);
                isDeleted = true;
                break;
            }
        }

        if (isDeleted){
            System.out.println("Vijest je obrisana");
        }else {
            System.out.println("Vijest ne postoji. Pokusajte ponovo");
            deleteNews(news);
        }
        return  news;
    }

}
