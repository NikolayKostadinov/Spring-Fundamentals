package bg.manhattan.books.web;

import bg.manhattan.books.model.dto.BookDTO;
import bg.manhattan.books.service.BooksService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BooksController {
    private final BooksService booksService;

    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookDTO> allBooks = this.booksService.getAllBooks();
        return ResponseEntity.ok(allBooks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable("id") Long id) {
        Optional<BookDTO> book = this.booksService.getBookById(id);
        if (book.isEmpty()) {
            return ResponseEntity
                    .notFound()
                    .build();
        }

        return ResponseEntity.ok(book.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookDTO> deleteBookById(@PathVariable("id") Long id) {
        this.booksService.deleteBookById(id);
        return ResponseEntity
                .noContent()
                .build();
    }

    @PostMapping()
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO book,
                                              UriComponentsBuilder uriComponentsBuilder) {
        Long bookId = this.booksService.createBook(book);
        URI uri = uriComponentsBuilder.path("/book/{id}")
                .buildAndExpand(bookId).toUri();
        return ResponseEntity
                .created(uri)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable("id") Long id,
                                              @RequestBody BookDTO book,
                                              UriComponentsBuilder uriComponentsBuilder) {
        try {
            Long bookId = this.booksService.updateBook(id, book);
            URI uri = uriComponentsBuilder.path("/book/{id}")
                    .buildAndExpand(bookId).toUri();
            return ResponseEntity
                    .created(uri)
                    .build();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity
                    .badRequest()
                    .build();
        }
    }
}
