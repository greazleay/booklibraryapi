package greazleay.booklibraryapi.service

import greazleay.booklibraryapi.datasource.GenreDataSource
import greazleay.booklibraryapi.model.Genre
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class GenreService (@Qualifier("database") private val dataSource: GenreDataSource) {

    fun getGenres(): MutableIterable<Genre> = dataSource.getGenres()
    fun getGenre(genreId: String): Genre = dataSource.getGenre(genreId)
    fun addNewGenre(genre: Genre): Genre = dataSource.addNewGenre(genre)
    fun updateGenre(genre: Genre): Genre = dataSource.updateGenre(genre)
    fun deleteGenre(genreId: String): Unit = dataSource.deleteGenre(genreId)
}