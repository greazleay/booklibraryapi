package greazleay.booklibraryapi.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Book(

    @JsonProperty("id")
    val id: String,

    @JsonProperty("title")
    val title: String,

    @JsonProperty("author")
    val author: String,

    @JsonProperty("numOfPages")
    val numOfPages: Int,

    @JsonProperty("firstPublish")
    val firstPublish: Int,

    @JsonProperty("isRead")
    val isRead: Boolean
)