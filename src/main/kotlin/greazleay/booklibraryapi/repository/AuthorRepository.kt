package greazleay.booklibraryapi.repository

import greazleay.booklibraryapi.model.Author
import org.springframework.data.repository.PagingAndSortingRepository

interface AuthorRepository : PagingAndSortingRepository<Author, String> {

    fun findByFirstName(firstName: String): List<Author>

    fun findByLastName(lastName: String): List<Author>

    fun findByFirstNameAndLastName(firstName: String, lastName: String): List<Author>
    
}