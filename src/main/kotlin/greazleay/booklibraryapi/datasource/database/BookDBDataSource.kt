package greazleay.booklibraryapi.datasource.database

import greazleay.booklibraryapi.datasource.BookDataSource
import greazleay.booklibraryapi.model.Book
import greazleay.booklibraryapi.repository.AuthorRepository
import greazleay.booklibraryapi.repository.BookRepository
import org.springframework.data.repository.*
import org.springframework.stereotype.Repository
import java.util.*

@Repository("book-db")
class BookDBDataSource (
    private val authorRepository: AuthorRepository,
    private val bookRepository: BookRepository
    ) : BookDataSource  {

    override fun getBooks(): MutableIterable<Book> = bookRepository.findAll()

    override fun getBook(bookId: String): Book =
        bookRepository.findByIdOrNull(bookId)
            ?: throw NoSuchElementException("Could not find a book with id $bookId")

    override fun addNewBook(book: Book): Book {
        if (bookRepository.findByTitle(book.title) != null) {
            throw throw IllegalArgumentException("Book with title: ${book.title} already exists.")
        }
        bookRepository.save(book)
        return book
    }

    override fun updateBook(book: Book): Book {
        val bookToUpdate = bookRepository.findByIdOrNull(book.id)
            ?: throw NoSuchElementException("Could not find a book with id ${book.id}")

//        bookToUpdate.title = book.title
//        bookToUpdate.author = book.author
//        bookToUpdate.publicationDate = book.publicationDate
//        bookToUpdate.pageCount = book.pageCount
//        bookToUpdate.isRead = book.isRead

        bookRepository.save(book)
        return bookToUpdate
    }

    override fun deleteBook(bookId: String) {
        val bookToRemove = bookRepository.findByIdOrNull(bookId)
            ?: throw NoSuchElementException("Could not find a book with id $bookId")
        bookRepository.deleteById(bookToRemove.id!!)
    }
}