package greazleay.booklibraryapi.controller

import greazleay.booklibraryapi.model.Author
import greazleay.booklibraryapi.service.AuthorService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/authors")
class AuthorController (private val authorService: AuthorService) {

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