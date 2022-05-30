package greazleay.booklibraryapi.model

import com.fasterxml.jackson.annotation.*
import org.hibernate.annotations.GenericGenerator
import java.util.Date
import javax.persistence.*

@Entity(name = "book")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
class Book(

    @Column(name = "title")
    var title: String,

//    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    var author: Author? = null,

    @Column(name = "description")
    var description: String = "",

    @Column(name = "publication_date")
    var publicationDate: Date,

    @Column(name = "publisher")
    var publisher: String = "",

    @Column(name = "page_count")
    var pageCount: Int,

    @Column(name = "isbn")
    var isbn: String = "",

    @Column(name = "language")
    var language: String = "English",

    @Column(name = "country")
    var country: String = "USA",

    @Column(name = "cover_url")
    var coverUrl: String? = null,

    @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.PERSIST])
    @JoinTable(
        name = "book_genre",
        joinColumns = [JoinColumn(name = "book_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "genre_id")]
    )
    var genres: MutableSet<Genre>? = mutableSetOf(),

    @Column(name = "is_read")
    var isRead: Boolean = false
) {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(name = "id", nullable = false)
    var id: String? = null

    @Column(name = "created_at")
    var createdAt: Date = Date()

    @Column(name = "updated_at")
    var updatedAt: Date = Date()

}
