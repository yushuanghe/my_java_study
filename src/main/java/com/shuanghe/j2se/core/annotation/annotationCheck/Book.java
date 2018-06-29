package com.shuanghe.j2se.core.annotation.annotationCheck;

/**
 * Created by yobdc on 2017/01/17.
 */
public class Book {

    private Integer isbn;

    @Check(request = true)
    private String bookName;

    @Check(request = false, range = "20-200")
    private Double unitPrice;

    public Integer getIsbn() {
        return isbn;
    }

    public void setIsbn(Integer isbn) {
        this.isbn = isbn;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn=" + isbn +
                ", bookName='" + bookName + '\'' +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
