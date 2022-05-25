package greazleay.booklibraryapi.datasource.database

import greazleay.booklibraryapi.datasource.BookDataSource
import greazleay.booklibraryapi.model.Book
import org.springframework.stereotype.Repository

@Repository("database")
class DBDataSource : BookDataSource {
    override fun getBooks(): Collection<Book> {
        TODO("Not yet implemented")
    }

    override fun getBook(bookId: String): Book {
        TODO("Not yet implemented")
    }

    override fun addNewBook(book: Book): Book {
        TODO("Not yet implemented")
    }

    override fun updateBook(book: Book): Book {
        TODO("Not yet implemented")
    }

    override fun deleteBook(bookId: String) {
        TODO("Not yet implemented")
    }
}