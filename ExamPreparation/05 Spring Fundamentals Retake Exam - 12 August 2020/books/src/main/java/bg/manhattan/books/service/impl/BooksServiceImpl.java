package bg.manhattan.books.service.impl;

import bg.manhattan.books.model.dto.BookDTO;
import bg.manhattan.books.model.entity.AuthorEntity;
import bg.manhattan.books.model.entity.BookEntity;
import bg.manhattan.books.repository.AuthorRepository;
import bg.manhattan.books.repository.BookRepository;
import bg.manhattan.books.service.BooksService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BooksServiceImpl implements BooksService {
    private final BookRepository repository;
    private final AuthorRepository authorRepository;
    private final ModelMapper mapper;

    public BooksServiceImpl(BookRepository repository, AuthorRepository authorRepository, ModelMapper mapper) {
        this.repository = repository;
        this.authorRepository = authorRepository;
        this.mapper = mapper;
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return this.repository.findAll().stream().map(book -> this.mapper.map(book, BookDTO.class)).toList();
    }

    @Override
    public Optional<BookDTO> getBookById(Long id) {
        return this.repository.findById(id).map(book -> this.mapper.map(book, BookDTO.class));
    }

    @Override
    public void deleteBookById(Long id) {
        this.repository.deleteById(id);
    }

    @Override
    public Long createBook(BookDTO book) {
        AuthorEntity author = this.authorRepository.findByName(book.getAuthor().getName()).orElseGet(() -> new AuthorEntity().setName(book.getAuthor().getName()));

        BookEntity newBook = this.mapper
                .map(book, BookEntity.class)
                .setAuthor(author);

        return this.repository.save(newBook).getId();
    }

    @Override
    public Long updateBook(Long bookId, BookDTO book) {
        AuthorEntity author = this.authorRepository
                .findByName(book.getAuthor().getName())
                .orElseThrow(() -> new IllegalArgumentException("Not found author: " + book.getAuthor().getName() + "!"));

        BookEntity bookEntity = this.repository
                .findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Not found book id: " + bookId + "!"));
        this.mapper.map(book, bookEntity);
        bookEntity.setAuthor(author);
        return this.repository.save(bookEntity).getId();
    }
}
