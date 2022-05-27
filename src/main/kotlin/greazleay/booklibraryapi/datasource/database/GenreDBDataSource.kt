package greazleay.booklibraryapi.datasource.database

import greazleay.booklibraryapi.datasource.GenreDataSource
import greazleay.booklibraryapi.model.Genre
import greazleay.booklibraryapi.repository.GenreRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.*
import org.springframework.stereotype.Repository
import java.util.*

@Repository("database")
class GenreDBDataSource : GenreDataSource {

    @Autowired lateinit var genreRepository: GenreRepository

    override fun getGenres(): MutableIterable<Genre> {
        TODO("Not yet implemented")
    }

    override fun getGenre(genreId: String): Genre {
        TODO("Not yet implemented")
    }

    override fun addNewGenre(genre: Genre): Genre {
        TODO("Not yet implemented")
    }

    override fun updateGenre(genre: Genre): Genre {
        TODO("Not yet implemented")
    }

    override fun deleteGenre(genreId: String) {
        TODO("Not yet implemented")
    }
}