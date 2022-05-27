package greazleay.booklibraryapi.datasource.database

import greazleay.booklibraryapi.datasource.AuthorDataSource
import greazleay.booklibraryapi.model.Author
import greazleay.booklibraryapi.repository.AuthorRepository
import org.springframework.data.repository.*
import org.springframework.stereotype.Repository
import java.util.*

@Repository("author-db")
class AuthorDBDataSource (private val authorRepository: AuthorRepository) : AuthorDataSource {

    override fun getAuthors(): MutableIterable<Author> = authorRepository.findAll()

    override fun getAuthor(authorId: String): Author = authorRepository.findByIdOrNull(id = authorId) 
        ?: throw NoSuchElementException("Could not find author with id: $authorId")

    override fun addNewAuthor(author: Author): Author {
        if (authorRepository.findByFirstNameAndLastName(author.firstName, author.lastName).isNotEmpty()) {
            throw IllegalArgumentException("Author with name ${author.firstName} ${author.lastName} already exists")
        }
        return authorRepository.save(author)
    }

    override fun updateAuthor(author: Author): Author {
        val authorToUpdate = authorRepository.findByIdOrNull(id = author.id)
            ?: throw NoSuchElementException("Could not find author with id: ${author.id}")

        authorToUpdate.firstName = author.firstName
        authorToUpdate.lastName = author.lastName
        authorToUpdate.birthDate = author.birthDate
        authorToUpdate.deathDate = author.deathDate

        return authorRepository.save(authorToUpdate)
    }

    override fun deleteAuthor(authorId: String) {
        val authorToRemove = authorRepository.findByIdOrNull(id = authorId)
            ?: throw NoSuchElementException("Could not find author with id: $authorId")

        authorRepository.delete(authorToRemove)
    }
}