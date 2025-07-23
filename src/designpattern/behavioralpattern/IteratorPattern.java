package designpattern.behavioralpattern;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// 커스텀 Aggregate 인터페이스
interface BookCollection {
    Iterator<String> createIterator();
}

class Library implements BookCollection {
    private List<String> books = new ArrayList<>();

    public void addBook(String title) {
        books.add(title);
    }

    @Override
    public Iterator<String> createIterator() {
        return books.iterator(); // 자바 내장 Iterator 반환
    }
}

public class IteratorPattern {
    public static void main(String[] args) {
        Library library = new Library();
        library.addBook("Effective Java");
        library.addBook("Clean Code");
        library.addBook("Design Patterns");

        Iterator<String> iterator = library.createIterator();

        System.out.println("📚 도서 목록:");
        while (iterator.hasNext()) {
            System.out.println("- " + iterator.next());
        }
    }
}
