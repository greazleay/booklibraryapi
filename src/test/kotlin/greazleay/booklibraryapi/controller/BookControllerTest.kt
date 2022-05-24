package greazleay.booklibraryapi.controller

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

@SpringBootTest
@AutoConfigureMockMvc
internal class BookControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    val baseUrl = "/api/books"

    @Nested
    @DisplayName("getBooks()")
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
    @DisplayName("getBook()")
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

            // when/then
            mockMvc.get("$baseUrl/$bookId")
                .andDo { print() }
                .andExpect {
                    status { isNotFound() }
                }
        }
    }

}