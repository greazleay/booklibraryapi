package greazleay.booklibraryapi.model

import org.hibernate.annotations.GenericGenerator
import java.util.Date
import javax.persistence.*

@Entity(name = "book")
data class Book(

    var title: String,
    var author: String,

    @Column(name = "page_count")
    var pageCount: Int,
    var firstPublish: Int,
    var isRead: Boolean
) {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    val id: String? = null

    val createdAt: Date = Date()

}
