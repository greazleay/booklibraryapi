package greazleay.booklibraryapi.datasource

import greazleay.booklibraryapi.model.Author

interface AuthorDataSource {

    fun getAuthors(): MutableIterable<Author>
    fun getAuthor(authorId: String): Author
    fun addNewAuthor(author: Author): Author
    fun updateAuthor(author: Author): Author
    fun deleteAuthor(authorId: String): Unit
}