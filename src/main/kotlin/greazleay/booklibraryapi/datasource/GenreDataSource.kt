package greazleay.booklibraryapi.datasource

import greazleay.booklibraryapi.model.Genre

interface GenreDataSource {

    fun getGenres(): MutableIterable<Genre>
    fun getGenre(genreId: String): Genre
    fun addNewGenre(genre: Genre): Genre
    fun updateGenre(genre: Genre): Genre
    fun deleteGenre(genreId: String): Unit
}