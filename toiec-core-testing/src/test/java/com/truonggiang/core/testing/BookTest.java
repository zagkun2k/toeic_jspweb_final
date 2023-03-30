//package com.truonggiang.core.testing;
//
//import com.truonggiang.core.model.Book;
//import org.testng.annotations.Test;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class BookTest {
//
//    @Test
//    public void checkBookModel() {
//        Map<Integer, Book> bookMap = new HashMap<>();
//
//        Book a = new Book(98, "bookA", "Giang", "NVC", "5");
//        Book b = new Book(99, "bookB", "Le", "TT", "5");
//        Book c = new Book(100, "bookC", "Me", "KDP", "5");
//
//        bookMap.put(1, a);
//        bookMap.put(2, b);
//        bookMap.put(3, c);
//
//        for (Map.Entry<Integer, Book> entry : bookMap.entrySet()) {
//            int key;
//            Book temp;
//            key = entry.getKey();
//            temp = entry.getValue();
//
//            System.out.println("Key: " + key);
//            System.out.println("Name: " + temp.getName() + " " + "Author: " + temp.getAuthor() + " " + "Publisher: " + temp.getPublisher() + " " + "Quantity: " + temp.getQuantity());
//        }
//    }
//}
