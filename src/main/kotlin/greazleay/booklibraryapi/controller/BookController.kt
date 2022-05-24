package greazleay.booklibraryapi.controller

import greazleay.booklibraryapi.model.Book
import greazleay.booklibraryapi.service.BookService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/books")
class BookController(private val bookService: BookService) {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(e: IllegalArgumentException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

    @GetMapping("all")
    fun getBooks(): Collection<Book> = bookService.getBooks()

    @GetMapping("/{bookId}")
    fun getBook(@PathVariable bookId: String): Book = bookService.getBook(bookId)

    @PostMapping("add")
    @ResponseStatus(HttpStatus.CREATED)
    fun addNewBook(@RequestBody book: Book): Book = bookService.addNewBook(book)
}