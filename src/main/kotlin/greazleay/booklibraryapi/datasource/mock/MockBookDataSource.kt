package greazleay.booklibraryapi.datasource.mock

import greazleay.booklibraryapi.datasource.BookDataSource
import greazleay.booklibraryapi.dto.AuthorToBookDto
import greazleay.booklibraryapi.dto.GenreToBookDto
import greazleay.booklibraryapi.model.Author
import greazleay.booklibraryapi.model.Book
import org.springframework.stereotype.Repository
import java.util.*
import kotlin.NoSuchElementException

@Repository("mock db")
class MockBookDataSource : BookDataSource {

    val authors = mutableListOf<Author>(
        Author(firstName = "Mark", lastName = "Twain"),
        Author(firstName = "Ernest", lastName = "Hemingway"),
        Author(firstName = "Ohn", lastName = "Steinbeck"),
        Author(firstName = "Virginia", lastName = "Woolf"),
        Author(firstName = "F. Scott", lastName = "Fitzgerald")
    )

    val books = mutableListOf<Book>(
        Book( title="Adventures of Huckleberry Finn", author=authors[0], publicationDate = Date(1924), pageCount = 500, isRead=true),
        Book( title = "The Sun Also Rises", author = authors[1], publicationDate = Date(1928), pageCount = 1000),
        Book( title = "The Grapes of Wrath", author = authors[2], publicationDate = Date(1939), pageCount = 700),
        Book( title="Mrs Dalloway", author = authors[3], publicationDate =  Date(1925), pageCount = 800, isRead =  true),
        Book( title="The Great Gatsby", author=authors[4], publicationDate = Date(1925), pageCount = 1300)
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

    override fun addAuthorToBook(authorToBook: AuthorToBookDto): Book {
        TODO("Not yet implemented")
    }

    override fun removeAuthorFromBook(authorToBook: AuthorToBookDto): Book {
        TODO("Not yet implemented")
    }

    override fun addGenreToBook(genreToBookDto: GenreToBookDto): Book {
        TODO("Not yet implemented")
    }

    override fun removeGenreFromBook(genreToBookDto: GenreToBookDto): Book {
        TODO("Not yet implemented")
    }

    override fun deleteBook(bookId: String): Unit {
        val foundBook = books.firstOrNull() { it.id == bookId }
            ?: throw NoSuchElementException("Could not find a book with id $bookId")

        books.remove(foundBook)
    }

}