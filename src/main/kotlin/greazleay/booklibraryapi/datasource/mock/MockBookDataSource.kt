package greazleay.booklibraryapi.datasource.mock

import greazleay.booklibraryapi.datasource.BookDataSource
import greazleay.booklibraryapi.model.Book
import org.springframework.stereotype.Repository

@Repository("mock db")
class MockBookDataSource : BookDataSource {

    val books = mutableListOf<Book>(
        Book( "Adventures of Huckleberry Finn", "Mark Twain", 500, 1984, true),
        Book( "The Sun Also Rises", "Ernest Hemingway", 1000, 1928, true),
        Book( "The Grapes of Wrath", "ohn Steinbeck", 700, 1939, false),
        Book( "Mrs Dalloway", "Virginia Woolf", 800, 1925, true),
        Book( "The Great Gatsby", "F. Scott Fitzgerald", 1300, 1925, false)
    )

    override fun getBooks(): MutableIterable<Book> = books

    override fun getBook(bookId: String): Book =
        books.firstOrNull() { it.id == bookId }
            ?: throw NoSuchElementException("Could not find a book with id $bookId")

    override fun addNewBook(book: Book): Book {
        if (books.any { it.id == book.id }) {
            throw IllegalArgumentException("Book with ID: ${book.id} already exists.")
        }
        books.add(book)
        return book
    }

    override fun updateBook(book: Book): Book {
        val foundBook = books.firstOrNull() { it.id == book.id }
            ?: throw NoSuchElementException("Could not find a book with id $book.id")

        books.remove(foundBook)
        books.add(book)

        return book
    }

    override fun deleteBook(bookId: String): Unit {
        val foundBook = books.firstOrNull() { it.id == bookId }
            ?: throw NoSuchElementException("Could not find a book with id $bookId")

        books.remove(foundBook)
    }

}