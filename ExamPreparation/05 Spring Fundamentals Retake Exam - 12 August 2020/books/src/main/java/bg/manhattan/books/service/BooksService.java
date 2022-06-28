package bg.manhattan.books.service;

import bg.manhattan.books.model.dto.BookDTO;

import java.util.List;
import java.util.Optional;

public interface BooksService {
    public List<BookDTO> getAllBooks();

    Optional<BookDTO> getBookById(Long id);

    void deleteBookById(Long id);

    Long createBook(BookDTO book);

    Long updateBook(Long bookId, BookDTO book);
}
