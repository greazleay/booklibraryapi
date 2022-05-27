package greazleay.booklibraryapi.service

import greazleay.booklibraryapi.datasource.AuthorDataSource
import greazleay.booklibraryapi.model.Author
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class AuthorService (@Qualifier("database") private val dataSource: AuthorDataSource) {

    fun getAuthors(): MutableIterable<Author> = dataSource.getAuthors()
    fun getAuthor(authorId: String): Author = dataSource.getAuthor(authorId)
    fun addNewAuthor(author: Author): Author = dataSource.addNewAuthor(author)
    fun updateAuthor(author: Author): Author = dataSource.updateAuthor(author)
    fun deleteAuthor(authorId: String): Unit = dataSource.deleteAuthor(authorId)

}