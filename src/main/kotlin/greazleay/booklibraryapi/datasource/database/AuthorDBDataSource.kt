package greazleay.booklibraryapi.datasource.database

import greazleay.booklibraryapi.datasource.AuthorDataSource
import greazleay.booklibraryapi.model.Author
import greazleay.booklibraryapi.repository.AuthorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.*
import org.springframework.stereotype.Repository
import java.util.*

@Repository("database")
class AuthorDBDataSource : AuthorDataSource {

    @Autowired lateinit var authorRepository: AuthorRepository

    override fun getAuthors(): MutableIterable<Author> {
        TODO("Not yet implemented")
    }

    override fun getAuthor(authorId: String): Author {
        TODO("Not yet implemented")
    }

    override fun addNewAuthor(author: Author): Author {
        TODO("Not yet implemented")
    }

    override fun updateAuthor(author: Author): Author {
        TODO("Not yet implemented")
    }

    override fun deleteAuthor(authorId: String) {
        TODO("Not yet implemented")
    }
}