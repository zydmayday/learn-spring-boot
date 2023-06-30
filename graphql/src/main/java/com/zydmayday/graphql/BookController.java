package com.zydmayday.graphql;

import org.reactivestreams.Publisher;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Controller
public class BookController {
    @QueryMapping
    public Optional<BookTable> getBookById(@Argument String id) {
        return BookTable.books.stream().filter(b -> b.id().equals(id)).findFirst();
    }

    @QueryMapping
    public List<BookTable> getBookList() {
        return BookTable.books;
    }

    @MutationMapping
    public BookTable addBook(@Argument BookTable book) {
        BookTable.books.add(book);
        return book;
    }

}

record BookTable(String id, String name) {
    public static List<BookTable> books = new ArrayList<>();

}
