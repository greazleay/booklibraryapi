package greazleay.booklibraryapi.repository

import greazleay.booklibraryapi.model.Book
import org.springframework.data.repository.PagingAndSortingRepository

interface BookRepository : PagingAndSortingRepository<Book, String> {

    fun findByTitle(title: String): Book?
}