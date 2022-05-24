package greazleay.booklibraryapi.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

data class Message(val id: String?, val text: String)

@RestController
@RequestMapping("/api/books")
class BookController {

    @GetMapping("all")
    fun getBooks(): List<Message> {
        return listOf(
            Message("1", "Hello!"),
            Message("2", "Bonjour!"),
            Message("3", "Privet!")
        )
    }
}