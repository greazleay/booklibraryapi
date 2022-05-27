package greazleay.booklibraryapi.controller

import greazleay.booklibraryapi.model.Author
import greazleay.booklibraryapi.service.AuthorService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/authors")
class AuthorController (private val authorService: AuthorService) {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(e: IllegalArgumentException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

    @GetMapping("all")
    fun getAuthors(): MutableIterable<Author> = authorService.getAuthors()

    @GetMapping("/{authorId}")
    fun getAuthor(@PathVariable authorId: String): Author = authorService.getAuthor(authorId)

    @PostMapping("add")
    @ResponseStatus(HttpStatus.CREATED)
    fun addNewAuthor(@RequestBody author: Author): Author = authorService.addNewAuthor(author)

    @PatchMapping("update")
    fun updateAuthor(@RequestBody author: Author): Author = authorService.updateAuthor(author)

    @DeleteMapping("delete/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteAuthor(@PathVariable authorId: String): Unit = authorService.deleteAuthor(authorId)
}