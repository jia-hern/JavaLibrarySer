import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.util.Scanner;

public class MainSystem {
    static String fileName = null;
    static Library lib = new Library();
    static Scanner in = new Scanner(System.in);
    static Boolean running = true;

    public static void main(String[] args) {
        // run this as many times as the user wants to
        while (running) {
            System.out.println("\nEnter 0 for load a library." + "\nEnter 1 for save and quit"
                    + "\nEnter 2 for list all books in library" + "\nEnter 3 for add book to library");

            int answer = in.nextInt();
            switch (answer) {
                case 0:
                    System.out.println("Enter the file name to load");
                    // fileName = in.next();
                    // loadScript(fileName);
                    // instead of writing everything here we can write everything below and call a
                    // method to run whatever
                    loadScript(in.next());
                    break;

                case 1:
                    saveAndQuit();
                    break;
                case 2:
                    System.out.println(lib.toString());
                    break;
                case 3:
                    addBook();
                    break;

            }
        }
        System.exit(0);
    }

    private static void addBook() {
        int isbn;
        String title, author;
        double price;

        System.out.println("\nEnter Title: ");
        title = in.next();
        System.out.println("\nEnter Author: ");
        author = in.next();
        System.out.println("\nEnter ISBN: ");
        isbn = in.nextInt();
        System.out.println("\nEnter Price: ");
        price = in.nextDouble();

        Book b = new Book(isbn, title, author, price);
        lib.addBook(b);
        System.out.println("Book is added!");
    }

    private static void saveAndQuit() {
        System.out.println("Enter file name: ");
        fileName = in.next() + ".ser";
        running = false;
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(fileName);
            out = new ObjectOutputStream(fos);
            out.writeObject(lib);
            fos.close();
            out.close();
            System.out.println("File is saved!");
            System.out.println("Exitting application, Goodbye~");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void loadScript(String name) {
        FileInputStream fis = null;
        ObjectInputStream in = null;
        // ensure the the file has an extension of .ser
        // File file = new File(name);
        File file = new File(name + ".ser");
        if (file.exists()) {
            try {
                fis = new FileInputStream(file);
                in = new ObjectInputStream(fis);
                lib = (Library) in.readObject();
                fis.close();
                in.close();
                System.out.println("File is loaded!");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("\nThe file does not exists!");
        }
    }
}
