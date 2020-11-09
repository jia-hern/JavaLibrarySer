import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class Library extends Object implements Serializable {
    private List<Book> collection;

    public Library() {
        collection = new ArrayList<Book>();
    }

    public void addBook(Book book) {
        collection.add(book);
    }

    @Override
    public String toString() {
        String total = "\n";
        /*
         * for (int i=0;i<collection.size(); i++){ Book b = collection.get(i); total =
         * total + b.toString(); }
         */
        // above works, but can use iterator too
        // Iterator<Book> acts as a pointer
        Iterator<Book> i = collection.iterator();
        while (i.hasNext()) {
            // casting
            Book b = (Book) i.next();
            total = total + b.toString();
        }
        return total;
    }
}
