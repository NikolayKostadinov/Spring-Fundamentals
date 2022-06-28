package bg.manhattan.books.init;

import bg.manhattan.books.model.entity.AuthorEntity;
import bg.manhattan.books.model.entity.BookEntity;
import bg.manhattan.books.repository.AuthorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DbInitializer implements CommandLineRunner {
    private final AuthorRepository authorRepository;

    public DbInitializer(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (this.authorRepository.findAll().isEmpty()) {
            List<AuthorEntity> authors = new ArrayList<>();
            AuthorEntity author1 = new AuthorEntity().setName("Елин Пелин");
            authors.add(author1.addBook(new BookEntity().setTitle("Нане Стоичковата върба").setIsbn("9789540903736").setAuthor(author1))
                    .addBook(new BookEntity().setTitle("Голяма книга на приказките").setIsbn("9789546600332").setAuthor(author1))
                    .addBook(new BookEntity().setTitle("Разкази").setIsbn("9789547921108").setAuthor(author1)));

            AuthorEntity author2 = new AuthorEntity().setName("Радко Пенев");
            authors.add(author2.addBook(new BookEntity().setTitle("Струни").setIsbn("978-954-28-2989-8").setAuthor(author2))
                    .addBook(new BookEntity().setTitle("Грехът на Лилит").setIsbn("9789542824695").setAuthor(author2))
                    .addBook(new BookEntity().setTitle("Ритуалът").setIsbn("9789542820833").setAuthor(author2)));

            AuthorEntity author3 = new AuthorEntity().setName("Иван Вазов");
            authors.add(author3.addBook(new BookEntity().setTitle("Аз съм българче").setIsbn("9789546572042").setAuthor(author3))
                    .addBook(new BookEntity().setTitle("Немили-недраги").setIsbn("9789540902425").setAuthor(author3))
                    .addBook(new BookEntity().setTitle("Епопея на забравените").setIsbn("954657368Х").setAuthor(author3)));

            this.authorRepository.saveAll(authors);
        }
    }
}
