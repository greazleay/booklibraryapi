package greazleay.booklibraryapi.model

data class Book(
    val id: String,
    val title: String,
    val author: String,
    val numOfPages: Int,
    val isRead: Boolean
)