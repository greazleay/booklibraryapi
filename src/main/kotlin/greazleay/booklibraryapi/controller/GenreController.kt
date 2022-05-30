package greazleay.booklibraryapi.controller

import greazleay.booklibraryapi.model.Genre
import greazleay.booklibraryapi.service.GenreService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/genres")
class GenreController (private val genreService: GenreService) {

    @GetMapping("all")
    fun getGenres(): MutableIterable<Genre> = genreService.getGenres()

    @GetMapping("/{genreId}")
    fun getGenre(@PathVariable genreId: String): Genre = genreService.getGenre(genreId)

    @PostMapping("add")
    @ResponseStatus(HttpStatus.CREATED)
    fun addNewGenre(@RequestBody genre: Genre): Genre = genreService.addNewGenre(genre)

    @PatchMapping("update")
    fun updateGenre(@RequestBody genre: Genre): Genre = genreService.updateGenre(genre)

    @DeleteMapping("delete/{genreId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteGenre(@PathVariable genreId: String): Unit = genreService.deleteGenre(genreId)
}