package greazleay.booklibraryapi.controller

import com.fasterxml.jackson.databind.ObjectMapper
import greazleay.booklibraryapi.model.Book
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.web.servlet.*
import java.util.*

@SpringBootTest
@AutoConfigureMockMvc
internal class BookControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper
) {

    val baseUrl = "/api/books"

    @Nested
    @DisplayName("GET /api/books/all")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetBooks {
        @Test
        fun `should return all books` () {

            // when/then
            mockMvc.get("$baseUrl/all")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$[0].numOfPages") { value(500) }
                }
        }
    }

    @Nested
    @DisplayName("GET /api/books/{bookId}")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetBook {
        @Test
        fun `should return the book with the specified id` () {

            // given
            val bookId = "2"

            // when/then
            mockMvc.get("$baseUrl/$bookId")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.numOfPages") { value(1000) }
                    jsonPath("$.title") { value("A Song of Fire and Ice") }
                }
        }

        @Test
        fun `should return 404 NOT FOUND if a book with the specified id doesn't exist` () {

            // given
            val bookId = "does_not_exist"

            // when & then
            mockMvc.get("$baseUrl/$bookId")
                .andDo { print() }
                .andExpect {
                    status { isNotFound() }
                }
        }

    }

    @Nested
    @DisplayName("POST /api/books/add")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class AddNewBook {

        @Test
        fun `it should add a new book` () {

            // given
            val newBook = Book(title = "Invisible Man", publicationDate = Date(1928), pageCount = 1000)

            // when
            val performPostRequest = mockMvc.post("$baseUrl/add") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newBook)
            }

            // then
            performPostRequest
                .andDo { print() }
                .andExpect {
                    status { isCreated() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        json(objectMapper.writeValueAsString(newBook))
                    }
                }

            // validate that the book was created with the supplied values
            mockMvc.get("$baseUrl/${newBook.id}")
                .andExpect { content { json(objectMapper.writeValueAsString(newBook)) } }

        }

        @Test
        fun `it should return a 400 BAD REQUEST if book with given ID already exists` () {

            // given
            val invalidBook = Book("Nineteen Eighty-Four", publicationDate = Date(1928), pageCount = 1000)

            // when
            val performPostRequest = mockMvc.post("$baseUrl/add") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidBook)
            }

            // then
            performPostRequest
                .andDo { print() }
                .andExpect {
                    status { isBadRequest() }
                }

        }
    }

    @Nested
    @DisplayName("PATCH /api/books/update")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class UpdateExistingBook {

        @Test
        fun `it should update an existing book matching the bookID in the request body` () {

            // given
            val updateBook = Book( "Invisible Man", publicationDate = Date(1928), pageCount = 1000)

            // when
            val performPatchRequest = mockMvc.patch("$baseUrl/update") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(updateBook)
            }

            // then
            performPatchRequest
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        json(objectMapper.writeValueAsString(updateBook))
                    }
                }

            // validate that the book was updated in the db
            mockMvc.get("$baseUrl/${updateBook.id}")
                .andExpect { content { json(objectMapper.writeValueAsString(updateBook)) } }

        }

        @Test
        fun `should return 404 NOT FOUND if a book with the specified id in the request body doesn't exist` () {

            // given
            val invalidBook = Book("Invisible Man", publicationDate = Date(1928), pageCount = 1000)

            // when & then
            // when
            val performPatchRequest = mockMvc.patch("$baseUrl/update") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidBook)
            }

            // then
            performPatchRequest
                .andDo { print() }
                .andExpect { status { isNotFound() } }
        }
    }

    @Nested
    @DisplayName("DELETE /api/books/delete/{bookId}")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class DeleteExistingBook {

        @Test
        @DirtiesContext
        fun `it should delete the book with the given ID` () {

            // given
            val bookID = "6"

            // when & then
            mockMvc.delete("$baseUrl/delete/$bookID")
                .andDo { print() }
                .andExpect {
                    status { isNoContent() }
                }

            // validate that the book was deleted in the db
            mockMvc.get("$baseUrl/$bookID")
                .andExpect { status { isNotFound() } }

        }

        @Test
        fun `should return 404 NOT FOUND if a book with the specified id doesn't exist` () {

            // given
            val bookID = "does_not_exist"

            // when & then
            mockMvc.delete("$baseUrl/delete/$bookID")
                .andDo { print() }
                .andExpect {
                    status { isNotFound() }
                }
        }
    }

}