package greazleay.booklibraryapi.datasource.database

import greazleay.booklibraryapi.datasource.BookDataSource
import greazleay.booklibraryapi.model.Author
import greazleay.booklibraryapi.model.Book
import greazleay.booklibraryapi.model.Genre
import greazleay.booklibraryapi.repository.AuthorRepository
import greazleay.booklibraryapi.repository.BookRepository
import greazleay.booklibraryapi.repository.GenreRepository
import greazleay.booklibraryapi.dto.AuthorToBookDto
import greazleay.booklibraryapi.dto.GenreToBookDto
import org.springframework.data.repository.*
import org.springframework.stereotype.Repository
import java.util.*

@Repository("book-db")
class BookDBDataSource (
    val bookRepository: BookRepository,
    val authorRepository: AuthorRepository,
    val genreRepository: GenreRepository
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

        bookToUpdate.title = book.title
        bookToUpdate.publicationDate = book.publicationDate
        bookToUpdate.pageCount = book.pageCount
        bookToUpdate.isRead = book.isRead

        bookRepository.save(bookToUpdate)
        return bookToUpdate
    }

    override fun addAuthorToBook(authorToBookDto: AuthorToBookDto): Book {
        val bookToUpdate = bookRepository.findByIdOrNull(authorToBookDto.bookId)
            ?: throw NoSuchElementException("Could not find a book with id ${authorToBookDto.bookId}")

        val authorToAdd: Author = authorRepository.findByIdOrNull(authorToBookDto.authorId)
            ?: throw NoSuchElementException("Could not find an author with id ${authorToBookDto.bookId}")

        bookToUpdate.author = authorToAdd

        bookRepository.save(bookToUpdate)
        return bookToUpdate
    }

    override fun removeAuthorFromBook(authorToBookDto: AuthorToBookDto): Book {
        val bookToUpdate = bookRepository.findByIdOrNull(authorToBookDto.bookId)
            ?: throw NoSuchElementException("Could not find a book with id ${authorToBookDto.bookId}")

        val authorToRemove: Author = authorRepository.findByIdOrNull(authorToBookDto.authorId)
            ?: throw NoSuchElementException("Could not find an author with id ${authorToBookDto.authorId}")

        bookToUpdate.author = null

        bookRepository.save(bookToUpdate)
        return bookToUpdate
    }

    override fun addGenreToBook(genreToBookDto: GenreToBookDto): Book {
        val bookToUpdate = bookRepository.findByIdOrNull(genreToBookDto.bookId)
            ?: throw NoSuchElementException("Could not find a book with id ${genreToBookDto.bookId}")

        val genreToAdd: Genre = genreRepository.findByIdOrNull(genreToBookDto.genreId)
            ?: throw NoSuchElementException("Could not find a genre with id ${genreToBookDto.genreId}")

        bookToUpdate.genres?.add(genreToAdd)

        bookRepository.save(bookToUpdate)
        return bookToUpdate
    }

    override fun removeGenreFromBook(genreToBookDto: GenreToBookDto): Book {
        val bookToUpdate = bookRepository.findByIdOrNull(genreToBookDto.bookId)
            ?: throw NoSuchElementException("Could not find a book with id ${genreToBookDto.bookId}")

        val genreToRemove: Genre = genreRepository.findByIdOrNull(genreToBookDto.genreId)
            ?: throw NoSuchElementException("Could not find a genre with id ${genreToBookDto.genreId}")

        bookToUpdate.genres?.remove(genreToRemove)

        bookRepository.save(bookToUpdate)
        return bookToUpdate
    }

    override fun deleteBook(bookId: String) {
        val bookToRemove = bookRepository.findByIdOrNull(bookId)
            ?: throw NoSuchElementException("Could not find a book with id $bookId")
        bookRepository.deleteById(bookToRemove.id!!)
    }
}