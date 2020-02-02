package com.bookstore;

import com.bookstore.entity.Book;
import com.bookstore.naturalid.NaturalRepositoryImpl;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.bookstore.service.BookstoreService;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = NaturalRepositoryImpl.class)
public class MainApplication {

    private final BookstoreService bookstoreService;

    public MainApplication(BookstoreService bookstoreService) {
        this.bookstoreService = bookstoreService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {

            bookstoreService.persistTwoBooks();

            Book book = bookstoreService.fetchFirstBookByNaturalId();
            System.out.println("\nFirst fetch:" + book);                        
            
            Book bookAgain = bookstoreService.fetchFirstBookByNaturalIdAgain();
            System.out.println("\nFetch again:" + bookAgain);                        
        };
    }
}
/*
 * 
 * How To Use Hibernate @NaturalIdCache For Skipping The Entity Identifier Retrieval

Description: This is a SpringBoot - MySQL application that maps a natural business key using Hibernate @NaturalId. This implementation allows us to use @NaturalId as it was provided by Spring. Moreover, this application uses Second Level Cache (EhCache) and @NaturalIdCache for skipping the entity identifier retrieval from the database.

Key points:

enable Second Level Cache (EhCache)
annotate entity with @NaturalIdCache for caching natural ids
optionally, annotate entity with @Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "Book") for caching entites as well
Output sample (for MySQL with IDENTITY generator, @NaturalIdCache and @Cache):
 * 
 * 
 * 
 */
