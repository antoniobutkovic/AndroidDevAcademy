package butkovic.main;

import butkovic.CRUD.AuthorCRUD;
import butkovic.CRUD.CategoryCRUD;
import butkovic.CRUD.NewsCRUD;
import butkovic.model.Author;
import butkovic.model.Category;
import butkovic.model.News;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainActivity {

    private static List<Author> authors;
    private static List<Category> categories;
    private static List<News> news;

    public static void main(String[] args) {

        authors = new ArrayList<>();
        categories = new ArrayList<>();
        news = new ArrayList<>();

        selectStartingOperation();

    }

    private static void selectStartingOperation() {
        int selection = 0;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Odaberi kategoriju: (1-3) \n 1. Author \n 2. Category \n 3. News");
            selection = scanner.nextInt();
        }while (selection < 1 || selection > 3);

        switch (selection){
            case 1:
                selectAuthorOperation();
                break;
            case 2:
                selectCategoryOperation();
                break;
            case 3:
                selectNewsOperation();
                break;
        }
    }

    private static void selectAuthorOperation() {
        int selection = 0;
        Scanner scanner = new Scanner(System.in);
        AuthorCRUD authorCRUD = new AuthorCRUD();
        do {
            System.out.println("Odaberi radnju: (1-4) \n 1. C \n 2. R \n 3. U \n 4. D");
            selection = scanner.nextInt();
        }while (selection < 1 || selection > 4);

        switch (selection){
            case 1:
                authors = authorCRUD.createAuthor(authors);
                selectStartingOperation();
                break;
            case 2:
                if (authors.isEmpty()){
                    System.out.println("Nema autora");
                    selectAuthorOperation();
                }else {
                    authorCRUD.readAuthor(authors);
                    selectStartingOperation();
                }
                break;
            case 3:
                if (authors.isEmpty()){
                    System.out.println("Nema autora");
                    selectAuthorOperation();
                }else {
                    authors = authorCRUD.updateAuthor(authors);
                    selectStartingOperation();
                }
                break;
            case 4:
                if (authors.isEmpty()){
                    System.out.println("Nema autora");
                    selectAuthorOperation();
                }else {
                    authors = authorCRUD.deleteAuthor(authors);
                    selectStartingOperation();
                }
                break;
        }
    }

    private static void selectCategoryOperation() {
        int selection = 0;
        Scanner scanner = new Scanner(System.in);
        CategoryCRUD categoryCRUD = new CategoryCRUD();
        do {
            System.out.println("Odaberi radnju: (1-4) \n 1. C \n 2. R \n 3. U \n 4. D");
            selection = scanner.nextInt();
        }while (selection < 1 || selection > 4);

        switch (selection){
            case 1:
                categories = categoryCRUD.createCategory(categories);
                selectStartingOperation();
                break;
            case 2:
                if (categories.isEmpty()){
                    System.out.println("Nema kategorija");
                    selectCategoryOperation();
                }else {
                    categoryCRUD.readCategory(categories);
                    selectStartingOperation();
                }
                break;
            case 3:
                if (categories.isEmpty()){
                    System.out.println("Nema kategorija");
                    selectCategoryOperation();
                }else {
                    categories = categoryCRUD.updateCategory(categories);
                    selectStartingOperation();
                }
                break;
            case 4:
                if (categories.isEmpty()){
                    System.out.println("Nema kategorija");
                    selectCategoryOperation();
                }else {
                    categories = categoryCRUD.deleteCategory(categories);
                    selectStartingOperation();
                }
                break;
        }
    }

    private static void selectNewsOperation() {
        int selection = 0;
        Scanner scanner = new Scanner(System.in);
        NewsCRUD newsCRUD = new NewsCRUD();
        do {
            System.out.println("Odaberi radnju: (1-4) \n 1. C \n 2. R \n 3. U \n 4. D");
            selection = scanner.nextInt();
        }while (selection < 1 || selection > 4);

        switch (selection){
            case 1:
                if (authors.isEmpty() || categories.isEmpty()){
                    System.out.println("Potrebno je prvo kreirati autora i kategoriju");
                    selectStartingOperation();
                }else {
                    news = newsCRUD.createNews(news, categories, authors);
                    selectStartingOperation();
                }
                break;
            case 2:
                if (news.isEmpty()){
                    System.out.println("Nema vijesti");
                    selectNewsOperation();
                }else {
                    selectNewsReadingOperation();
                }
                break;
            case 3:
                if (news.isEmpty()){
                    System.out.println("Nema vijesti");
                    selectNewsOperation();
                }else {
                    newsCRUD.updateNews(news, categories, authors);
                    selectStartingOperation();
                }
                break;
            case 4:
                if (news.isEmpty()){
                    System.out.println("Nema vijesti");
                    selectNewsOperation();
                }else {
                    news = newsCRUD.deleteNews(news);
                    selectStartingOperation();
                }
                break;
        }
    }

    private static void selectNewsReadingOperation() {
        int selection = 0;
        Scanner scanner = new Scanner(System.in);
        NewsCRUD newsCRUD = new NewsCRUD();

        do {
            System.out.println("Odaberi kako zelis ispisati vijesti: (1-4) \n 1. Po kategorijama \n 2. Po autorima \n 3. Po datumu");
            selection = scanner.nextInt();
        }while (selection < 1 || selection > 3);

        switch (selection){
            case 1:
                newsCRUD.readNewsByCathegory(news, categories);
                selectStartingOperation();
                break;
            case 2:
                newsCRUD.readNewsByAuthor(news, authors);
                selectStartingOperation();
                break;
            case 3:
                newsCRUD.readNewsByDate(news);
                selectStartingOperation();
                break;
        }
    }

}
