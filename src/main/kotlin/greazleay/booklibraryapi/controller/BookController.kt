package greazleay.booklibraryapi.controller

import greazleay.booklibraryapi.dto.AuthorToBookDto
import greazleay.booklibraryapi.dto.GenreToBookDto
import greazleay.booklibraryapi.model.Book
import greazleay.booklibraryapi.service.BookService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/books")
class BookController(private val bookService: BookService) {

    @GetMapping("all")
    fun getBooks(): MutableIterable<Book> = bookService.getBooks()

    @GetMapping("/{bookId}")
    fun getBook(@PathVariable bookId: String): Book = bookService.getBook(bookId)

    @PostMapping("add")
    @ResponseStatus(HttpStatus.CREATED)
    fun addNewBook(@RequestBody book: Book): Book = bookService.addNewBook(book)

    @PatchMapping("update")
    fun updateBook(@RequestBody book: Book): Book = bookService.updateBook(book)

    @PutMapping("add-author")
    fun addAuthor(@RequestBody authorToBookDto: AuthorToBookDto): Book = bookService.addAuthorToBook(authorToBookDto)

    @PutMapping("add-genre")
    fun addGenre(@RequestBody genreToBookDto: GenreToBookDto): Book = bookService.addGenreToBook(genreToBookDto)
    
    @PutMapping("remove-author")
    fun removeAuthor(@RequestBody authorToBookDto: AuthorToBookDto): Book = bookService.removeAuthorFromBook(authorToBookDto)

    @PutMapping("remove-genre")
    fun removeGenre(@RequestBody genreToBookDto: GenreToBookDto): Book = bookService.removeGenreFromBook(genreToBookDto)

    @DeleteMapping("delete/{bookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteBook(@PathVariable bookId: String): Unit = bookService.deleteBook(bookId)
}