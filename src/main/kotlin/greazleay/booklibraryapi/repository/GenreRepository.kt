package greazleay.booklibraryapi.repository

import greazleay.booklibraryapi.model.Genre
import org.springframework.data.repository.PagingAndSortingRepository

interface GenreRepository : PagingAndSortingRepository<Genre, String> {

    fun findByName(name: String): Genre?
}