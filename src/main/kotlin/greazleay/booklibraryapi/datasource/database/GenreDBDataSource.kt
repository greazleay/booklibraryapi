package greazleay.booklibraryapi.datasource.database

import greazleay.booklibraryapi.datasource.GenreDataSource
import greazleay.booklibraryapi.model.Genre
import greazleay.booklibraryapi.repository.GenreRepository
import org.springframework.data.repository.*
import org.springframework.stereotype.Repository
import java.util.*

@Repository("genre-db")
class GenreDBDataSource (private val genreRepository: GenreRepository) : GenreDataSource {

    override fun getGenres(): MutableIterable<Genre> = genreRepository.findAll()

    override fun getGenre(genreId: String): Genre = genreRepository.findByIdOrNull(genreId)
        ?: throw NoSuchElementException("Could not find a book with id $genreId")
    override fun addNewGenre(genre: Genre): Genre {
        if (genreRepository.findByName(name = genre.name) != null) {
            throw IllegalArgumentException("Genre with name ${genre.name} already exists")
        }

        return genreRepository.save(genre)
    }

    override fun updateGenre(genre: Genre): Genre {
        val genreToUpdate = genreRepository.findByIdOrNull(genre.id)
            ?: throw NoSuchElementException("Could not find a book with id ${genre.id}")

        genreToUpdate.name = genre.name
        genreToUpdate.description = genre.description

        return genreRepository.save(genreToUpdate)
    }

    override fun deleteGenre(genreId: String) {
        val genreToRemove = genreRepository.findByIdOrNull(genreId)
            ?: throw NoSuchElementException("Could not find a genre with id $genreId")

        genreRepository.delete(genreToRemove)
    }
}