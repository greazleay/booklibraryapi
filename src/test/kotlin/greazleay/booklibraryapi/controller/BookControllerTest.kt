package greazleay.booklibraryapi.controller

import com.fasterxml.jackson.databind.ObjectMapper
import greazleay.booklibraryapi.model.Book
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

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
            val newBook = Book("6", "Invisible Man", "Ralph Ellison", 800, 1952, false)

            // when
            val performPost = mockMvc.post("$baseUrl/add") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newBook)
            }

            // then
            performPost
                .andDo { print() }
                .andExpect {
                    status { isCreated() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.id") { value("6") }
                    jsonPath("$.title") { value("Invisible Man") }
                    jsonPath("$.author") { value("Ralph Ellison") }
                    jsonPath("$.numOfPages") { value(800) }
                }

        }

        @Test
        fun `it should return a 400 BAD REQUEST if book with given ID already exists` () {

            // given
            val invalidBook = Book("4", "Nineteen Eighty-Four", "George Orwell", 650, 1949, false)

            // when
            val performPost = mockMvc.post("$baseUrl/add") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidBook)
            }

            // then
            performPost
                .andDo { print() }
                .andExpect {
                    status { isBadRequest() }
                }

        }
    }

}