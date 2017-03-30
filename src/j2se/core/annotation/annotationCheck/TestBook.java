package j2se.core.annotation.annotationCheck;

/**
 * Created by yobdc on 2017/01/17.
 */
public class TestBook {
    public static void main(String[] args) {
        Book book = new Book();
        book.setIsbn(1001);
        book.setBookName("");
        book.setUnitPrice(2d);

        if (Tool.checkInput("bookName", book.getBookName(), book)) {
            if (Tool.checkInput("unitPrice", book.getUnitPrice(), book)) {
                System.out.println(book);
            }
        }
    }
}
